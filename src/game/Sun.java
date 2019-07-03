package game;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Sun{
    /*
        实现阳光 12.30
  实现阳光的下落 消失 和被击中
     */


    int x;
    int y;
    int endY;
    int timer = 0;
    int state;//1还在 2被清除
    
    

    public Sun(int x,int y) {
    	state=1;
    	this.y=y;
    	this.x=x;
    }
    
    public Sun() {
    	/*
    	 * 用于产生阳光
    	 */
    	
    	state=1;
    	y=-80;
    	x=80*new Random().nextInt(9);
		endY=100*new Random().nextInt(5);
    }
    
    
    public void paintComponent(Graphics g) {

        g.drawImage((new ImageIcon("Image/sun.png")).getImage(),35+x,81+y,null);
    }
    
    public void drop() {
    	if(y<endY)
		{
			y+=3;
		}
		else
		{
			timer++;
			if(timer>25)
			{
				state=2;
			}
		}
	
	}
    
    boolean ifclicked(int x, int y){
		if(new Rectangle(34+this.x,81+ this.y, 78, 78).contains(x, y))
		{
			SoundAndMusic a=new SoundAndMusic("music/sun.wav");
			a.playSound("music/sun.wav");
			return true;
		}
		return false;
	}
    
    
    

}
