package me.xxfreakdevxx.de.game.electricity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

import me.xxfreakdevxx.de.game.Location;

public class CircuitBoard extends EComp{

	public HashMap<Integer, EComp> ecomps = new HashMap<Integer, EComp>();
	public int pins_row = 0;
	public int pins_column = 0;
	public Color background_color = new Color(21, 176, 0);
	
	
	public CircuitBoard(int width, int height) {
		super(new Location(50, 50), width, height);
		System.out.println("CircuitBoard wird geladen...");
		this.pins_row = width / (Config.pin_size + Config.space_between_pins);
		this.pins_column = height / (Config.pin_size + Config.space_between_pins);
		
		generatePins();
	}
	
	public void generatePins() {
		int index = 0;
		for(int r = 0; r != pins_row; r++) {
			for(int c = 0; c != pins_column; c++) {
				pins.put(index, new Pin("Pin "+index,
									Config.space_between_pins+(index*Config.pin_size),
									Config.space_between_pins+(c*Config.pin_size),
									Config.pin_size,
									Config.pin_size));
				index++;
			}
			index++;
		}
		System.out.println(index+" Pins erstellt");
	}

	@Override
	public void tick() {
		
		//Update Komponenten
		if(ecomps.isEmpty() == false) {			
			for(int i : ecomps.keySet()) {
				ecomps.get(i).tick();
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(background_color);
		g.fillRect(body.x, body.y, body.width, body.height);
		
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
	
}
