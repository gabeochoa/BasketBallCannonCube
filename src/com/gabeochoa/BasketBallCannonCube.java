package com.gabeochoa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.gabeochoa.objects.Ball;
import com.gabeochoa.objects.Entity;
import com.gabeochoa.objects.Player;

public class BasketBallCannonCube extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	Graphics g;
	Image Buffer;


	private Player player;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private Dimension size;


	private boolean running;
	private Thread thread;
	

    public InputHandler inputHandler;
    public boolean keys[];
    public int mouseButton, mouseX,mouseY;


	private long startTime;
	private long timePassed;


	private int ln;
	
	public BasketBallCannonCube()
	{
		//CREATING WINDOW WITH CERTAIN DIMENSIONS
    	size = new Dimension(WIDTH, HEIGHT);
    	setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BasketBallCannonCube");
      
        //CREATING THE IMAGE AND BUFFER TO DRAW ON
        Buffer = createImage(size.width,size.height);
        g = Buffer.getGraphics();
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

        //INPUT BASED ALLOCATIONS
        inputHandler = new InputHandler(this);
        addKeyListener(inputHandler);
        addFocusListener(inputHandler);
        addMouseListener(inputHandler);
        addMouseMotionListener(inputHandler);
		
		player= new Player();	
		entities.add(player);
		
		Ball b = new Ball();
		entities.add(b);
	}
	
	 public void run()
	    {
	    	try{Thread.sleep(20);}
	    	catch(Exception e){e.printStackTrace();}

	       //setCursor(cutScene)? defaultCursor:emptyCursor);

	        int frames = 0;

			double unprocessedSeconds = 0;
			long lastTime = System.nanoTime();
			double secondsPerTick = 1 / 60.0;
			int tickCount = 0;


			while (true) {
				long now = System.nanoTime();
				long passedTime = now - lastTime;
				lastTime = now;
				if (passedTime < 0) passedTime = 0;
				if (passedTime > 100000000) passedTime = 100000000;

				unprocessedSeconds += passedTime / 1000000000.0;

				boolean ticked = false;
				handleKeysAndMouse(inputHandler.keys, inputHandler.mouse);
				
				while (unprocessedSeconds > secondsPerTick) {
					
					tick();
					unprocessedSeconds -= secondsPerTick;
					ticked = true;

					tickCount++;
					if (tickCount % 60 == 0) {
						System.out.println(frames + " fps");
						lastTime += 1000;
						frames = 0;
					}
				}

				if (ticked) {
					repaint();
					frames++;
				} else {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
	    }
	 
	 public void tick()
	 {
		 g.setColor(Color.gray);
		 g.fillRect(0, 0, WIDTH, HEIGHT);

		 g.setColor(Color.black);
		 g.drawLine(00, 500, WIDTH, 500);
		 g.drawLine(500, 0, 500, HEIGHT);
			
		
		player.getInput(mouseX, mouseY);
		
		checkCollision();
		
		ln = entities.size();
		if(ln > 50)
			ln = 50;
		
		for(int i=0; i<ln; i++){
			Entity e = entities.get(i);
			e.update();
			e.paint(g);
		}
		
		
	}
	
	 
	 private void checkCollision() {
		Entity h1, h2;
		 for (int i = 0; i < entities.size(); i++)  
		 {  
			 h1 = entities.get(i);
			 for (int j = i + 1; j < entities.size(); j++)  
		     {  
		    	 h2 = entities.get(j);
		    	 
		         h1.handleCollisions(h2);
		     }
		 }
	}

	public void addEntity(double time)
	 {
		if(entities.size() > 50)
		 entities.remove(1);
			
		 Ball b = new Ball(getSpawnX(), getSpawnY());
		 b.setVX(time * 4  + 1);
		 System.out.println(""+time);
		 player.setVX(time*-b.vx);
		 
		 entities.add(b);
	 }
	 
	 private double getSpawnX() {
		 double dy =  (mouseY - player.y);
		 double dx =  (mouseX - player.x);
		 
		 double angle = Math.atan(Math.toRadians(dy/dx));
		 
		 return player.getCenterX() + ( 20 * Math.cos(angle));
	} 
	 private double getSpawnY() {
		 double dy =  (mouseY - player.y);
		 double dx =  (mouseX - player.x);
		 
		 double angle = Math.atan(Math.toRadians(dy/dx));
		 		 
		 return  player.getCenterY() +( 20 * Math.sin(angle));
	}

	 
	
	private void handleKeysAndMouse(boolean[] k, int[] m) {

	    	keys = k;	  	
	        mouseButton = m[0];
	        mouseX = m[1];
	        mouseY = m[2];
		}
 
	 
	public void paint(Graphics g)
	{
		g.drawImage(Buffer, 0, 0, this);
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args)
	{
		BasketBallCannonCube BBCC = new BasketBallCannonCube();
		while(true)
			BBCC.run();
	}
	
}
