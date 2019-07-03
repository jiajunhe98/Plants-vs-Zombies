package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * 所有僵尸的父类
 * 
 * 12.30
 */
public class zombie {
	int a,b,_health,_page;
	int state;//1.move2.eat.3.die4.clear
	SoundAndMusic xxx;
	public SoundAndMusic a() {
		return xxx;
	}
	public zombie() {
		
	}
	public zombie(int x,int y) {
		this.b=y;
		this.a=x;
		move();

	}
	
	public boolean meetwithplant(Plants[][] plants){
		for (int h = 0; h < 6; h++)
		{
			for (int l = 0; l < 9; l++)
			{
				if(plants[h][l]!=null&&new Rectangle(a+34, b+81, 80, 100).intersects(plants[h][l].x+34, plants[h][l].y+81, 70,70))
				{
					return true;
				}
			}
		}
		return false;
	}
	
/*
 * 实现僵尸move
 */
	public void move() {
	}
	public void move_show(Graphics g){
	}
	public void move_anime() {	
	}
	public void move_action(Plants[][] plants) {
	}
	
	/*
	 * 实现僵尸eat
	 */
	public void eat() {
	}
	public void eat_anime() {
	}
	public void eat_show(Graphics g) {
	}
	public void eat_action(Plants[][] plants) {
	}
	
	/*
	 * 实现僵尸死亡die
	 */
	public void die() {
	}
	public void die_show(Graphics g) {
	}
	public void die_anime() {
	}
	
	
	
	/*
	 * 清理僵尸
	 */
	public void clear() {
	}
	
	
	/*
	 * -----------------------------------整体组织----------------------------------------
	 */
	public void show(Graphics g) {
	}
	public void action(Plants[][] a) {
	}
	
	public int getx() {
		return a;
	}
	public int gety() {
		return b;
	}
	public void sethealth(int a) {
		
	}
	public int getstate() {
		return state;
	}
	public void ice() {
		
	}

}
