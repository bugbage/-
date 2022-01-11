package BorrowManage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.*;

import Controller.BorrowManageMethod;
import Controller.MySQLConnection;
import Controller.ReaderManageMethod;
import ReaderMS.text;
import model.reader;
import java.awt.Toolkit;

public class BorrowManage extends JPanel {

	private JPanel contentPane;
	private JTextField txtRdID;
	private JTextField txtRdName;
	private JTextField txtCanLendQty;
	private JTextField txtDept;
	private JTextField txtLendDay;
	private JTextField txtType;
	private JTextField txtLendQty;
	public static JTable table;
	private JTextField txtBookName;
	public static JTable table_book;
	private static 	JScrollPane scrollPane;
	private static JScrollPane scrollPane_book;
	private static	JLabel txtName;
    private BorrowManageMethod listener=new BorrowManageMethod();
	public static BorrowManage s=new BorrowManage();
	public static BorrowManage getInstance() {
		return s;
	}
	private BorrowManage() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\IDE\\BookManage\\icon\\登录(1).png"));
		//setBackground(new Color(255, 255, 255));
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 792);
		//contentPane = new JPanel();
		contentPane=this;
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 1086, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("读者信息");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel.setBounds(0, 0, 58, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("读者编号");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 25, 58, 15);
		panel.add(lblNewLabel_1);
		
		txtRdID = new JTextField();
		txtRdID.setBounds(121, 22, 99, 21);
		panel.add(txtRdID);
		txtRdID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("读者姓名");
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(394, 10, 58, 15);
		panel.add(lblNewLabel_2);
		
		JLabel txtRdCanLendQty = new JLabel("可借阅数量");
		txtRdCanLendQty.setFont(new Font("楷体", Font.PLAIN, 14));
		txtRdCanLendQty.setBounds(394, 43, 73, 15);
		panel.add(txtRdCanLendQty);
		
		txtRdName = new JTextField();
		txtRdName.setBounds(477, 7,  113, 21);
		panel.add(txtRdName);
		txtRdName.setColumns(10);
		
		txtCanLendQty = new JTextField();
		txtCanLendQty.setBounds(477, 37,  113, 21);
		panel.add(txtCanLendQty);
		txtCanLendQty.setColumns(10);
		
		JLabel txtRdDept = new JLabel("读者单位");
		txtRdDept.setFont(new Font("楷体", Font.PLAIN, 14));
		txtRdDept.setBounds(627, 10, 58, 15);
		panel.add(txtRdDept);
		
		JLabel txtRdCanLendDay = new JLabel("可借阅天数");
		txtRdCanLendDay.setFont(new Font("楷体", Font.PLAIN, 14));
		txtRdCanLendDay.setBounds(627, 43, 73, 15);
		panel.add(txtRdCanLendDay);
		
		txtDept = new JTextField();
		txtDept.setBounds(710, 4, 113, 21);
		panel.add(txtDept);
		txtDept.setColumns(10);
		
		txtLendDay = new JTextField();
		txtLendDay.setBounds(710, 37, 113, 21);
		panel.add(txtLendDay);
		txtLendDay.setColumns(10);
		
		JLabel txtRdType = new JLabel("读者类别");
		txtRdType.setFont(new Font("楷体", Font.PLAIN, 14));
		txtRdType.setBounds(872, 10, 58, 15);
		panel.add(txtRdType);
		
		JLabel txtRdLendQty = new JLabel("已借阅数量");
		txtRdLendQty.setFont(new Font("楷体", Font.PLAIN, 14));
		txtRdLendQty.setBounds(872, 43, 78, 15);
		panel.add(txtRdLendQty);
		
		txtType = new JTextField();
		txtType.setBounds(960, 7,  113, 21);
		panel.add(txtType);
		txtType.setColumns(10);
		
		txtLendQty = new JTextField();
		txtLendQty.setBounds(960, 40,  113, 21);
		panel.add(txtLendQty);
		txtLendQty.setColumns(10);
		
		JButton btnRdSerach = new JButton("查找");
		btnRdSerach.setForeground(Color.WHITE);
		btnRdSerach.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnRdSerach.setFocusPainted(false);
		btnRdSerach.setBorderPainted(false);
		btnRdSerach.setBackground(new Color(255, 153, 0));
		btnRdSerach.setBounds(267, 21, 97, 23);
		panel.add(btnRdSerach);
		btnRdSerach.addActionListener(listener);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 95, 1086, 255);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1066, 235);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 372, 1086, 312);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("图书信息");
		lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 0, 58, 15);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("书名");
		lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(26, 42, 58, 15);
		panel_2.add(lblNewLabel_5);
		
		txtBookName = new JTextField();
		txtBookName.setBounds(121, 39, 97, 21);
		panel_2.add(txtBookName);
		txtBookName.setColumns(10);
		
		 scrollPane_book = new JScrollPane();
		scrollPane_book.setBounds(10, 67, 1066, 235);
		panel_2.add(scrollPane_book);
		
		table_book = new JTable();
		scrollPane_book.setViewportView(table_book);
		
		JButton btnBookSearch = new JButton("查找 ");
		btnBookSearch.setForeground(Color.WHITE);
		btnBookSearch.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnBookSearch.setFocusPainted(false);
		btnBookSearch.setBorderPainted(false);
		btnBookSearch.setBackground(new Color(255, 153, 0));
		btnBookSearch.setBounds(267, 38, 97, 23);
		panel_2.add(btnBookSearch);
		btnBookSearch.addActionListener(listener);
		
		JLabel lblNewLabel_4 = new JLabel("已借图书");
		lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(20, 77, 58, 15);
		contentPane.add(lblNewLabel_4);
		
		JButton btnBorrow = new JButton("借书");
		btnBorrow.setForeground(Color.WHITE);
		btnBorrow.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnBorrow.setFocusPainted(false);
		btnBorrow.setBorderPainted(false);
		btnBorrow.setBackground(new Color(255, 153, 0));
		btnBorrow.setBounds(203, 722, 97, 23);
		contentPane.add(btnBorrow);
		btnBorrow.addActionListener(listener);
		
		
		JButton btnReturn = new JButton("还书");
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnReturn.setFocusPainted(false);
		btnReturn.setBorderPainted(false);
		btnReturn.setBackground(new Color(255, 153, 0));
		btnReturn.setBounds(503, 722, 97, 23);
		contentPane.add(btnReturn);
		btnReturn.addActionListener(listener);
		
		JButton btnContinue = new JButton("续借");
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnContinue.setFocusPainted(false);
		btnContinue.setBorderPainted(false);
		btnContinue.setBackground(new Color(255, 153, 0));
		btnContinue.setBounds(803, 722, 97, 23);
		contentPane.add(btnContinue);
		btnContinue.addActionListener(listener);
		
	 txtName = new JLabel("借阅管理员");
		txtName.setFont(new Font("楷体", Font.PLAIN, 14));
		txtName.setBounds(20, 722, 117, 40);
		contentPane.add(txtName);
	}
	
	public int  getReaderID() {
		int result;
		try {
			result=Integer.parseInt(txtRdID.getText());
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "请检查输入的读者编号","警告",JOptionPane.WARNING_MESSAGE);
		     return  0;
		}
		return result;
		
	}
	public String getBookName() {
		String result;
		try {
			result=txtBookName.getText();		
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "请检查输入的读者编号","警告",JOptionPane.WARNING_MESSAGE);
			return null;
		}
		return result;
	}
	
	public String setReaderInfo(reader model) {
		String message="用户不存在";
		if(model!=null) {
		txtRdName.setText(model.getName());
		txtCanLendQty.setText(String.valueOf(model.getLendQty()));
		txtDept.setText(model.getDept());
		String[] result= getCanLendDay( model);
		txtCanLendQty.setText(result[0]);
		txtLendDay.setText(result[1]);
		txtType.setText(result[2]);
		txtLendQty.setText(String.valueOf(model.getLendQty()));
	    message="显示";
		}
		return message;	
	}
	public static String [] getCanLendDay(reader model)  {
		String result[]=new String[3];
	 String sql="call getReaderTypeInfo(?)";
	 Connection con=MySQLConnection.MysqlConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, model.getType());
			ResultSet rs=pst.executeQuery();
			rs.next();
			result[0]=rs.getString(1);//可借阅数量
			result[1]=rs.getString(2);//可借阅时间
			result[2]=rs.getString(3);//读者类型
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return result;
	}
	public static  void setBorrowTable(Object[][] info,Object []o) {
		table = new JTable(info,o);
		scrollPane.setViewportView(table);
	}
	public static  void setBookTable(Object[][] info,Object []o) {
		table_book= new JTable(info,o);
		scrollPane_book.setViewportView(table_book);
	}
  public  static void  setAdminName(String  name) {
	  txtName.setText("管理员："+name);
  }
}
