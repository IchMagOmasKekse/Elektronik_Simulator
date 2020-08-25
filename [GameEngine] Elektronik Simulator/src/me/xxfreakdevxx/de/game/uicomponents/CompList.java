package me.xxfreakdevxx.de.game.uicomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import me.xxfreakdevxx.de.game.KeyInput;
import me.xxfreakdevxx.de.game.Simulator;
import me.xxfreakdevxx.de.game.electricity.EComp;
import me.xxfreakdevxx.de.game.electricity.component.Resistor;
import me.xxfreakdevxx.de.game.objects.xcomponents.XComponent;

public class CompList extends XComponent {
	
	private ArrayList<ListEntry> content = new ArrayList<ListEntry>();
	
	private static int yOffset = 0;
	private static int originX = 20;
	private static int originY = 20;
	private static int slotWidth = 0;
	private static int slotHeight = 0;
	
	/* Darstellung */
	private Color background_color = new Color(0.36862745f, 0.45882353f, 0.29803922f, 0.8f); //grünlich
	private Color background_color_solid = new Color(0.36862745f, 0.45882353f, 0.29803922f, 1f); //grünlich
//	private Color background_color = new Color(0.980392157f, 1f, 0.749019608f, 0.5f); //gelblich
	private Font font_title = new Font("Candara", 1, 15);
	private Color font_title_color = Color.white;
	public Font font_default = new Font("Arial", 1, 11);
	private Color font_default_color = Color.white;
	
	public CompList(String text, int x, int y, int sizex, int sizey) {
		super(Simulator.getRandomId(), "Ghost Text", text, x, y, sizex, sizey);
		this.xcomp = this;
		originX = bounds.x+10;
		originY = bounds.y+20;
		slotWidth = bounds.width-20;
		slotHeight = (bounds.width);
		for(int i = 0; i != 100; i++) {
			content.add(new ListEntry(i,
					new Resistor("R1", Simulator.r.nextInt(100), Simulator.r.nextInt(230))));
		}
	}

	@Override
	public void render(Graphics g) {
		/* Box rendern */
		g.setFont(font_default);
		g.setColor(font_default_color);
		g.setColor(background_color);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(background_color_solid);
		
		/* Bauteile rendern */
		for(ListEntry e : content) {
			if(e != null) {
				e.render(g);
			}
		}
		/* ------------------------- */
		
		/* - Solide Boxen für Oberes und Unteres Ende
		 * - Muss unbedingt erst NACH den Elektro. Bauteilen gerendert werden */
		g.fillRect(bounds.x, bounds.y, bounds.width, 20);
		g.fillRect(bounds.x, bounds.height, bounds.width, 20);
		
		/* Title Rendern
		 * - Muss unbedingt erst NACH den Elektro. Bauteilen gerendert werden */
		g.setFont(font_title);
		g.setColor(font_title_color);
		g.drawString(text, bounds.x+15, bounds.y+15);
		g.setColor(Color.black);
		g.drawLine(bounds.x, bounds.y+20, bounds.x+bounds.width, bounds.y+20);
		g.drawLine(bounds.x, bounds.height, bounds.x+bounds.width, bounds.height);
		
	}
	
	public void scrollUp() {
		if(KeyInput.isSHIFT) yOffset -= 50;
		else yOffset -= 10;
	}
	public void scrollDown() {
		if(KeyInput.isSHIFT) yOffset += 50;
		else yOffset += 10;
	}

	@Override
	public void update() {
		checkForInteraction();
		
		for(ListEntry e : content) {
			if(e != null) {
				e.getComponent().tick();
				e.getComponent().getLocation().setX(originX+(slotWidth/2)-(e.getComponent().width/2));
				e.getComponent().getLocation().setY(originY+yOffset+(slotHeight/2)-(e.getComponent().height/2)+(slotHeight*e.slotId));
			}
		}
	}
	@Override
	public void hovered() { }

	@Override
	public void clicked() { }
	
	public static class ListEntry {
		
		private int slotId = 0;
		private EComp comp = null;
		private Color slotColorBorder = new Color(229, 217, 255);
		private Color slotColorFilling = new Color(0.89803922f, 0.85098039f, 1f, 0.05f);
		private Color tempColor = null;
		
		public ListEntry(int slotId, EComp comp) {
			this.slotId = slotId;
			this.comp = comp;
		}
		
		public void render(Graphics g) {
			if(((originY+yOffset+(slotHeight*slotId+(slotId*10))+slotHeight) > 0) && originY+yOffset+(slotHeight*slotId+(slotId*10)) < Simulator.windowHeight) {
				tempColor = g.getColor();
				g.setColor(slotColorFilling);
				g.fillRect(originX, originY+yOffset+(slotHeight*slotId+(slotId*10)), slotWidth, slotHeight);
				g.setColor(slotColorBorder);
				g.drawRect(originX, originY+yOffset+(slotHeight*slotId+(slotId*10)), slotWidth, slotHeight);
				g.setColor(Color.ORANGE);
				g.drawString(comp.typeName, originX+4, originY+yOffset+(slotHeight*slotId+(slotId*10))+15);
			}

			comp.render(g, originX+(slotWidth/2)-(comp.width/2),
					originY+(slotHeight/2)-(comp.height/2)+yOffset+(slotHeight*slotId+(slotId*10)),
					comp.width,
					comp.height);
			g.setColor(tempColor);
		}
		
		public EComp getComponent() {
			return comp;
		}
		
	}
	
}
