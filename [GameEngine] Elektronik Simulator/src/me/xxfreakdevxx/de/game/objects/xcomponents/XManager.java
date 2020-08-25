package me.xxfreakdevxx.de.game.objects.xcomponents;

import java.awt.Graphics;
import java.util.HashMap;

import me.xxfreakdevxx.de.game.Simulator;

public class XManager {
	
	public static HashMap<Integer, XComponent> comps = new HashMap<Integer, XComponent>();
	public static XComponent selectedComponent = null;
	
	public XManager() {
	}
	
	public static void renderComps(Graphics g) {
		for(int id : comps.keySet()) {
			comps.get(id).render(g);
		}
	}
	
	public static void tick() {
		if(Simulator.isReady) {			
			for(int id : comps.keySet()) {
				comps.get(id).update();
			}
		}
	}
	
	public static boolean addComponent(XComponent comp) {
		if(comps.containsKey(comp.id)) return false;
		else comps.put(comp.id, comp);
		return true;
	}
	
	public static boolean removeComponent(XComponent comp) {
		if(comps.containsKey(comp.id) == false) return false;
		else comps.put(comp.id, comp);
		return true;
	}
	
	
	
}
