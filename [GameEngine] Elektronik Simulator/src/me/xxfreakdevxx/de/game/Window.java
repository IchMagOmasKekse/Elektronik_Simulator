package me.xxfreakdevxx.de.game;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	public static JFrame frame = null;
	public Window(int width, int height, String title, Simulator simulator) {
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
//		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(600, 450));
		
		frame.add(simulator);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setCursor(new Cursor(0));
	}
	
}
