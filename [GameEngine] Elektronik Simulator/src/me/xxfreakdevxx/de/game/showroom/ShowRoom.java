package me.xxfreakdevxx.de.game.showroom;

import java.awt.Graphics;

public class ShowRoom {
	
	public static Scene scene = null;
	
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
	
}
