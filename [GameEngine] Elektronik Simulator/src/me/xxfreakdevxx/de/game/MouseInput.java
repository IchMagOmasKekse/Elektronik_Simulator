package me.xxfreakdevxx.de.game;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.xxfreakdevxx.de.game.showroom.ShowRoom;

@SuppressWarnings("unused")
public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	public static Rectangle mouse_click_bounds = new Rectangle(0,0,1,1);
	public static boolean rightclick = false;
	public static int x_con = 0;
	public static int y_con = 0;
	public static int x_uncon = 0;
	public static int y_uncon = 0;
	public static int x_clicked_con = 0;
	public static int y_clicked_con = 0;
	public static int x_clicked_uncon = 0;
	public static int y_clicked_uncon = 0;
	
	public MouseInput() {
		this.handler = Simulator.getInstance().getHandler();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Camera camera = Simulator.camera;
		x_uncon = e.getX();
		y_uncon = e.getY();
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());		
		x_con = mx;
		y_con = my;
		super.mouseMoved(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Camera camera = Simulator.camera;
		x_clicked_uncon = e.getX();
		y_clicked_uncon = e.getY();
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());		
		x_clicked_con = mx;
		y_clicked_con = my;
		
		mouse_click_bounds.setBounds(x_clicked_uncon, y_clicked_uncon, 1, 1);
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(ShowRoom.scene != null) ShowRoom.scene.getCircuitBoard().mousePressed(e);
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
