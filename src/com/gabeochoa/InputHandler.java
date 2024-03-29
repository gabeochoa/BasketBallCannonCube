package com.gabeochoa;


import java.awt.event.*;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {
    public boolean[] keys = new boolean[65536];
    public int[] mouse = new int[5];
    private boolean elapsedTimeStarted = false;
	
    private long startTime;
	private double elapsedTime;
	
	public BasketBallCannonCube BBCC;
	
	public InputHandler(BasketBallCannonCube bbcc)
	{
		BBCC = bbcc;
	}
    public void mouseDragged(MouseEvent arg0) {
    	
    }

    public void mouseMoved(MouseEvent arg0) {
        mouse[1] = arg0.getX();
        mouse[2] = arg0.getY();
    }

    public void mouseClicked(MouseEvent arg0) { 
    }

    public void mouseEntered(MouseEvent arg0) {
        mouse[3] = 1;
    }

    public void mouseExited(MouseEvent arg0) {
         mouse[3] = 0;
    }

    public void mousePressed(MouseEvent arg0) {
        mouse[0] = arg0.getButton();
        mouse[1] = arg0.getX();
        mouse[2] = arg0.getY();
    }

    public void mouseReleased(MouseEvent arg0) {
        mouse[0] = 0;//arg0.getButton();
        mouse[1] = arg0.getX();
        mouse[2] = arg0.getY();
    }

    public void focusGained(FocusEvent arg0) {
    }

    public void focusLost(FocusEvent arg0) {
        for (int i=0; i<keys.length; i++) {
            keys[i] = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); 
        if (code>0 && code<keys.length) {
            keys[code] = true;
        }
        
        if(keys[KeyEvent.VK_SPACE]&& !elapsedTimeStarted)
        {
        	//System.out.println("Time started");
        	elapsedTimeStarted = true;
        	startTime = System.currentTimeMillis();
        }
        
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); 
        if (code>0 && code<keys.length) {
            keys[code] = false;
        }
        
        if(!keys[KeyEvent.VK_SPACE] && elapsedTimeStarted)
        	{
        	    //System.out.println("Time stopped");
        	    elapsedTimeStarted = false;
        	    //System.out.println(((System.currentTimeMillis() - startTime) +""));
        		elapsedTime =  (double) ((System.currentTimeMillis() - startTime) / 1000.0);
        		if(elapsedTime > 2)
        			elapsedTime =2;
        		BBCC.addEntity(elapsedTime);
        	}
 
    }

    public void keyTyped(KeyEvent arg0) {
    }
}