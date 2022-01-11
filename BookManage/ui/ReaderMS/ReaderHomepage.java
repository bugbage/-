package ReaderMS;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;
public class ReaderHomepage extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public  ReaderInfo readerInfo;
	 protected 	text texts;
	 public  chose  choses;
	public ReaderHomepage() {
		//super("ReaderMS");
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\IDE\\BookManage\\icon\\登录(1).png"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 760);
		contentPane = this;
		
	
		contentPane.setOpaque(false);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
	choses = chose.getChose();
		choses.setBounds(10, 10, 1086, 60);
		contentPane.add(choses);
		
		text texts = text.getText();
		texts.setBounds(10, 108, 669, 581);
		contentPane.add(texts);
		 

		
		 readerInfo = ReaderInfo.getReaderInfo();
		readerInfo.setBounds(689, 108, 407, 581);
		contentPane.add(readerInfo);
	
	}
}
