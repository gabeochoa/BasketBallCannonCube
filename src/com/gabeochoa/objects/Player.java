package com.gabeochoa.objects;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity{

	Graphics g;
	private int mouseY;
	private int mouseX;
	
	public int bounceFactor = 8;
	public int slideFactor = 8;
	public int mass = 28;
	
	public Player()
	{
		super();
	}
	
	public Player(int x, int y, int vx, int vy)
	{
		super(x, y, vx, vy);
	}
	public void getInput(int mx, int my)
	{
		mouseX = mx;
		mouseY = my;
	}
	public void update()
	{
		super.update();
	}

	

	public void paint(Graphics gr)
	{
		g = gr;
		drawPlayer();
	}
	
	public void drawPlayer()
	{
		g.setColor(Color.cyan);
		g.fillRect((int) x,(int) y,getDiameter(),getDiameter());
	}
}
