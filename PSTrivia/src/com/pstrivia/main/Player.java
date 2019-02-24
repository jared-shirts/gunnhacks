package com.pstrivia.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Player extends GameObject{

	Random r = new Random();
	Handler handler;
	int num = 0;
	
	private BufferedImage player_image;
	//private Image[] left = new Image[3];
	//private Image[] right = new Image[3];
	private Image back;
	public Player(int x, int y, ID id, Handler handler){
		super(x, y, id);
		this.handler = handler;
		//left[0] = new ImageIcon("res/left/run1.jpg").getImage();
		//left[1] = new ImageIcon("res/left/run2.jpg").getImage();
		//left[2] = new ImageIcon("res/left/run3.jpg").getImage();
		//right[0] = new ImageIcon("res/right/run1.jpg").getImage();
		//right[1] = new ImageIcon("res/right/run2.jpg").getImage();
		//right[2] = new ImageIcon("res/right/run3.jpg").getImage();
		back = new ImageIcon("res/back.png").getImage();
		
	}
	public Rectangle getBound() {
		return new Rectangle((int)x,(int)y,32,32);
	}

	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp(x, 0, Game.WIDTH-100);
		y = Game.clamp(y, 0, Game.HEIGHT-54);
	}

	public void render(Graphics g) {
		if((int)x < 50 && (int)x > 0) {
			g.setColor(Color.white);
			Font fnt1 = new Font("arial", 1, 20);
			g.setFont(fnt1);
			g.drawString(Game.answer[0], 50, 100);
			Game.place = 0;
		}
		if((int)x < 400 && (int)x > 350) {
			g.setColor(Color.white);
			Font fnt1 = new Font("arial", 1, 20);
			g.setFont(fnt1);
			g.drawString(Game.answer[1], 50, 100);
			Game.place = 1;
		}
		if((int)x < 700 && (int)x > 500) {
			g.setColor(Color.white);
			Font fnt1 = new Font("arial", 1, 20);
			g.setFont(fnt1);
			g.drawString(Game.answer[2], 50, 100);
			Game.place = 2;
		}
		if((int)x < 900 && (int)x > 800) {
			g.setColor(Color.white);
			Font fnt1 = new Font("arial", 1, 20);
			g.setFont(fnt1);
			g.drawString(Game.answer[3], 50, 100);
			Game.place = 3;
		}
		//0 is right and 1 is left
		//g.setColor(Color.white);
		//g.fillRect((int)x, (int)y, 32, 32);
		/*if(direction == 0) {
			for(int i = 0; i < 3; i++) {
				g.drawImage(right[i], (int)x, 310, null);
			}
		}else if(direction == 1) {
			for(int i = 0; i < 3; i++) {
				g.drawImage(left[i], (int)x, 310, null);
			}
		}else if(direction == 3) {*/
			g.drawImage(back, (int)x, 310, null);
		//}
	}

	


}
