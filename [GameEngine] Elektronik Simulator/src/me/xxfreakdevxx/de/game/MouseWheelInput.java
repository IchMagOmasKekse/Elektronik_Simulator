package me.xxfreakdevxx.de.game;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;

import me.xxfreakdevxx.de.game.showroom.ShowRoom;

public class MouseWheelInput implements MouseWheelListener {
	DecimalFormat f = new DecimalFormat("#0.0");

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		return;
//		switch (e.getWheelRotation()) {
//		case 1:
//			if(ShowRoom.zoom < ShowRoom.max_zoom) {
//				ShowRoom.zoom+=0.1d;
//				System.out.println(f.format(ShowRoom.zoom).replace(",", "."));
//				ShowRoom.zoom = Double.parseDouble(f.format(ShowRoom.zoom).replace(",", "."));
//				Game.camera.tickMouse();
//			}
//			break;
//		case -1:
//			if(ShowRoom.zoom > ShowRoom.min_zoom) {
//				ShowRoom.zoom-=0.1d;
//				System.out.println(f.format(ShowRoom.zoom).replace(",", "."));
//				ShowRoom.zoom = Double.parseDouble(f.format(ShowRoom.zoom).replace(",", "."));
//				Game.camera.tickMouse();
//			}
//			
//			break;
//
//		default:
//			break;
//		}
	}

}
