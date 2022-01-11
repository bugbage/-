package ReaderMS;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import Controller.ReaderManageMethod;

import java.awt.Color;
import javax.swing.JTable;

public class text extends JPanel {
	public static JTable table;
	private static JScrollPane scrollPane;
	public static text s=new text();
	private  ReaderManageMethod listener=new  ReaderManageMethod();
	
	public static  text getText()
	{
		return s;
	}
	private  text() {
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 625, 459);
		add(scrollPane);
		
		
		JLabel lblNewLabel = new JLabel("查询结果");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 0, 100, 15);
		add(lblNewLabel);
		
		JLabel label_text_info = new JLabel("借阅信息");
		label_text_info.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_text_info.setBounds(10, 516, 100, 15);
		add(label_text_info);
		
		JButton button_requist = new JButton("办理借书证");
		button_requist .setFocusPainted(false);
		button_requist .setBorderPainted(false);
		button_requist.setForeground(new Color(255, 255, 255));
		button_requist.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_requist.setBackground(new Color(255, 153, 0));
		button_requist.addActionListener(listener);
	
		button_requist.setBounds(10, 547, 100, 23);
		add(button_requist);
		
		JButton button_ChangeCard = new JButton("变更借书证");
		 button_ChangeCard.setFocusPainted(false);
		 button_ChangeCard.setBorderPainted(false);
		button_ChangeCard.setForeground(new Color(255, 255, 255));
		button_ChangeCard.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_ChangeCard.setBackground(new Color(255, 153, 0));
		button_ChangeCard.setBounds(120, 547, 97, 23);
		add(button_ChangeCard);
		button_ChangeCard.addActionListener(listener);
		
		JButton button_loss = new JButton("挂失");
		 button_loss.setFocusPainted(false);
		 button_loss.setBorderPainted(false);
		button_loss.setForeground(new Color(255, 255, 255));
		button_loss.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_loss.setBackground(new Color(255, 153, 0));
		button_loss.setBounds(227, 547, 87, 23);
		add(button_loss);
		
		button_loss.addActionListener(listener);
		
		JButton button_release = new JButton("解除挂失");
		button_release.setFocusPainted(false);
		button_release.setBorderPainted(false);
		button_release.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_release.setForeground(new Color(255, 255, 255));
		button_release.setBackground(new Color(255, 153, 0));
		button_release.setBounds(324, 547, 97, 23);
		add(button_release);
		button_release.addActionListener(listener);
		
		JButton button_cancel = new JButton("注销");
		button_cancel.setFocusPainted(false);
		button_cancel.setBorderPainted(false);
		button_cancel.setForeground(new Color(255, 255, 255));
		button_cancel.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_cancel.setBackground(new Color(255, 153, 0));
		button_cancel.setBounds(431, 547, 97, 23);
		add(button_cancel);
		button_cancel.addActionListener(listener);
		
		JButton button_back = new JButton("退出");
		button_back.setFocusPainted(false);
		button_back.setBorderPainted(false);
		button_back.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_back.setForeground(new Color(255, 255, 255));
		button_back.setBackground(new Color(255, 153, 0));
		button_back.setBounds(538, 547, 97, 23);
		add(button_back);	
	}
	
	public static  void setTable(Object[][] info,Object []o) {
		table = new JTable(info,o);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
	}
}
