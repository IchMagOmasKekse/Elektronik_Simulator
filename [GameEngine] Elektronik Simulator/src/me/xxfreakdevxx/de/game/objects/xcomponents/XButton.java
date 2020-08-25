package me.xxfreakdevxx.de.game.objects.xcomponents;

import java.awt.Graphics;

import me.xxfreakdevxx.de.game.Simulator;

public class XButton extends XComponent {

	public XButton(String text, int sizex, int sizey, int x, int y, boolean inCenter) {
		super(Simulator.getRandomId(), "", text, sizex, sizey, x, y, inCenter);
		this.xcomp = this;
	}
	
	public XButton(String text, int sizex, int sizey, int x, int y) {
		super(Simulator.getRandomId(), "", text, sizex, sizey, x, y);
		this.xcomp = this;
	}

	@Override
	public void render(Graphics g) {
		if(font != null) g.setFont(font);
		if(isHovering == false)g.setColor(background_color);
		else g.setColor(highlight_color);
		
		if(isHovering == false) {			
			if(isSelected == false)g.setColor(background_color);
			else g.setColor(highlight_color);
		}
		if(showTexture && texture != null) {
			g.drawImage(texture, x-10, y, xSize+10, ySize, null);
		}else {				
			g.fillRect(x, y, xSize, ySize);
			g.setColor(border_color);
			for(int i = border_size; i != 0; i--) {
				g.drawRect(x+i-1, y+i-1, xSize-i-i+1, ySize-i-i+1);
			}
		}
		g.setColor(font_color);
		g.drawString(text, x+border_size+2, y+(ySize/2)+(g.getFont().getSize()/2));
	}

	@Override
	public void update() {
		checkForInteraction();
	}

	@Override
	public void hovered() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clicked() {
		action.run();
		XManager.selectedComponent = null;
		isSelected = false;
	}

}
