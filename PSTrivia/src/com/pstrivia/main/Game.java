package com.pstrivia.main;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = -6112428091888191314L;	
	public static final int WIDTH = 1000, HEIGHT = 514;
	private Menu menu;
	private Handler handler;
	public static int level = 1;
	public static int lev = 0;
	public enum STATE{
		Menu,
		Help,
		Intro,
		Death,
		End,
		Game
	}
	public static STATE gameState = STATE.Menu;
	public static int place;
	private Thread thread;
	public static int life = 5;
	private String question;
	public static int realAns;
	public static String[] answer = new String[4];
	BufferedReader in;
	private boolean running = false;
	Image background = Toolkit.getDefaultToolkit().createImage("res/background.jpg");
	public static Image level1 = Toolkit.getDefaultToolkit().createImage("res/level1.jpg");
	public static Image level2 = Toolkit.getDefaultToolkit().createImage("res/level2.jpg");
	public static Image level3 = Toolkit.getDefaultToolkit().createImage("res/level3.jpg");
	public Game() throws IOException{
		handler = new Handler();
		in = new BufferedReader(new InputStreamReader(new FileInputStream("res/question.txt")));
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		menu = new Menu(this,handler);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		new Window(WIDTH,HEIGHT,"PSTrivia",this);
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 100000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				try {
					render();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();	
	}
	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void tick() {
		if(gameState == STATE.Menu || gameState == STATE.Help) {
			menu.tick();
		}else if(gameState == STATE.Game) {
			handler.tick();
		}
	}
	public void render() throws IOException {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Intro || gameState == STATE.Death || gameState == STATE.End) {
			g.drawImage(background, 0, 0, null);
			menu.render(g);
		}else if(gameState == STATE.Game) {
			if(level%3 == 0)g.drawImage(Game.level3,0,0,null);
			else if(level%2 == 0)g.drawImage(Game.level2,0,0,null);
			else g.drawImage(Game.level1,0,0,null);
			
			handler.render(g);
			for (int i = 0; i < handler.object.size(); i++) {
		        GameObject tempObject = handler.object.get(i);
		        
		        if (tempObject.getId() == ID.Player){
		            tempObject.render(g);
		        }
			}
			if(lev < level) {
				question = in.readLine();
				for(int i = 0; i < 4; i++) {
					answer[i] = in.readLine();
					if(answer[i].substring(0,2).equals("69")) {
						realAns = i;
						answer[i] = answer[i].substring(3,answer[i].length());
					}
				}
				lev++;
			}
			Font fnt1 = new Font("arial", 1, 20);
			g.setFont(fnt1);
			g.setColor(Color.black);
			g.drawString(question, 50, 50);
		}
		g.dispose();
		bs.show();
	}
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else return var;
	}
	public static void main(String[] arg) throws IOException {
		new Game();
	}
	
}
