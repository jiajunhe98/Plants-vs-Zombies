package game;
import java.awt.*;
import java.util.*;


public class gamefield {

	plantsvszombies maingame;
	public gamefield(plantsvszombies maingame) {
		this.maingame=maingame;
	}
	
	ArrayList<Sun> slList; //sunlight todo
	ArrayList<zombie>   zbList;   //zblist todo
	ArrayList<bullet>   blList;   //todo
	ArrayList<ice_bullet>   ibList;   //todo
	ArrayList<card>     cdList;   
	
	
	
	background background;
	Plants[][] plants=new Plants[6][9];
	Plants     peashooter;
	Plants     sunflower;
	Plants     nut;
	Plants     ice_peashooter;
	
	int sunlight_value=50;
	
	
	int zombie_die_number;
	int raw_zombie_number;
	int zombies_number;
	int zombie_random_number;//用于调整游戏难度 easy：15个僵尸，random 20；中等：20个僵尸，random 50 困难 30个僵尸 random 100
	
	SoundAndMusic BGM;


	
	
	void newGame(int zombie_number,int zombie_random_number) {
		this.zombies_number=zombie_number;
		this.zombie_random_number=zombie_random_number;
		raw_zombie_number=zombie_number;
		zombie_die_number=0;
		slList=new ArrayList<Sun>();
		zbList=new ArrayList<zombie>();
		blList=new ArrayList<bullet>();
		cdList=new ArrayList<card>();
		ibList=new ArrayList<ice_bullet>();
		
		
		cdList.add(new card(0,"sunflower"));
		cdList.add(new card(1,"peashooter"));
		cdList.add(new card(2,"nut"));
		cdList.add(new card(3,"iceshooter"));
		
		background=new background();
		
		peashooter=new Plants();
		sunflower=new Plants();
		nut=new Plants();
		ice_peashooter=new Plants();
		
		plants=new Plants[6][9];
		
		sunlight_value=50;
		BGM=new SoundAndMusic("music/BGM.wav");
		BGM.StartPlay_BGM();
	}

/*
 * 以下实现鼠标点击效果，
 */
	void mouseclick(int x,int y) {
		//对card实现鼠标点击效果
		for(int a=0;a<cdList.size();a++) {
			//==========================nut====================
			if(cdList.get(a).cardname.equals("nut")&&cdList.get(a).canbuyornot==1) {
				int ah = (y-81)/100;
				int al = (x-34)/80;

				Plants newnut= new Plants(2,al*80,ah*100);
				if(plants[ah][al] ==null) {
				plants[ah][al] = newnut;
				sunlight_value-=50;
				cdList.get(a).canbuyornot=0;
				nut.clear();
				}
			}
			if(cdList.get(a).cardname.equals("nut")&&cdList.get(a).if_pressed(x,y)&&sunlight_value>=50) {
				cdList.get(a).canbuyornot++;
				cdList.get(a).canbuyornot%=2;
				if(nut==null)nut=new Plants(2,x,y,3);
				else nut.nut_put();
			}
			//===================iceshooter===========================================
			if(cdList.get(a).cardname.equals("iceshooter")&&cdList.get(a).canbuyornot==1) {
				int ah = (y-81)/100;
				int al = (x-34)/80;

				Plants newice= new Plants(3,al*80,ah*100);
				if(plants[ah][al] ==null) {
				plants[ah][al] = newice;
				sunlight_value-=150;
				cdList.get(a).canbuyornot=0;
				ice_peashooter.clear();
				}
			}
			if(cdList.get(a).cardname.equals("iceshooter")&&cdList.get(a).if_pressed(x,y)&&sunlight_value>=150) {
				cdList.get(a).canbuyornot++;
				cdList.get(a).canbuyornot%=2;
				if(ice_peashooter==null) ice_peashooter=new Plants(3,x,y,3);
				else ice_peashooter.ice_put();
			}
			
			//=================sunflower=======================================
			if(cdList.get(a).cardname.equals("sunflower")&&cdList.get(a).canbuyornot==1) {
				int ah = (y-81)/100;
				int al = (x-34)/80;

				Plants newsun= new Plants(1,al*80,ah*100);
				if(plants[ah][al] ==null) {
				plants[ah][al] = newsun;
				sunlight_value-=25;
				cdList.get(a).canbuyornot=0;
				sunflower.clear();
				}
			}
			if(cdList.get(a).cardname.equals("sunflower")&&cdList.get(a).if_pressed(x,y)&&sunlight_value>=25) {
				cdList.get(a).canbuyornot++;
				cdList.get(a).canbuyornot%=2;
				if(sunflower==null) sunflower=new Plants(1,x,y,3);
				else sunflower.sun_put();
			}
			
			//=======================peashooter=========================
			if(cdList.get(a).cardname.equals("peashooter")&&cdList.get(a).canbuyornot==1) {
				int ah = (y-81)/100;
				int al = (x-34)/80;

				Plants newpes= new Plants(0,al*80,ah*100);
				if(plants[ah][al] ==null) {
				plants[ah][al] = newpes;
				sunlight_value-=100;
				cdList.get(a).canbuyornot=0;
				peashooter.clear();
				}
			}
			if(cdList.get(a).cardname.equals("peashooter")&&cdList.get(a).if_pressed(x,y)&&sunlight_value>=100) {
				cdList.get(a).canbuyornot++;
				cdList.get(a).canbuyornot%=2;
				if(sunflower==null) peashooter=new Plants(0,x,y,3);
				else peashooter.shooter_put();
			}
		}
//========================================sunlight======================================
			for(  int g= 0 ; g< slList.size()  ; g++   )
			{
				
				if(slList.get(g).ifclicked(x, y)){
					slList.remove(g);
					sunlight_value+=25;
				}

				

			}

		
			
			if(new Rectangle(785,0,50,50).contains(x, y)) {
				System.exit(0);
			}//关闭游戏
			
			
			if(new Rectangle(710,0,50,50).contains(x, y)) {
				maingame.runstate=1;
				
			}//暂停
			
			if(new Rectangle(625,0,50,50).contains(x, y)) {
				maingame.runstate=0;
				
			}//启动
		
	}
//==================================以上 鼠标点击效果=========================================================
	public void mouse_move(int  mx,int my){
			if(peashooter!=null&&peashooter.state==3)
			{
				peashooter.x=mx;
				peashooter.y=my;
			}
			if(ice_peashooter!=null&&ice_peashooter.state==3)
			{
				ice_peashooter.x=mx;
				ice_peashooter.y=my;
			}
			if(nut!=null&&nut.state==3)
			{
				nut.x=mx;
				nut.y=my;
			}
			if(sunflower!=null&&sunflower.state==3)
			{
				sunflower.x=mx;
				sunflower.y=my;
			}
	}
	//=======================虚影拖拽效果===========================================================
	
	public  void gamepanel(Graphics g) {
		background.runbackground(g);  //Background

		
		//===============以下为卡片=============================
	
			if (sunlight_value>=25) {
			cdList.get(0).showinshop_canbuy(g);
			}else {
				cdList.get(0).showinshop_cannotbuy(g);}
			
			if (sunlight_value>=100) {
			cdList.get(1).showinshop_canbuy(g);
			}else {
				cdList.get(1).showinshop_cannotbuy(g);}
			
			if (sunlight_value>=50) {
			cdList.get(2).showinshop_canbuy(g);
			}else {
				cdList.get(2).showinshop_cannotbuy(g);}
			
			if (sunlight_value>=150) {
			cdList.get(3).showinshop_canbuy(g);
			}else {
				cdList.get(3).showinshop_cannotbuy(g);}

			//=====================以下为植物==========================================
			
			for (int h = 0; h < 6; h++)
			{
				for (int l = 0; l < 9; l++)
				{
					if(plants[h][l]!=null)
					{
						plants[h][l].show(g);
					}
				}
			}
			
			//=================拖拽的影子===============
		if(peashooter!=null)peashooter.shopter_put_show(g);;
		if(sunflower!=null)sunflower.sun_put_show(g);
		if(nut!=null)nut.nut_put_show(g);
		if(ice_peashooter!=null)ice_peashooter.ice_put_show(g);
		//=================zombie==============================
		for(  int a= 0 ; a< zbList.size() ; a++   )//对于? ,从?循环到? 
		{
		     zbList.get(a).show(g);
		}
		//============bullet&icebullet=====================================
		
		for(  int a= 0 ; a< blList.size()  ; a++   )
		{
			blList.get(a).image(g);
		}
		for(  int a= 0 ; a< ibList.size()  ; a++   )
		{
			ibList.get(a).image(g);
		}
		
		//================sunlight====================================
		for( int ge =0 ; ge <=slList.size()-1 ; ge=ge+1 )
		{
			slList.get(ge).paintComponent(g);
		}
		g.setFont(new Font("宋体", 0, 15));
		g.drawString(""+sunlight_value, 40, 80);
	}
	
	public void timer() throws Exception {
		if(new Random().nextInt(2400)<zombie_random_number&&zombies_number>0) {
			int  x = 800;
			int  y = 100*new Random().nextInt(6);
			zombie newzombie=new normalzombie(x,y);
			zbList.add(newzombie);
			zombies_number--;
		}
		if(new Random().nextInt(2400)<zombie_random_number&&zombies_number>0) {
			int  x = 800;
			int  y = 100*new Random().nextInt(6);
			zombie newzombie=new redzombie(x,y);
			zbList.add(newzombie);
			zombies_number--;
		}
		if(new Random().nextInt(2400)<zombie_random_number&&zombies_number>0) {
			int  x = 800;
			int  y = 100*new Random().nextInt(6);
			zombie newzombie=new yellowzombie(x,y);
			zbList.add(newzombie);
			zombies_number--;
		}
		if(new Random().nextInt(2400)<zombie_random_number&&zombies_number>0) {
			int  x = 800;
			int  y = 100*new Random().nextInt(6);
			zombie newzombie=new darkzombie(x,y);
			zbList.add(newzombie);
			zombies_number--;
		}
		
		for(  int x= 0 ; x<= blList.size()-1  ; x++   )
		{
			blList.get(x).Action(zbList);
		}
		for(  int x= 0 ; x<= ibList.size()-1  ; x++   )
		{
			ibList.get(x).Action(zbList);
		}
		
		for (int h = 0; h < 6; h++)
		{
			for (int l = 0; l < 9; l++)
			{
				if(plants[h][l]!=null)
				{
	 
					plants[h][l].action(blList, ibList, slList);;
					if(plants[h][l].state==2)

					plants[h][l]=null;
				}
			}
		}
	
		for(  int x= 0 ; x<= zbList.size()-1  ; x++   )
		{
			zbList.get(x).action(plants);
			if(zbList.get(x).getstate()==4)
			{
				zombie_die_number++;
				zbList.remove(x);

			}

		}

		for(  int x= 0 ; x<= zbList.size()-1  ; x++   )
		{

			if(zbList.get(x).getx()<-200) {


  
                   BGM.StopPlay_BGM();
				   Thread.sleep(200);

					SoundAndMusic a=new SoundAndMusic("music/laugh.wav");
					a.playSound("music/laugh.wav");

                   maingame.pvz_game=null;

                   maingame.pvz_die=new diefield(maingame);
                   
			}
		}
		if (zombie_die_number==raw_zombie_number) {
            BGM.StopPlay_BGM();

			SoundAndMusic a=new SoundAndMusic("music/winner.wav");
			a.playSound("music/winner.wav");
			   Thread.sleep(1000);

            maingame.pvz_game=null;
            maingame.pvz_win=new winfield(maingame);
		}
		
		if(new Random().nextInt(600)<7)
		{
			Sun sl = new Sun();
			slList.add(sl);
		}
		for( int ge =0 ; ge <=slList.size()-1 ; ge=ge+1 )
		{
			slList.get(ge).drop();

			if(slList.get(ge).state==2)
			{
				slList.remove(ge);
			}
		}
		
	}
	

}
