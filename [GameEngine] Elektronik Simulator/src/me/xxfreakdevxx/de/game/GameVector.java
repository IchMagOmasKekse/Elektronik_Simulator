package me.xxfreakdevxx.de.game;

import java.awt.Point;
import java.util.Optional;

public class GameVector {
	
	double speed=2;
	double x=0;
	double y=0;
	double velX=0;
	double velY=0;
	
	boolean mxNegative = false;
	boolean myNegative = false;
	
	boolean isNormalized = false;
	
	public GameVector(double x, double y) {
		this.x=x;
		this.y=y;
//		if(x < 0) {mxNegative = true; System.out.println("x < 0 (x="+x+")"); x = x * (-1);}
//		if(y < 0) {myNegative = true; System.out.println("y < 0 (y="+y+")"); y = y * (-1);}
	}
	
	public double getXVelocity() {
		normalize();
		return velX;
	}
	public double getYVelocity() {
		normalize();
		return velY;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public void normalize() {
		if(isNormalized == false) {				
			double l /* Länge */ = Math.sqrt( ( x * x ) + ( y * y ) );
			velX = (x / l) * speed;
			velY = (y / l) * speed;
			
//			System.out.println("l = "+l);
//			System.out.println("velX = "+velX);
//			System.out.println("velY = "+velY);
			
//		if(mxNegative) velX = velX * (-1);
//		if(myNegative) velY = velY * (-1);
//		
//		System.out.println("velX2 = "+velX);
//		System.out.println("velY2 = "+velY);
			isNormalized = true;
//			calculateIntersectionPoint(1, 1, 1, 1).get().
		}
	}
	
	public Optional<Point> calculateIntersectionPoint(
		    double m1, 
		    double b1, 
		    double m2, 
		    double b2) {
		 
		    if (m1 == m2) {
		        return Optional.empty();
		    }
		 
		    double x = (b2 - b1) / (m1 - m2);
		    double y = m1 * x + b1;
		 
		    Point point = new Point();
		    point.setLocation(x, y);
		    return Optional.of(point);
		}
	
}