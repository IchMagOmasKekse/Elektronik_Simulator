package me.xxfreakdevxx.de.game.showroom;

import java.awt.Graphics;

import me.xxfreakdevxx.de.game.electricity.CircuitBoard;

public abstract class Scene {
	
	/* Identifikation */
	public String name = "Unbenannte Szene";
	
	public abstract void render(Graphics g);
	public abstract void tick();
	public abstract void addCircuitBoard(CircuitBoard board);
	
}
