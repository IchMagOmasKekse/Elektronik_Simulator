package me.xxfreakdevxx.de.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TextureAtlas {
	
	private BufferedImage image;
	private HashMap<String, BufferedImage> textures;
	
	public TextureAtlas() {
		this.textures=new HashMap<String, BufferedImage>();
		reloadTextures();
	}
	
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void reloadTextures() {
		/* L�dt alle Texturen neu und f�gt sie zum Atlas(textures(hashmap)) hinzu */
		textures.clear();
//		textures.put("monster", loadImage("/monster.png"));
	}
	
	public HashMap<String, BufferedImage> getTextures() {
		return textures;
	}
	
}
