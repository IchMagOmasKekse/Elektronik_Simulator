package me.xxfreakdevxx.de.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import me.xxfreakdevxx.de.game.objects.xcomponents.XManager;
import me.xxfreakdevxx.de.game.showroom.ShowRoom;

@SuppressWarnings({ "unused", "serial" })
public class Simulator extends Canvas implements Runnable {
	
	/* Window */
	public static int windowWidth = 1200;
	public static int windowHeight = 900;
	
	/* FPS */
	public static int fps = 0;
	public int maxFps = -1;
	public static int gameTicks = 3;
	private int ticks = 0;
	
	/* Debugging */
	public static final Random r = new Random();
	public static boolean showF3 = false;
	public static DecimalFormat f = new DecimalFormat("#0.0");
	
	/* Running */
	public static boolean isReady = false;
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private BufferedImage level = null;
	public static Camera camera;
	private TextureAtlas textureAtlas;
	public static final int blocksize = 32;
	
	static Simulator instance;
	public static Simulator getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		new Simulator();
	}
	
	public Simulator() {
		instance = this;
		setBackground(Color.BLACK);
		new Window(windowWidth, windowHeight, "Platinen Simulation", this);
		start();
		
		handler = new Handler();
		camera = new Camera(0,0);
		textureAtlas = new TextureAtlas();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput());
		this.addMouseMotionListener(new MouseMotion());
		this.addMouseWheelListener(new MouseWheelInput());

		preInit();
		init();
		postInit();
		
//		level = textureAtlas.loadImage("/wizard_level.png");
//		loadLevel(level);
	}
	
	public void preInit() {
		
	}
	public void init() {
		
	}
	public void postInit() {
		new ShowRoom();
		isReady = true;
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	
	@Override
	public void run() {
		/*
		 * GameLoop - Made by Notch
		 */
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while(delta >= gameTicks) {
					tick();
					//update++;
					delta--;
				}
				if(maxFps < 0) {
					render();
					frames++;
				}else{
					if((frames < maxFps)) {
						render();
						frames++;				
					}
				}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps=frames;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		windowWidth = Window.frame.getWidth();
		windowHeight = Window.frame.getHeight();
		if(isReady && ShowRoom.complist != null) ShowRoom.complist.bounds.setSize(150, windowHeight-ShowRoom.complist.bounds.y-40);
		for(int i = 0; i < handler.object.size(); i++) {
			
		}
		handler.tick();
		XManager.tick();
		
		if(MouseInput.rightclick) camera.tickMouse();
	}
	private Color debug_background_color = new Color(0f, 0f, 0f, 0.3f);
	private Font debug_font = new Font("Arial", 0, 13);
	private Font debug_font_bold = new Font("Arial", 1, 13);
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		Graphics2D g2gui = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, windowWidth, windowHeight);
		g2d.translate(-camera.getX(), -camera.getY());
		g2d.scale(ShowRoom.zoom, ShowRoom.zoom);
		
		handler.render(g);
		g2d.translate(camera.getX(), camera.getY());
		
		XManager.renderComps(g2gui);
		if(showF3 && isReady) {
			g2gui.setColor(debug_background_color);
			g2gui.fillRect(15, 5, 640, 85);
			g2gui.setColor(Color.YELLOW);
			g2gui.setFont(debug_font_bold);
			g2gui.drawString("DRÜCKE F3 UM DAS MENÜ ZU SCHLIEßEN", 300, 20);
			g2gui.setColor(Color.WHITE);
			g2gui.setFont(debug_font);
			g2gui.drawString("FPS: "+fps, windowWidth-60, 20);
			g2gui.drawString("Zoom: "+ShowRoom.zoom, 20, 35);
			g2gui.drawString("offX: "+ShowRoom.getMouseOffsetX(), 20, 50);
			g2gui.drawString("offY: "+ShowRoom.getMouseOffsetY(), 20, 65);
			g2gui.setColor(Color.ORANGE); g2gui.drawString("Zoom Funktion STRG + Mousrad ist deaktiviert in Klasse MouseWheelInput ist alles auskommentiert", 20, 80);g2gui.setColor(Color.WHITE);
			g2gui.drawString("X/Y List: "+ShowRoom.complist.x+"/"+ShowRoom.complist.y + " // "+ShowRoom.complist.xSize+"/"+ShowRoom.complist.ySize, 20, 95);
			g2gui.drawString("X/Y Mouse: "+MouseMotion.mouse_x+"/"+MouseMotion.mouse_y, 20, 110);
			
		}
		g2gui.setColor(Color.WHITE);
		g2gui.setFont(debug_font);
		g2gui.drawString("FPS: "+fps, windowWidth-100, 20);
		g2gui.drawString("Zoom: "+ShowRoom.zoom, windowWidth-100, 35);
		if(isReady) g2gui.drawString("Löcher: "+ShowRoom.scene.getCircuitBoard().pins.size(), windowWidth-100, 50);
		
		g2gui.setColor(Color.BLACK);
		g2gui.drawLine(MouseMotion.mouse_x-15, MouseMotion.mouse_y, MouseMotion.mouse_x+15, MouseMotion.mouse_y);
		g2gui.drawLine(MouseMotion.mouse_x, MouseMotion.mouse_y-15, MouseMotion.mouse_x, MouseMotion.mouse_y+15);
		
		g.dispose();
		bs.show();
	}
	
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
//				if(red == 255) handler.addObject(new StoneWallBlock(ID.BLOCK, new Location(xx*blocksize, yy*blocksize), 32, 32));
//				if(green == 255) handler.addObject(new Monster(ID.ENEMY, new Location(xx*blocksize, yy*blocksize), 32, 32, 15.0D));
//				if(blue == 255) handler.addObject(new Player(ID.PLAYER, new Location(xx*blocksize, yy*blocksize), 100.0D));
			}
		}
	}
	
	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public static int calculateStringWidth(Font font, String enteredText) {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm = img.getGraphics().getFontMetrics(font);
		int width = fm.stringWidth(enteredText);
		return width;
	}
	
	@SuppressWarnings("deprecation")
	public static String getTimeInString() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		return d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
	}
	@SuppressWarnings("deprecation")
	public static String getDateInString() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		int day = d.getDate();
		int mon = d.getMonth();
		int year = d.getYear();
		return day+":"+mon+":"+year;
	}
	public static void log(String prefix, String... strings) {
		if(prefix.equals("")) prefix = "Debug";
		for(String s : strings) {
			System.out.println("["+getTimeInString()+"]["+prefix+"] "+s);
		}
	}
	
	public static int getRandomId() {
		return r.nextInt(10000);
	}
	
}
