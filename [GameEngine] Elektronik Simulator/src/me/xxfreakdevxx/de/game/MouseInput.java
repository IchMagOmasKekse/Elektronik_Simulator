package me.xxfreakdevxx.de.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.xxfreakdevxx.de.game.showroom.ShowRoom;

@SuppressWarnings("unused")
public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	public static boolean rightclick = false;
	
	public MouseInput() {
		this.handler = Game.getInstance().getHandler();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			ShowRoom.scene.getCircuitBoard().mousePressed(e);
		}else if(e.getButton() == MouseEvent.BUTTON3) {
			rightclick = true;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3) {
			rightclick = false;
		}
		super.mouseReleased(e);
	}
}
