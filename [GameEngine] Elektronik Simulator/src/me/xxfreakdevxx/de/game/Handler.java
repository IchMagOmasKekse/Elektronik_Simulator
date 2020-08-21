package me.xxfreakdevxx.de.game;
import java.awt.Graphics;
import java.util.LinkedList;

import me.xxfreakdevxx.de.game.electricity.EComp;
import me.xxfreakdevxx.de.game.showroom.ShowRoom;

public class Handler {
	/* Lagert alle GameObject in einer Liste */
	public LinkedList<EComp> object = new LinkedList<EComp>();
	
	private boolean up = false, down = false, right = false, left = false;
	
	
	public void tick() {
		ShowRoom.tick();
	}
	
	public void render(Graphics g) {
		ShowRoom.render(g);
	}
	
	public void addObject(EComp tempObject) {
		object.add(tempObject);
	}
	public void removeObject(EComp tempObject) {
		object.remove(tempObject);
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	
}
