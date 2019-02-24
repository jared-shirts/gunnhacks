package com.pstrivia.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[2];
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
	//	this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events for player 1

				if(key == KeyEvent.VK_RIGHT) { 
					tempObject.setVelX(Handler.spd); keyDown[0] = true;
					}
				if(key == KeyEvent.VK_LEFT) { 
					tempObject.setVelX(-Handler.spd); keyDown[1] = true;}
				if(key == KeyEvent.VK_UP) {
					if(Game.place == Game.realAns) {
						Game.level++;
						AudioPlayer.getSound("door").play();
					}else if(Game.life > 1){
						Game.life --;
						Game.gameState = Game.STATE.Death;
						AudioPlayer.getSound("gun").play();
					}else {
						Game.life = 5;
						Game.gameState = Game.STATE.End;
						AudioPlayer.getMusic("music").pause();;
						AudioPlayer.getSound("gameover").play();
					}
				}
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events for player 1
				
				if(key == KeyEvent.VK_RIGHT) keyDown[0] = false; //tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT ) keyDown[1] = false; //tempObject.setVelX(0);
				
				//horizontal movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelX(0);
			}
		}
	}
	
}
