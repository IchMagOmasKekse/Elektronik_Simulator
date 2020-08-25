package me.xxfreakdevxx.de.game;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotion extends MouseMotionAdapter {
	
	public static int mouse_x = 0;
	public static int mouse_y = 0;
	public static Rectangle mouse_bounds = new Rectangle(0,0,1,1);
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_x = e.getX();
		mouse_y = e.getY();
		mouse_bounds.setLocation(mouse_x, mouse_y);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		mouse_x = e.getX();
		mouse_y = e.getY();		
		mouse_bounds.setBounds(mouse_x, mouse_y, 1, 1);
	}
	
}
