package game;
import java.awt.*;
import javax.swing.ImageIcon;

public class diefield {
	plantsvszombies i;
	public diefield(plantsvszombies i) {
		this.i=i;
	}
	void diefield(Graphics g) {
		Image tu = (new ImageIcon("Image/Background/die.png")).getImage();
		g.drawImage(tu, 0, 0, null);
	}
	public void mouseclick(int mx,int my) {
		if(new Rectangle(0,0,1000,1000).contains(mx, my)) {
			i.pvz_die=null;
			i.pvz_pre=new preparepanel(i);

		}

	}
}
