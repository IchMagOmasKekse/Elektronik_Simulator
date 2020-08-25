package me.xxfreakdevxx.de.game.showroom;

import java.awt.Graphics;

import me.xxfreakdevxx.de.game.electricity.CircuitBoard;

public class DebugScene extends Scene {
	
	public CircuitBoard board = null;
	
	public DebugScene() {
		System.out.println("DebugScene wird geladen...");
		preInit();
		init();
		postInit();
	}
	
	public void preInit() {
		
	}
	public void init() {
		
	}
	public void postInit() {
		addCircuitBoard(new CircuitBoard(10, 7));
	}
	
	@Override
	public void render(Graphics g) {
		if(board != null) board.render(g);
	}

	@Override
	public void tick() {
		if(board != null) board.tick();
	}

	@Override
	public void addCircuitBoard(CircuitBoard board) {
		this.board = board;
	}

	@Override
	public CircuitBoard getCircuitBoard() {
		return board;
	}

}
