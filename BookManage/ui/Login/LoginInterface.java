package Login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.LoginMethod;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginInterface extends JFrame {

	private ImagePanel  contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnExit;
	private JButton btnLogin;
    private LoginMethod listener=new LoginMethod();

	public LoginInterface() {
		setTitle("登录系统");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\IDE\\BookManage\\icon\\登录(1).png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 315);
		 contentPane = new ImagePanel();
		 contentPane.setOpaque(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户编号");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setIcon(new ImageIcon("D:\\eclipse\\IDE\\BookManage\\icon\\用户(1).png"));
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		lblNewLabel.setBounds(51, 110, 113, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\eclipse\\IDE\\BookManage\\icon\\密码(1).png"));
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(51, 155, 113, 21);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("黑体", Font.PLAIN, 18));
		textField.setForeground(Color.BLACK);
		textField.setBounds(195, 110, 165, 21);
		contentPane.add(textField);
		textField.setOpaque(false);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("黑体", Font.ITALIC, 12));
		passwordField.setForeground(Color.BLACK);
		passwordField.setBounds(195, 155, 165, 21);
		contentPane.add(passwordField);
		passwordField.setOpaque(false);
		
		 btnLogin = new JButton("登录");
		btnLogin.setForeground(Color.RED);
		btnLogin.setFont(new Font("华文琥珀", Font.PLAIN, 12));
		btnLogin.setBackground(new Color(0, 51, 0));
		btnLogin.setBounds(92, 223, 97, 23);
		contentPane.add(btnLogin);
		btnLogin.setOpaque(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setBorderPainted(false);
		btnLogin.addActionListener(listener);
		
	   btnExit =new JButton("退出");
		btnExit.setForeground(Color.RED);
		btnExit.setFont(new Font("华文琥珀", Font.PLAIN, 12));
		btnExit.setBackground(new Color(255, 153, 0));
		btnExit.setBounds(263, 223, 97, 23);
		btnExit.setFocusPainted(false);
		btnExit.setBorderPainted(false);
		contentPane.add(btnExit);
		btnExit.setOpaque(false);
		btnExit.addActionListener(listener);
	}
	public void clearPassword() {
		passwordField.setText("");
	}
	public void clearLoginInfo() {
		textField.setText("");
		passwordField.setText("");
	}
	public String  getUserID() {
		return textField.getText();
	}
	public char[] getUserPwd() {
		return passwordField.getPassword();
	}
	    static class ImagePanel extends JPanel {
		 private Image img = Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\IDE\\BookManage\\icon\\背景.jpg");//获得图片
	   public void paintComponent(Graphics g)
	 { 
	         super.paintComponents(g);
			g.drawImage(img,0,0,450, 315,this);
	  }
	 } 
}
