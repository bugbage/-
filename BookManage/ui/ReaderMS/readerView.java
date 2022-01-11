package ReaderMS;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Controller.BorrowManageMethod;
import Controller.MySQLConnection;
import model.borrowInfo;
import model.reader;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class readerView extends JPanel {
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtSex;
	private JTextField txtDept;
	private JTextField txtPhone;
	private JTable table;
	private JPasswordField pwdOne;
	private JPasswordField pwdTwo;
	private JButton btnChange;
	private String address;
	private 	photo photo ;
	private	JScrollPane scrollPane;
	private reader model;
	public static Object[] o= {"借阅顺序号","读者序号","图书序号","续借次数","借书日期","应还日期","实际还书日期","超期天数","超期金额","罚款金额","是否已经还书","借书操作员","还书操作员"};

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public readerView()  {
		
		setBackground(new Color(153, 204, 255));
		setLayout(null);
		setBounds(100, 100, 1120, 760);
		
		JLabel lblNewLabel = new JLabel("借书证号");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel.setBounds(61, 46, 79, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("姓名\r\n");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(61, 113, 58, 15);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("电子邮箱");
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(301, 46, 71, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("性别");
		lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(314, 117, 58, 15);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("单位");
		lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(603, 46, 58, 15);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("电话号码");
		lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(603, 117, 96, 15);
		add(lblNewLabel_5);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(139, 46, 152, 21);
		add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(139, 111, 152, 21);
		add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setBounds(393, 46, 152, 21);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSex = new JTextField();
		txtSex.setEditable(false);
		txtSex.setBounds(393, 111, 152, 21);
		add(txtSex);
		txtSex.setColumns(10);
		
		txtDept = new JTextField();
		txtDept.setEditable(false);
		txtDept.setBounds(718, 46, 152, 21);
		add(txtDept);
		txtDept.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		txtPhone.setBounds(718, 111, 152, 21);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("个人信息");
		lblNewLabel_6.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(23, 10, 117, 15);
		add(lblNewLabel_6);
		
		 photo = new photo();
		photo.setBounds(914, 10, 185, 228);
		add(photo);
		
		JLabel lblNewLabel_7 = new JLabel("借阅记录");
		lblNewLabel_7.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(23, 319, 86, 15);
		add(lblNewLabel_7);
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 360, 1044, 357);
		add(panel);
		panel.setLayout(null);
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1024, 337);
		panel.add(scrollPane);
		
		
		
		
		
		JLabel lblNewLabel_8 = new JLabel("更改密码");
		lblNewLabel_8.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(45, 217, 74, 15);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("确认密码");
		lblNewLabel_9.setFont(new Font("楷体", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(45, 267, 74, 15);
		add(lblNewLabel_9);
		
		 btnChange = new JButton("确认更改");
		btnChange.setForeground(Color.WHITE);
		btnChange.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnChange.setFocusPainted(false);
		btnChange.setBorderPainted(false);
		btnChange.setBackground(new Color(255, 153, 0));
		btnChange.setBounds(314, 264, 97, 23);
		add(btnChange);
		btnChange.addActionListener(new ActionProcess());
		
		pwdOne = new JPasswordField();
		pwdOne.setBounds(139, 217, 134, 21);
		add(pwdOne);
		
		pwdTwo = new JPasswordField();
		pwdTwo.setBounds(139, 265, 134, 21);
		add(pwdTwo);
		
	}
public void setView(reader model) throws SQLException {
	this.model=model;
	txtID.setText(String.valueOf(model.getID()));
	txtName.setText(model.getName());
	txtPhone.setText(model.getPhone());
     txtEmail.setText(model.getEmaill());
     photo.setImage(model.getPhoto());
     txtSex.setText(model.getSex());
     txtDept.setText(model.getDept());
    borrowInfo array[]=BorrowManageMethod.getBorrowInfo(model);
    if(array==null) {
    	 Object info[][]=new Object[0][];
         table = new JTable(info,o);
    		scrollPane.setViewportView(table);
    	return ;
    }
     Object info[][]=BorrowManageMethod.toTable(array);
     table = new JTable(info,o);
		scrollPane.setViewportView(table);
}

	class ActionProcess implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				String One=String.valueOf(pwdOne.getPassword());
				String two=String.valueOf(pwdTwo.getPassword());
				if(One.equals("")||two.equals("")) {
					JOptionPane.showMessageDialog(null,"非法操作");
				}
				else {
					boolean status= ReaderInfo.inspectPwd(two);
					if(One.equals(two)&&status) {
						//更新到数据库
						String sql="call setPassword(?,?)";
						Connection con=MySQLConnection.MysqlConnection();
						PreparedStatement pst;
						try {
							pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							pst.setInt(1, model.getID());
							pst.setString(2,two);
							pst.execute();
							con.close();
							JOptionPane.showMessageDialog(null, "修改成功");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				}
				
			
		}
		
	}
}
