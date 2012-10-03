package com.gabeochoa.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.gabeochoa.Vector2D;

public class Entity {

	public int mass = 3;
	
	public Vector2D speed;
	public double x,y;
	public double vx,vy;
	public int radius;
	public int height = 500;
	public int width = 500;
	public Graphics g;
	public double bounceFactor = 3.5;
	public double slideFactor = 1.05;
	
	public double LOSS = 0.87;
	public Entity()
	{
		x = 40;
		y = 40;
		setRadius(20);
		vx = 1;
		vy = 1;
		
		speed = new Vector2D( vx, vy); 
		
	}
	
	public Entity(int x, int y, int vx, int vy)
	{
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	
	public Entity(double x, double y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	public void boundaryChecks() {
		  if (y >= height - getRadius()) {
		    y = height - getRadius();
		    vy = -vy/bounceFactor ;
		  }
		  if (y <= getRadius()) {
		    y = getRadius();
		    vy = -vy/1.5;
		  }
		  if (x >= width - getRadius()) {
		    x = width -getRadius();
		    vx = -vx/slideFactor ;
		  }
		  
		  if (y >= height - getRadius()) {
			  vx = vx/slideFactor;
		  }
		  
		  if (x <= 0) {
			  x = getRadius();
			  vx = -vx/bounceFactor;
		  }
		}

	public void update()
	{
		boundaryChecks();
		gravity();
		x += vx;
		y += vy;
	}
	
	private void gravity() {
		if(y < height - getRadius())
		{
			vy+= .05;
		}
		
		if(y >= height - getRadius())
		{
			vy-= 0.05;
		}
	}
	public Vector2D getVector()
	{
		speed = new Vector2D(vx,vy);
		return speed;
	}
	
	public void addVector(Vector2D v2d)
	{
		speed.add(v2d);
	}
	public void paint(Graphics gr)
	{
		g = gr;
		drawEntity();
	}
	public void drawEntity()
	{
		g.setColor(Color.black);
		g.fillOval((int) x,(int) y,getRadius()*2,getRadius()*2);
	}

	public int getRadius() {
		return radius;
	}
	public int getDiameter() {
		return radius*2;
	}

	public void setVY(double d) {
		vy = d;
	}
	public void setVX(double d) {
		vx = d;
	}
	
	public double getAngleOfMovement()
	{
		return Math.toDegrees(Math.atan2(-vy, vx));
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public double getCenterX() {
		return x + getRadius();
	}
	public double getCenterY() {
		return y + getRadius();
	}
	
	public void handleCollisions(Entity B){
	    double xDist, yDist;
	    Entity A = this;
	    
	            xDist = A.getCenterX() - B.getCenterX();
	            yDist = A.getCenterY() - B.getCenterY();
	            double distSquared = xDist*xDist + yDist*yDist;
	            //Check the squared distances instead of the the distances, same result, but avoids a square root.
	            if(distSquared <= (A.radius + B.radius)*(A.radius + B.radius)){
	                double xVelocity = B.vx - A.vx;
	                double yVelocity = B.vy - A.vy;
	                double dotProduct = xDist*xVelocity + yDist*yVelocity;
	                //Neat vector maths, used for checking if the objects moves towards one another.
	                if(dotProduct > 0){
	                    double collisionScale = dotProduct / distSquared;
	                    double xCollision = xDist * collisionScale;
	                    double yCollision = yDist * collisionScale;
	                    //The Collision vector is the speed difference projected on the Dist vector,
	                    //thus it is the component of the speed difference needed for the collision.
	                    double combinedMass = A.mass + B.mass;
	                    double collisionWeightA = 2 * B.mass / combinedMass;
	                    double collisionWeightB = 2 * A.mass / combinedMass;
	                    A.vx += LOSS * ( collisionWeightA * xCollision );
	                    A.vy += LOSS * ( collisionWeightA * yCollision );
	                    B.vx -= LOSS * ( collisionWeightB * xCollision );
	                    B.vy -= LOSS * ( collisionWeightB * yCollision );
	                }
	            }

	            
	}
	


}
