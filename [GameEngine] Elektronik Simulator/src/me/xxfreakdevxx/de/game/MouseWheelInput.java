package me.xxfreakdevxx.de.game;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;

import me.xxfreakdevxx.de.game.showroom.ShowRoom;

public class MouseWheelInput implements MouseWheelListener {
	

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		switch (e.getWheelRotation()) {
		case 1:
			if(KeyInput.isCTRL) {				
				if(ShowRoom.zoom > ShowRoom.min_zoom) {
					ShowRoom.zoom-=0.1d;
					System.out.println(Simulator.f.format(ShowRoom.zoom).replace(",", "."));
					ShowRoom.zoom = Double.parseDouble(Simulator.f.format(ShowRoom.zoom).replace(",", "."));
					Simulator.camera.tickMouse();
				}
			}else {
				if(ShowRoom.complist.isHovering) {
					ShowRoom.complist.scrollUp();
				}
			}
			break;
		case -1:
			if(KeyInput.isCTRL) {				
				if(ShowRoom.zoom < ShowRoom.max_zoom) {
					ShowRoom.zoom+=0.1d;
					System.out.println(Simulator.f.format(ShowRoom.zoom).replace(",", "."));
					ShowRoom.zoom = Double.parseDouble(Simulator.f.format(ShowRoom.zoom).replace(",", "."));
					Simulator.camera.tickMouse();
				}
			}else {
				if(ShowRoom.complist.isHovering) {
					ShowRoom.complist.scrollDown();
				}
			}
			
			break;

		default:
			break;
		}
	}

}
