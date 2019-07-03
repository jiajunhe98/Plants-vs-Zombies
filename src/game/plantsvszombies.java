package game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class plantsvszombies {
	
	
	int runstate=0;//用于暂停整个游戏
	//主方法入口
	public static void main(String[] args) {
		new plantsvszombies();
	}
	
	gamefield pvz_game;
	preparepanel pvz_pre;
	
	diefield   pvz_die;
	winfield   pvz_win;
	
	
	void newGame() {
		pvz_pre=new preparepanel(this);
	}
	public void mouseclick(MouseEvent e) {
		if(pvz_game!=null) pvz_game.mouseclick(mx, my);
		if(pvz_pre!=null) pvz_pre.mouseclick(mx, my);
	    if(pvz_die!=null) pvz_die.mouseclick(mx, my);
		if(pvz_win!=null) pvz_win.mouseclick(mx, my);
	}
	
	void mouserelease(MouseEvent e) {}
	void mousemove(MouseEvent e)
	{
		if(pvz_game!=null)
		{
			pvz_game.mouse_move(mx, my);
		}
	}
	void mousedrag(MouseEvent e) {}
	void panel(Graphics g)
	{

		if(pvz_game!=null)
		{
			pvz_game.gamepanel(g);
		}
		if(pvz_pre!=null)
		{
			pvz_pre.preparepanel(g);
		}
		if(pvz_die!=null) {
			pvz_die.diefield(g);
		}
		if(pvz_win!=null) {
			pvz_win.winfield(g);
		}
	}
	void timer() throws Exception {
		if(pvz_game!=null)
		{
			pvz_game.timer();
		}
	}
	
	
	mainPanel panel=null;
	mainTimer timer=null;


	int mx,my;//鼠标横纵坐标
	plantsvszombies(){
		newGame();
		
		panel=new mainPanel();
		panel.setSize(860, 678+35);
		panel.setVisible(true);
		timer=new mainTimer(59);
		
	}
	
	class mainPanel extends JFrame{
		mainPanelListener  a= null;
		window b=null;
		mainPanel(){
			b=new window();
			this.add(b);
			a=new mainPanelListener();
			this.addWindowListener(a);
			this.repaint();
		}
	}
	class mainPanelListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
	
	
	class window extends JPanel
	{
		mouseListener						a					= null;

		window()
		{
			a = new mouseListener();
			this.addMouseListener(a);
			this.addMouseMotionListener(a);
		}

		class mouseListener extends MouseAdapter implements MouseMotionListener
		{
			public void mousePressed(MouseEvent e)
			{
				mx = e.getX();
				my = e.getY();
				mouseclick(e);
				panel.repaint();
			}

			public void mouseDragged(MouseEvent e)
			{
				mx = e.getX();
				my = e.getY();
				mousedrag(e);
				panel.repaint();
			}

			public void mouseMoved(MouseEvent e)
			{
				mx = e.getX();
				my = e.getY();
				mousemove(e);
				panel.repaint();
			}

			public void mouseReleased(MouseEvent e)
			{
				mx = e.getX();
				my = e.getY();
				mouserelease(e);
				panel.repaint();
			}

			public void mouseClicked(MouseEvent e)//鼠标单击
			{
			}
		}

		public void paint(Graphics g)
		{
			panel(g);

		}
	}

	class mainTimer implements Runnable
	{
		Thread	xc	= null;
		long	a;

		mainTimer(long jianGe)
		{
			this.a = jianGe;
			xc = new Thread(this);
			xc.start();
		}

		public void run()
		{
			while(true) {
				System.out.print("");
			while (runstate==0)
			{
				try
				{
					Thread.sleep(a);

					if (this == timer)
					{

						try {
							timer();
						} catch (Exception e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}

						panel.repaint();
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}}
	}
	

}
