package me.xxfreakdevxx.de.game.electricity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import me.xxfreakdevxx.de.game.Location;

public abstract class EComp {
	/* EComp ist ein Elektrobauteil, das von anderen Klassen geerbt werden kann.
	 * Z.b. bei Widerständen, Kondensatoren, Transistoren, Taster, Schalter */
	protected Location location;
	public Rectangle body = null;
	public int width=32;
	protected int height=32;
	public float velX = 0;
	public float velY = 0;
	protected BufferedImage texture;
	public HashMap<Integer, Pin> pins = new HashMap<Integer, Pin>();
	
	public EComp(Location location, int width, int height) {
		this.location=location;
		this.width=width;
		this.height=height;
		this.body = new Rectangle(location.getIntX(), location.getIntY(), width, height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void triggerPin(int pin_index, double volt, double ampere, double ohm);
	public abstract Rectangle getBounds();


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public Rectangle getBody() {
		return body;
	}
	
	public static class Pin {
		
		/* Eigenschaften */
		private Rectangle body = null;
		
		/* Indentifikation und Funktion */
		private String name = "Unbekannter Pin";
		private boolean hasConnection = false;
		private Pin couplePin = null;
		
		/* Elektrizität */
		private double volt = 0.0d;
		private double ampere = 0.0d;
		private double ohm = 0.0d;
		
		public Pin(String name, int x, int y, int width, int height) {
			this.name = name;
			this.body = new Rectangle(x, y, width, height);
		}
		
		public void render(Graphics g) {
			if(hasConnection) {
				g.setColor(Color.ORANGE);
				g.fillRect(body.x, body.y, body.width, body.height);
				g.setColor(Color.BLACK);
				g.drawRect(body.x, body.y, body.width, body.height);				
			}else {
				g.setColor(Color.ORANGE);
				g.fillOval(body.x, body.y, body.width, body.height);
				g.setColor(Color.BLACK);
				g.drawOval(body.x, body.y, body.width, body.height);
			}
		}

		public boolean hasConnection() {
			return hasConnection;
		}

		public void setConnection(boolean hasConnection, Pin couplePin) {
			this.hasConnection = hasConnection;
			if(hasConnection == false) {
				this.couplePin = null;
				this.ohm = 999999999d;
			}else {
				this.couplePin = couplePin;
				this.ohm = 0d;
			}
		}
		
		public void disconnect() {
			this.couplePin = null;
			this.ohm = 999999999d;
		}

		public double getVolt() {
			return volt;
		}

		public void setVolt(double volt) {
			this.volt = volt;
		}

		public double getAmpere() {
			return ampere;
		}

		public void setAmpere(double ampere) {
			this.ampere = ampere;
		}

		public double getOhm() {
			return ohm;
		}

		public void setOhm(double ohm) {
			this.ohm = ohm;
		}
		
		public Rectangle getBody() {
			return body;
		}
		
		public Pin getCouplePin() {
			return couplePin;
		}
		
		public void setCouplePin(Pin couplePin) {
			this.couplePin = couplePin;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}
