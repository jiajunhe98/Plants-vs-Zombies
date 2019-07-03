package game;
import java.awt.*;
import javax.swing.ImageIcon;

public class preparepanel {
	plantsvszombies i;
	SoundAndMusic BGM=new SoundAndMusic("music/gaming.wav");
	public preparepanel(plantsvszombies i) {
		this.i=i;
		BGM.StartPlay_BGM();
	}
	void preparepanel(Graphics g) {
		Image tu = (new ImageIcon("Image/start.png")).getImage();
		g.drawImage(tu, 0, 0, null);
	}
	public void mouseclick(int mx,int my) {
		if(new Rectangle(266,612,112,38).contains(mx, my)) {
			BGM.StopPlay_BGM();
			SoundAndMusic a=new SoundAndMusic("music/laugh.wav");
			a.playSound("music/laugh.wav");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			i.pvz_pre=null;
			i.pvz_game=new gamefield(i);
			i.pvz_game.newGame(10, 5);
		}//简单游戏
		
		if(new Rectangle(381,612,91,38).contains(mx, my)) {
			BGM.StopPlay_BGM();
			SoundAndMusic a=new SoundAndMusic("music/laugh.wav");
			a.playSound("music/laugh.wav");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			i.pvz_pre=null;
			i.pvz_game=new gamefield(i);
			i.pvz_game.newGame(20, 7);
		}//中等游戏
		
		if(new Rectangle(477,612,112,38).contains(mx, my)) {
			BGM.StopPlay_BGM();
			SoundAndMusic a=new SoundAndMusic("music/laugh.wav");
			a.playSound("music/laugh.wav");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			i.pvz_pre=null;
			i.pvz_game=new gamefield(i);
			i.pvz_game.newGame(30, 10);
		}//困难游戏
		if(new Rectangle(785,0,50,50).contains(mx, my)) {
			System.exit(0);
		}//关闭游戏
	}
}
