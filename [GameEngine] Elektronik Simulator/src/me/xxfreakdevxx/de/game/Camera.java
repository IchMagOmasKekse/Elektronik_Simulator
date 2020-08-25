package me.xxfreakdevxx.de.game;

import me.xxfreakdevxx.de.game.electricity.EComp;

public class Camera {
	private float x,y;
	public static boolean stayPositiveCoords = false;
	
	public Camera(float x, float y) {
		this.x=x;
		this.y=y;
	}
	
	public void tick(EComp object) {
		x += ((object.getLocation().getX() - x) - Simulator.windowWidth/2) * 0.05f;
		y += ((object.getLocation().getY() - y) - Simulator.windowHeight/2) * 0.05f;
		
		if(stayPositiveCoords) {			
			if(x <= 0) x = 0; 
			if(x >= (Simulator.windowWidth + 32)) x = (Simulator.windowWidth+32);
			if(y <= 0) y = 0; 
			if(x >= (Simulator.windowHeight + 16)) x = (Simulator.windowHeight+16);
		}
	}
	GameVector mouseMove = null;
	public void tickMouse() {
		x += ((MouseMotion.mouse_x) - Simulator.windowWidth/2) * 0.05f;
		y += ((MouseMotion.mouse_y) - Simulator.windowHeight/2) * 0.05f;
		
		if(stayPositiveCoords) {			
			if(x <= 0) x = 0; 
			if(x >= (Simulator.windowWidth + 32)) x = (Simulator.windowWidth+32);
			if(y <= 0) y = 0; 
			if(x >= (Simulator.windowHeight + 16)) x = (Simulator.windowHeight+16);
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
