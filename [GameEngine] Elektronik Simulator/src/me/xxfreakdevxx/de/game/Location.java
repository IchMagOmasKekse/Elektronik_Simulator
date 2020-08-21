package me.xxfreakdevxx.de.game;

public class Location {
	
	private double x, y;
	
	public Location(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x=x;
	}
	public void setY(double y) {
		this.y=y;
	}
	public void add(double x, double y) {
		this.x+=x;
		this.y+=y;
	}

	public int getIntX() {
		return (int)x;
	}
	public int getIntY() {
		return (int)y;
	}
	
}
