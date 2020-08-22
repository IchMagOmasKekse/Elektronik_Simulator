package me.xxfreakdevxx.de.game.showroom;

import java.awt.Graphics;

public class ShowRoom {
	
	public static Scene scene = null;
	public static double max_zoom = 5.0D;
	public static double min_zoom = 0.2D;
	public static double zoom = 1.0D;
	
	public ShowRoom() {
		System.out.println("ShowRoom wird geladen...");
		preInit();
		init();
		postInit();
	}
	
	public void preInit() {
		scene = new DebugScene();
	}
	public void init() {
		
	}
	public void postInit() {
		
	}
	
	public static void render(Graphics g) {
		if(scene !=  null) scene.render(g);
	}
	public static void tick() {
		if(scene !=  null) scene.tick();
	}

	
	/*
	 * DIE OFFSETS MÜSSEN RICHTIG BERECHNET WERDEN ODER EIN ANDERER ALGORITHMUS MUSS HER
	 */
	static double off = -10;
	public static int getMouseOffsetX() {

		if(zoom == 1.0d) {
			return 0;
		}else if(zoom < 1.0D) {
			return 60 * (int)(((zoom - 1) * 10));
		}else if(zoom > 1.0D) {
			return -57 * (int)((zoom * 10 -0) * -10);
		}
		return 0;
	}
	public static int getMouseOffsetY() {

		if(zoom == 1.0d) {
			return 0;
		}else if(zoom < 1.0D) {
			
			return 50 * (int)((100 - (zoom * 10) ) / 10);
		}else if(zoom > 1.0D) {
			return -45 * (int)((1.0d - zoom) * -10);
		}
		return 0;
	}
	
}
