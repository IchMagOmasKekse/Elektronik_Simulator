package me.xxfreakdevxx.de.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.xxfreakdevxx.de.game.object.GameObject;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.entity.Bullet;
import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

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
			
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ID.PLAYER) {
					int w = (int)tempObject.getBounds().getWidth();
					int h = (int)tempObject.getBounds().getHeight();
					
					int pX = tempObject.getLocation().getIntX()+(w/2);
					int pY = tempObject.getLocation().getIntY()+(h/2);
					int sizeX = 8;
					int sizeY = 8;
					handler.addObject(new Bullet(ID.BULLET, new Location(pX, pY), ((LivingEntity)tempObject), sizeX, sizeY, mx, my));
				}
			}
		}
	}
}
