package me.xxfreakdevxx.de.game.electricity;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.MouseMotion;
import me.xxfreakdevxx.de.game.showroom.ShowRoom;

public abstract class EComp {
	/* EComp ist ein Elektrobauteil, das von anderen Klassen geerbt werden kann.
	 * Z.b. bei Widerständen, Kondensatoren, Transistoren, Taster, Schalter */
	protected Location location;
	public Rectangle body = null;
	public static Color copperColor = new Color(255, 153, 0);
	public int width=32;
	protected int height=32;
	public float velX = 0;
	public float velY = 0;
	protected BufferedImage texture;
	public HashMap<Integer, Pin> pins = new HashMap<Integer, Pin>();
	
	public String name = "Unbenanntes Bauteil";
	public boolean showPinNames = false;
	
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
		private Color color = copperColor;
		private Font font = new Font("Arial", 0, 10);
		
		/* Indentifikation und Funktion */
		private String name = "Unbekannter Pin";
		private int name_width = 0;
		private boolean hasConnection = false;
		private Pin couplePin = null;
		private EComp parent = null;
		
		/* Elektrizität */
		private double volt = 0.0d;
		private double ampere = 0.0d;
		private double ohm = 0.0d;
		
		public Pin(String name, int x, int y, int width, int height, EComp parent) {
			this.name = name;
			this.body = new Rectangle(x, y, width, height);
			this.parent = parent;
			name_width = Game.calculateStringWidth(font, name);
		}
		
		public void tick() {
			if(getBody().intersects(new Rectangle((int)(Game.camera.getX()+MouseMotion.mouse_x)-1+(ShowRoom.getMouseOffsetX()),
					                              (int)(Game.camera.getY()+MouseMotion.mouse_y)-1+(ShowRoom.getMouseOffsetY()),
					2,
					2)))
				setConnection(true,  null);
			else disconnect();
		}
		
		public void render(Graphics g) {
			if(hasConnection) {
				g.setColor(Color.RED);
				g.fillRect(body.x, body.y, body.width, body.height);
				g.setColor(Color.BLACK);
				g.drawRect(body.x, body.y, body.width, body.height);				
			}else {
				g.setColor(color);
				g.fillOval(body.x, body.y, body.width, body.height);
				g.setColor(Color.BLACK);
				g.drawOval(body.x, body.y, body.width, body.height);
				if(parent != null && parent.showPinNames) {					
					g.setFont(font);
					g.drawString(name, body.x-(name_width/2)+(Config.pin_size/2), body.y-5);
				}
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
			this.hasConnection = false;
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
		
		public Pin setColor(Color color) {
			this.color = color;
			return this;
		}
		
	}
	
}
