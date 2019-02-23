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
	private Random r = new Random();
	public int gameMode;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
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
			g.drawString("Wave", 240, 70);
			
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
			Font fnt4 = new Font("arial", 1, 15);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 250, 70);
			
			g.setFont(fnt3);
			g.drawString("Use direction or WSAD keys to move player and dodge enemies" , 17, 150);
			g.drawString("Use P key to pause the game and ESC key to go back", 70, 200);
			g.drawString("Made by Kevin Chan -2018" , 190, 250);
			
			g.setColor(Color.white);
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 273, 393);
		}
	}
	
}
