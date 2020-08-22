package me.xxfreakdevxx.de.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import me.xxfreakdevxx.de.game.showroom.DebugScene;
import me.xxfreakdevxx.de.game.showroom.ShowRoom;

@SuppressWarnings({ "unused", "serial" })
public class Game extends Canvas implements Runnable {
	
	/* Window */
	public static int windowWidth = 1200;
	public static int windowHeight = 900;
	
	/* FPS */
	public static int fps = 0;
	public int maxFps = -1;
	public static int gameTicks = 3;
	private int ticks = 0;	
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private BufferedImage level = null;
	public static Camera camera;
	private TextureAtlas textureAtlas;
	public static final int blocksize = 32;
	
	static Game instance;
	public static Game getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		instance = this;
		setBackground(Color.BLACK);
		new Window(windowWidth, windowHeight, "GameEngine Preset", this);
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
		for(int i = 0; i < handler.object.size(); i++) {
			
		}
		handler.tick();
		
		if(MouseInput.rightclick) camera.tickMouse();
	}
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
		g2gui.setColor(Color.BLACK);
		g2gui.fillRect(15, 5, 100, 70);
		g2gui.setColor(Color.WHITE);
		g2gui.drawString("FPS: "+fps, 20, 20);
		g2gui.setColor(Color.WHITE);
		g2gui.drawString("Zoom: "+ShowRoom.zoom, 20, 35);
		g2gui.setColor(Color.WHITE);
		g2gui.drawString("offX: "+ShowRoom.getMouseOffsetX(), 20, 50);
		g2gui.setColor(Color.WHITE);
		g2gui.drawString("offY: "+ShowRoom.getMouseOffsetY(), 20, 65);
		
		g2gui.drawLine(MouseMotion.mouse_x-100, MouseMotion.mouse_y, MouseMotion.mouse_x+100, MouseMotion.mouse_y);
		g2gui.drawLine(MouseMotion.mouse_x, MouseMotion.mouse_y-100, MouseMotion.mouse_x, MouseMotion.mouse_y+100);
		
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
	
}
