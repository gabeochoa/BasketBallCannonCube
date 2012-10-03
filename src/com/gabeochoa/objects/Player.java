package com.gabeochoa.objects;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity{

	Graphics g;
	private boolean action;
	private int mouseY;
	private int mouseX;
	private int sp;
	private int spos;
	public int spy;
	
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
		handleInputX();
		handleInputY();
		
	}
	private void handleInputX() {
		if(mouseX > x+getRadius())
		{
			spawnPointX(1);
		}
		if(mouseX == x+getRadius())
		{
			spawnPointX(0);
		}
		if(mouseX < x+getRadius())
		{
			spawnPointX(-1);
		}
	}
	
	private void handleInputY() {
		if(mouseY > y+getRadius())
		{
			spawnPointY(1);
		}
		if(mouseY == y+getRadius())
		{
			spawnPointY(0);
		}
		if(mouseY < y+getRadius())
		{
			spawnPointY(-1);
		}
	}

private void spawnPointX(int i) {
		
		spos = i;
		
		switch(i){
		
		case 1:
			sp = (int) (x + getRadius() + 5);
			break;
		
		case 0:
			sp = (int) (x + (getRadius()/2));
			break;
		
		case -1:
			sp = (int) (x - 5);
			break;
		}
	}

	private void spawnPointY(int i) {
	
	switch(i){
	
	case 1:
		spy = (int) (y + getRadius() + 5);
		break;
	
	case 0:
		spy = (int) (y + (getRadius()/2));
		break;
	
	case -1:
		spy = (int) (y - 5);
		break;
	}
}

	public int getSpawn()
	{
		return sp;
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

	public int getSpawnPos() {
		return spos;
	}
}
