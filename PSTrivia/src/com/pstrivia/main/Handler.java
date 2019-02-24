package com.pstrivia.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.pstrivia.main.Game.STATE;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public static int spd = 1;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				object.clear();
				if(Game.gameState != STATE.End) {
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
				}
			}
		}
	}
}
