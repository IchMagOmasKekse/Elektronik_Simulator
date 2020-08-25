package me.xxfreakdevxx.de.game.objects.xcomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.xxfreakdevxx.de.game.MouseInput;
import me.xxfreakdevxx.de.game.MouseMotion;
import me.xxfreakdevxx.de.game.Simulator;

public abstract class XComponent {
	
	protected XComponent xcomp = null;
	protected int id = 0;
	public Font font = new Font("Arial", 0, 12);
	protected Color background_color = new Color(0.4f, 0.4f, 0.4f, 0.8f);
	protected Color border_color = Color.BLACK;
	protected Color font_color = Color.BLACK;
	protected Color font_color_disabled = Color.BLACK;
	protected Color highlight_color = Color.LIGHT_GRAY;
	protected int border_size = 1;
	public int xSize = 100;
	public int ySize = 30;
	public int x = 30;
	public int y = 30;
	protected boolean inCenter = false;
	public XAction action = null;
	protected String ghost_text = "";
	public String text = "";
	public BufferedImage texture = null;
	public Rectangle bounds = null;
	
	/* Properties */
	public boolean isHovering = false;
	protected boolean isSelected = false;
	public int max_text_length = 16;
	public boolean showTexture = true;
	
	public XComponent(int id, String ghost_text, String text, int x, int y, int sizex, int sizey) {
		this.id = id;
		this.xSize = sizex;
		this.ySize = sizey;
		this.x = x;
		this.y = y;
		this.ghost_text = ghost_text;
		this.text = text;
		bounds = new Rectangle(x, y, sizex, sizey);
	}
	public XComponent(int id, String ghost_text, String text, int sizex, int sizey, int x, int y, boolean inCenter) {
		this.id = id;
		this.xSize = sizex;
		this.ySize = sizey;
		if(inCenter) {
			this.x = (Simulator.windowWidth/2)+x-(sizex/2);
			this.y = (Simulator.windowHeight/2)+y-(sizey/2);
		}else {
			this.x = x;
			this.y = y;			
		}
		this.ghost_text = ghost_text;
		this.text = text;
		this.inCenter = inCenter;
	}
	
	public abstract void render(Graphics g);
	public abstract void update();
	public abstract void hovered();
	public abstract void clicked();
	
	public void setTexture(BufferedImage im) {
		this.texture = im;
	}
	public void setAction(XAction action) {
		this.action = action;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	
	public void checkForInteraction() {
		checkHover();
		checkClick();
	}
	
	public void checkHover() {
		if(bounds.intersects(MouseMotion.mouse_bounds)) {
			 xcomp.hovered();
			 isHovering = true;
		}else isHovering = false;
	}
	
	public void checkClick() {
		if(bounds.intersects(MouseInput.mouse_click_bounds)) {
			xcomp.clicked();
			isSelected = true;
			XManager.selectedComponent = this;
			MouseInput.x_clicked_con = 0;
			MouseInput.y_clicked_con = 0;
			MouseInput.x_clicked_uncon = 0;
			MouseInput.y_clicked_uncon = 0;
		}else isSelected = false;
	}
	
}
