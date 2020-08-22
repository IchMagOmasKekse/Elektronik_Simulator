package me.xxfreakdevxx.de.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotion extends MouseMotionAdapter {
	
	public static int mouse_x = 0;
	public static int mouse_y = 0;
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_x = e.getX();
		mouse_y = e.getY();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		mouse_x = e.getX();
		mouse_y = e.getY();		
	}
	
}
