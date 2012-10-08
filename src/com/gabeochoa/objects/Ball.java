package com.gabeochoa.objects;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Entity {

	public Ball()
	{
		super();
	}
	public Ball(double d, double e)
	{
		super(d, e, 0, 3);
		radius = 12;
	}

	public void paint(Graphics gr)
	{
		g = gr;
		drawBall();
	}
	public void drawBall()
	{
		g.setColor(Color.black);
		g.fillOval((int) x,(int) y,getDiameter(),getDiameter());
	}
	  
	
	

	
}
