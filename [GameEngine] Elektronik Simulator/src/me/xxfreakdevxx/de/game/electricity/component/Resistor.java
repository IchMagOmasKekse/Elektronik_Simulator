package me.xxfreakdevxx.de.game.electricity.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.Simulator;
import me.xxfreakdevxx.de.game.electricity.EComp;
import me.xxfreakdevxx.de.game.showroom.ShowRoom;

public class Resistor extends EComp {
	
	/* String der in der Elektro Liste angezeigt wird. Integer ist die Halbe Breite des Bauteils */
	private HashMap<String, Integer> lore = new HashMap<String, Integer>();
	public Color fillcolor = new Color(Simulator.r.nextInt(255), Simulator.r.nextInt(255), Simulator.r.nextInt(255));
	
	public Resistor(String name, double ohm, double max_volt) {
		super(new Location(500, 500), 40, 10);
		this.name = name;
		this.isForPickList = true;
		this.current_ohm = ohm;
		this.max_volt = max_volt;
		if(max_volt <= 0d) max_volt = 15d;
		if(current_ohm <= 0d) current_ohm = 15d;
		this.max_ampere = Double.parseDouble(Simulator.f.format((max_volt / current_ohm)).replace(",", "."));
		this.typeName = "Widerstand";
	}
	
	public Resistor(Location location, int width, int height) {
		super(location, width, height);
	}

	@Override
	public void tick() {
		if(lore.isEmpty()) {
			if(Simulator.isReady) {			
				lore.put("Imax="+max_ampere+"A",
						(Simulator.calculateStringWidth(ShowRoom.complist.font_default, "Imax="+max_ampere+"A")/2));
				
				lore.put("R="+current_ohm+"Ω Umax="+max_volt+"V",
						(Simulator.calculateStringWidth(ShowRoom.complist.font_default, "R="+current_ohm+"Ω Umax="+max_volt+"V")/2));
				
//				lore.put(typeName,
//						(Simulator.calculateStringWidth(ShowRoom.complist.font_default, typeName)/2));
				
			}
		}
	}

	@Override
	public void render(Graphics g) {
	}
	
	int i = 1;
	@Override
	public void render(Graphics g, int x, int y, int width, int height) {
		if(isForPickList) {
			if(((y+height) > 0) && y < Simulator.windowHeight) {				
				g.setFont(ShowRoom.complist.font_default);
				g.setColor(Color.BLACK);
				g.drawLine(x-10, y+(height/2), x+width+10, y+(height/2));
				g.setColor(fillcolor);
				g.fillRect(x, y, width, height);
				g.setColor(Color.WHITE);
				for(String s : lore.keySet()) {
					g.drawString(s, x+(width/2)-lore.get(s), y+(i*(-15)));
					i++;
				}
				i=1;
			}else i=1;
		}
	}

	@Override
	public void triggerPin(int pin_index, double volt, double ampere, double ohm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
