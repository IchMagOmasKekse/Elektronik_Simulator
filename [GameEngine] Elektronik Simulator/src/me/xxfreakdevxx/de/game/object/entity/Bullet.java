package me.xxfreakdevxx.de.game.object.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Handler;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.object.GameObject;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.entity.Bullet.GameVector;

public class Bullet extends GameObject {
	
	private Handler handler;
	private double damage = 3;
	private LivingEntity shooter;
	private static int speed = 20;
	private GameVector locationVector = null;
	private GameVector mouseVector = null;
	private GameVector moveVector = null;
	
	public Bullet(ID id, Location location, LivingEntity shooter, int width, int height, int mx, int my) {
		super(id, location, width, height);
		this.shooter = shooter;
		this.handler = Game.getInstance().getHandler();
		
		locationVector = new GameVector(location.getX(), location.getY());
		mouseVector = new GameVector(mx, my);
		mouseVector.normalize();
		System.out.println("MouseX/MouseY: "+mouseVector.getX()+"/"+mouseVector.getY());
		System.out.println("LocX/LocY: "+locationVector.getX()+"/"+locationVector.getY());
		moveVector = new GameVector(mouseVector.getX()-locationVector.getX(),
									mouseVector.getY()-locationVector.getY());
		
		System.out.println("MoveX/MoveY: "+moveVector.getXVelocity()+"/"+moveVector.getYVelocity());
		System.out.println(" ");
		
		//////////////////////////////////////////////////////
		
//		boolean mxNegative = false;
//		boolean myNegative = false;
//		
//		if(mx < 0) {mxNegative = true; mx = mx * (-1);}
//		if(my < 0) {myNegative = true; my = my * (-1);}
//		
//		double l = Math.sqrt( ( mx * mx ) + ( my * my ) ); //Länge
//		velX = (mx / l) * speed;
//		velY = (my / l) * speed;
		
//		if(mxNegative) velX = velX * (-1);
//		if(myNegative) velY = velY * (-1);
		
		//////////////////////////////////////////////////////
		
//		this.velX = (mx - getLocation().getIntX()) / speed;
//		this.velY = (my - getLocation().getIntY()) / speed;
//		if(this.velX > speed) velX = speed;
//		if(this.velY > speed) velY = speed;
	}

	@Override
	public void tick() {
		getLocation().add(moveVector.getXVelocity(), moveVector.getYVelocity());
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BLOCK) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}else if(tempObject.getId() == ID.ENEMY && (tempObject != shooter)) {
				if(getBounds().intersects(tempObject.getBounds())) {
					((LivingEntity)tempObject).damage(damage, shooter);
					handler.removeObject(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(getLocation().getIntX(), getLocation().getIntY(), width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getLocation().getIntX(), getLocation().getIntY(), width, height);
	}
	
	public static class GameVector {
		
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
//			if(x < 0) {mxNegative = true; System.out.println("x < 0 (x="+x+")"); x = x * (-1);}
//			if(y < 0) {myNegative = true; System.out.println("y < 0 (y="+y+")"); y = y * (-1);}
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
				
				System.out.println("l = "+l);
				System.out.println("velX = "+velX);
				System.out.println("velY = "+velY);
				
//			if(mxNegative) velX = velX * (-1);
//			if(myNegative) velY = velY * (-1);
//			
//			System.out.println("velX2 = "+velX);
//			System.out.println("velY2 = "+velY);
				isNormalized = true;
			}
		}
		
	}
}
