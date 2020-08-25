package me.xxfreakdevxx.de.game.electricity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import me.xxfreakdevxx.de.game.Simulator;
import me.xxfreakdevxx.de.game.Location;

public class CircuitBoard extends EComp{

	public HashMap<Integer, EComp> ecomps = new HashMap<Integer, EComp>();
	public int pins_row = 0;
	public int pins_column = 0;
	public Color background_color = new Color(21, 176, 0);
	public boolean showCopperSide = false;
	
	
	public CircuitBoard(int pins_row, int pin_column) {
		super(new Location((Simulator.windowWidth/2-(calcWidth(pins_row)/2)), (Simulator.windowHeight/2-(calcHeight(pin_column)/2))), calcWidth(pins_row), calcHeight(pin_column));
		System.out.println("CircuitBoard wird geladen...");
		this.pins_row = width / (Config.pin_size + Config.space_between_pins);
		this.pins_column = height / (Config.pin_size + Config.space_between_pins);
		
		name = "Platine "+width+"px  X "+height+"px";
		
		generatePins();
	}
	
	public static int calcWidth(int pins_row) {
		return (pins_row * Config.pin_size) + ((pins_row + 1) * Config.space_between_pins);
	}
	public static int calcHeight(int pins_column) {
		return (pins_column * Config.pin_size) + ((pins_column + 1) * Config.space_between_pins);
	}
	
	public void generatePins() {
		int index = 0;
		for(int r = 0; r != pins_row; r++) {
			for(int c = 0; c != pins_column; c++) {
				pins.put(index, new Pin("P"+index,
									getLocation().getIntX()+(r*Config.space_between_pins+(r*Config.pin_size))+Config.space_between_pins,
									getLocation().getIntY()+(c*Config.space_between_pins+(c*Config.pin_size))+Config.space_between_pins,
									Config.pin_size,
									Config.pin_size,
									this).setColor(Color.GRAY));
				index++;
			}
			index++;
		}
		System.out.println(index+" Pins erstellt");
	}

	@Override
	public void tick() {
		
		for(int i : pins.keySet()) {
			pins.get(i).tick();
		}
		
		//Update Komponenten
		if(ecomps.isEmpty() == false) {			
			for(int i : ecomps.keySet()) {
				ecomps.get(i).tick();
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		if(showCopperSide) {
			g.setColor(copperColor);
			g.fillRect(body.x, body.y, body.width, body.height);
		}else {			
			g.setColor(background_color);
			g.fillRect(body.x, body.y, body.width, body.height);
		}
		g.setColor(Color.GREEN);
		g.drawRect(body.x, body.y, body.width, body.height);
		
		g.setColor(Color.white);
		g.drawString(name, getBody().x-10, getBody().y-5);
		
		for(int i : pins.keySet()) {
			pins.get(i).render(g);
		}
		
		//Render Komponenten
		if(ecomps.isEmpty() == false) {			
			for(int i : ecomps.keySet()) {
				ecomps.get(i).render(g);
			}
		}
	}

	@Override
	public void triggerPin(int pin_index, double volt, double ampere, double ohm) {
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void mousePressed(MouseEvent e) {
		for(int i : pins.keySet()) {
			if(pins.get(i).getBody().intersects(new Rectangle(e.getPoint()))) {
				pins.get(i).setConnection(true, null);
			}
		}
	}

	@Override
	public void render(Graphics g, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
}
