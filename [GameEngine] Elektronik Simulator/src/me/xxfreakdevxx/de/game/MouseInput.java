package me.xxfreakdevxx.de.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	
	public MouseInput() {
		this.handler = Game.getInstance().getHandler();
	}
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {			
			Camera camera = Game.getInstance().getCamera();
			int mx = (int) (e.getX() + camera.getX());
			int my = (int) (e.getY() + camera.getY());
			
			
		}
	}
}
