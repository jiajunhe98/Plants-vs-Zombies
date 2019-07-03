package game;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/*
 * 实现僵尸的行走（move）和进食（eat）和死亡（die）。
 * 分别使用x,x_show,x_anime,x_action来刻画x动作的状态值，加载x的图片，循环播放图片来实现动画效果和实现整个动作的发生
 * 12.30
 */
public class redzombie extends zombie {
	SoundAndMusic a=new SoundAndMusic("music/eat.wav");
	int x,y,health,page;
	int state;//1.move2.eat.3.die4.clear
	public redzombie(int x,int y) {
		this.y=y;this.x=x;
		move();
		health=100;
	}
	public SoundAndMusic a() {
		return a;
	}
	
	public boolean meetwithplant(Plants[][] plants){
		for (int h = 0; h < 6; h++)
		{
			for (int l = 0; l < 9; l++)
			{
				if(plants[h][l]!=null&&new Rectangle(x+34, y+81, 80, 100).intersects(plants[h][l].x-10, plants[h][l].y+81, 70,70))
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
		state=1;
	}
	public void move_show(Graphics g)
	{
		g.drawImage((new ImageIcon("Image/redzombie/Frame"+page+".png")).getImage(), x, y, null);
	}
	public void move_anime() {
		if(page==17)page=0;
		else {
			page++;
		}
	}
	public void move_action(Plants[][] plants) {
		x-=3;
		if(meetwithplant(plants)) {
			eat();
		}
		if (health<=0) {
			die();

		}
	}
	
	/*
	 * 实现僵尸eat
	 */
	public void eat() {
		state=2;
		page=0;
	}
	public void eat_anime() {
		if(page==17){page=0;a.playSound("music/eat.wav");}
		else {
			page++;
		}
	}
	public void eat_show(Graphics g) {
		g.drawImage((new ImageIcon("Image/redzombieeat/Frame"+page+".png")).getImage(), x, y, null);

	}
	public void eat_action(Plants[][] plants) {
		for (int h = 0; h < 6; h++)
		{
			for (int l = 0; l < 9; l++)
			{
				if(plants[h][l]!=null&&new Rectangle(x+34, y+81, 80, 100).intersects(plants[h][l].x-10, plants[h][l].y+81, 70,70))
				{
					plants[h][l].health--;
				}
			}
		}
		if(! meetwithplant(plants)) {

			move();
		}
		if(health<=0) {
			die();

		}
		
	}
	
	/*
	 * 实现僵尸死亡die
	 */
	public void die() {
		state=3;
		page=0;

	}
	public void die_show(Graphics g) {

		g.drawImage((new ImageIcon("Image/redzombiedie/Frame"+page+".png")).getImage(), x, y, null);
		g.drawImage((new ImageIcon("Image/redzombiehead/Frame"+page+".png")).getImage(), x, y, null);

	}
	public void die_anime() {
		if(page==11) {
			clear();
		}
		else {
			page++;
		}
	}
	
	
	
	/*
	 * 清理僵尸
	 */
	public void clear() {
		state=4;
	}
	//===========================ice===============================
	int ice=0,iice=0,iiice=0,iiiice=0;
	public void imove() {
		state=10;

	}
	public void imove_show(Graphics g)
	{
		g.drawImage((new ImageIcon("Image/redzombie/Frame"+page+".png")).getImage(), x, y, null);
	}
	public void imove_anime() {
		if(page==17)page=0;
		else {
			if (ice==0) {
			page++;ice++;ice%=2;}
			else { ice++;ice%=2;}
		}
	}
	public void imove_action(Plants[][] plants) {
		if(iice==0) {
		x-=3;iice++;iice%=2;}else {iice++;iice%=2;}
		if(meetwithplant(plants)) {
			ieat();
		}
		if (health<=0) {
			die();
		}
	}
	
	/*
	 * 实现僵尸eat
	 */
	public void ieat() {
		state=20;
		page=0;

		
	}
	public void ieat_anime() {
		if(page==17) {page=0;a.playSound("music/eat.wav");}
		else {
			if(iiice==0) {
				
			iiice++;iiice%=2;
			page++;}else {iiice++;iiice%=2;}
		}
	}
	public void ieat_show(Graphics g) {
		g.drawImage((new ImageIcon("Image/redzombieeat/Frame"+page+".png")).getImage(), x, y, null);


	}
	public void ieat_action(Plants[][] plants) {
		for (int h = 0; h < 6; h++)
		{
			for (int l = 0; l < 9; l++)
			{
				if(plants[h][l]!=null&&new Rectangle(x+34, y+81, 80, 100).intersects(plants[h][l].x-10, plants[h][l].y+81, 70,70))
				{if(iiiice==0) {
					plants[h][l].health--;iiiice++;iiiice%=2;
				}else {
					iiiice++;iiiice%=2;
				}
			}
		}
		if(! meetwithplant(plants)) {

			imove();
		}
		if(health<=0) {
			die();

		}
		
	}
	}
	/////////////////////////////////////////////////////
	
	/*
	 * -----------------------------------整体组织----------------------------------------
	 */
	public void show(Graphics g) {
		if(state==1) move_show(g);
		if(state==2) eat_show(g);
		if(state==3) die_show(g);
		if(state==10) imove_show(g);
		if(state==20)ieat_show(g);
	}
	public void action(Plants[][] a) {
		if(state==1) {
			move_anime();
			move_action(a);
		}
		if(state==2) {
			eat_anime();
			eat_action(a);
		}
		if(state==3) {
			die_anime();
		}
		if(state==10) {
			imove_anime();
			imove_action(a);
		}
		if(state==20) {
			ieat_anime();
			ieat_action(a);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public void sethealth(int a) {
		health-=a;
	}
	public int getstate() {
		return state;
	}
	public void ice() {
		if(state==1) state=10;
		if(state==2) state=20;
	}

}
