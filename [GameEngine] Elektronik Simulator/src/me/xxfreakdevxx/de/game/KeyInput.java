package me.xxfreakdevxx.de.game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.xxfreakdevxx.de.game.showroom.ShowRoom;

@SuppressWarnings("unused")
public class KeyInput extends KeyAdapter {
	
	Handler handler;
	public static boolean isCTRL = false;
	public static boolean isSHIFT = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(e.getKeyCode()) {
		case KeyEvent.VK_O:
			ShowRoom.scene.getCircuitBoard().showCopperSide=!ShowRoom.scene.getCircuitBoard().showCopperSide;
			break;
		case KeyEvent.VK_SPACE:
			if(MouseInput.rightclick) {
				MouseInput.rightclick = false;
				ShowRoom.zoom = 1d;
				Simulator.camera.setX(0);
				Simulator.camera.setY(0);
			}
			break;
		case KeyEvent.VK_P:
			ShowRoom.scene.getCircuitBoard().showPinNames=!ShowRoom.scene.getCircuitBoard().showPinNames;
			break;
		case KeyEvent.VK_F3:
			Simulator.showF3=!Simulator.showF3;
			break;
		case KeyEvent.VK_CONTROL:
			isCTRL = true;
			break;
		case KeyEvent.VK_SHIFT:
			isSHIFT = true;
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(e.getKeyCode()) {
		case KeyEvent.VK_CONTROL:
			isCTRL = false;
			break;
		case KeyEvent.VK_SHIFT:
			isSHIFT = false;
			break;
		}
	}
	
}
