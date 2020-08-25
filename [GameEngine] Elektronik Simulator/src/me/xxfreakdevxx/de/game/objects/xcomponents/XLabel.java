package me.xxfreakdevxx.de.game.objects.xcomponents;

import java.awt.Graphics;

import me.xxfreakdevxx.de.game.Simulator;

public class XLabel extends XComponent {

	public XLabel(String text, int sizex, int sizey, int x, int y) {
		super(Simulator.getRandomId(), "", text, sizex, sizey, x, y);
		// TODO Auto-generated constructor stub
	}
	public XLabel(String text, int sizex, int sizey, int x, int y, boolean inCenter) {
		super(Simulator.getRandomId(), "", text, sizex, sizey, x, y, inCenter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		if(font != null) g.setFont(font);
//		g.setColor(background_color);
//		g.fillRect(x, y, xSize, ySize);
//		g.setColor(border_color);
//		for(int i = border_size; i != 0; i--) {
//			g.drawRect(x+i-1, y+i-1, xSize-i-i+1, ySize-i-i+1);
//		}
		if(text.equals("")) {
			g.setColor(font_color_disabled);
			g.drawString(ghost_text, x+border_size+2, y+(ySize/2)+(g.getFont().getSize()/2));
		} else {
			g.drawString(text, x+border_size+2, y+(ySize/2)+(g.getFont().getSize()/2));
			g.setColor(font_color);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hovered() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clicked() {
		// TODO Auto-generated method stub
		
	}

}
