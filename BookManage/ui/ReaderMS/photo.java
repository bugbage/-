package ReaderMS;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.LineBorder;
public class photo extends JPanel {

	/**
	 * Create the panel.
	 */
	private Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\86185\\Desktop\\1.jpg.jpg");//获得图片
   // img.getScaledInstance(163, 263, Image.SCALE_SMOOTH);
	
	public photo() {
		setBorder(new LineBorder(new Color(0, 0, 0), 5));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		  SwingUtilities.updateComponentTreeUI(this);

	}
	public void setImage(Image img) {
		this.img=img;
	}
	public void paint(Graphics g) {
		g.drawImage(img,0,0,185,225,this);
		//g.drawImage(img,0,0,500,400,this);
		g.dispose();
	}

}
