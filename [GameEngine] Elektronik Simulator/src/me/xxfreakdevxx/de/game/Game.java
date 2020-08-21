package me.xxfreakdevxx.de.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import me.xxfreakdevxx.de.game.showroom.DebugScene;
import me.xxfreakdevxx.de.game.showroom.ShowRoom;

@SuppressWarnings({ "unused", "serial" })
public class Game extends Canvas implements Runnable {
	
	/* Window */
	public static int windowWidth = 1200;
	public static int windowHeight = 900;
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private BufferedImage level = null;
	private Camera camera;
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
			while(delta >= 1) {
				tick();
				//update++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		for(int i = 0; i < handler.object.size(); i++) {
			
		}
		handler.tick();
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, windowWidth, windowHeight);
		g2d.translate(-camera.getX(), -camera.getY());
		
		handler.render(g);
		g2d.translate(camera.getX(), camera.getY());
		
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
	
	public Camera getCamera() {
		return camera;
	}
	
}
