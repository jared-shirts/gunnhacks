package com.pstrivia.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.pstrivia.main.Game.STATE;


public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private Random r = new Random();
	public int gameMode;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(Game.gameState == STATE.Menu) {
			//help button
			if(mouseOver(mx,my,210,250,200,64)) {
				Game.gameState = STATE.Help;	
			}
			
			//quit button
			if(mouseOver(mx,my,210,350,200,64)){
				System.exit(1);
			}
			//play button
			if(mouseOver(mx,my,210,150,200,64)) {
				Game.gameState = STATE.Intro;
			}
		}
		//back button for end
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mx,my,210,350,200,64)) {
				Game.gameState = STATE.Menu;
				
				return;
			}
		}
		//clicks for intro screen
		if(Game.gameState == STATE.Intro) {
			if(mouseOver(mx,my,300,350,200,100)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-60,ID.Player, handler));
				handler.clearEnemies();
			}
		}
		
		if(Game.gameState == STATE.Death) {
			if(mouseOver(mx,my,300,350,200,100)) {
				Game.gameState = STATE.Game;
			}
		}
		if(Game.gameState == STATE.End) {
			if(mouseOver(mx,my,300,350,200,100)) {
				Game.gameState = STATE.Menu;

			}
		}
	}
	
	public void mouseRelease(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("PSTrivia", 210, 80);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 275, 193);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 275, 293);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 275, 393);
		}else if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 250, 70);
			
			g.setFont(fnt3);
			g.drawString("Use direction keys to move to the doors" , 70, 180);
			g.drawString("Use P key to pause the game and ESC key to go back", 50, 230);
			g.drawString("Made by PSTrivia Co." , 190, 280);
			
			g.setColor(Color.white);
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 273, 393);
		}else if(Game.gameState == STATE.Intro) {
			Font fnt = new Font("arial",1,50);
			g.setColor(Color.black);
			g.fillRect(0, 0, game.WIDTH, game.HEIGHT);
			
			g.setColor(Color.white);
			g.drawString("You spawn in a city. And you need to follow public safety.", 300, 250);
			g.drawString("Click here to continue.", 400, 400);
		}else if(Game.gameState == STATE.Death) {
			Font fnt = new Font("arial",1,50);
			g.setColor(Color.black);
			g.fillRect(0, 0, game.WIDTH, game.HEIGHT);
			
			g.setColor(Color.white);
			g.drawString("You died!", 300, 250);
			g.drawString("Lives left: " + Game.life, 400, 325);
			g.drawString("Click here to continue.", 400, 400);
		}else if(Game.gameState == STATE.End) {
			Font fnt = new Font("arial",1,50);
			g.setColor(Color.black);
			g.fillRect(0, 0, game.WIDTH, game.HEIGHT);
			
			g.setColor(Color.white);
			g.setColor(Color.white);
			g.drawString("Game Over!", 500, 250);
			g.drawString("You died at level: " + Game.level, 400, 325);
			g.drawString("Click here to continue.", 400, 400);
		}
	}
	
}
