package Controller;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Login.LoginInterface;
import MainFrame.MainFrame;
import model.*;
public class LoginMethod  implements ActionListener{
	private static  LoginInterface frame;
 public static void main(String args[]) {
	   frame =new LoginInterface();
		frame.setVisible(true);
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="登录") {
			 Connection con=MySQLConnection.MysqlConnection();//连接数据库
			String username=new String(frame.getUserID());
			String password=new String(frame.getUserPwd());
			try {
				if(password.equals("")||username.equals("")) {
					JOptionPane.showMessageDialog(null,"输入信息为空或不完整");
				}
				else {
				 String Info[]=getLoginIfno(username,password);
				 if(Info!=null) {
					 JOptionPane.showMessageDialog(null,"欢迎"+Info[2]);
					MainFrame Main=new MainFrame(Info[1],Info[2],Integer.parseInt(username));
					frame.dispose();
				   }
				
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else {
			frame.dispose();
		}
		    		
		}
	
	public static String[] getLoginIfno(String userID,String pwd) throws SQLException {
		String sql="select query_login(?,?)";
		String result[]=new String[4];
		 Connection con=MySQLConnection.MysqlConnection();
			PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				pst.setString(1,userID);
				pst.setString(2, pwd);
				ResultSet rs=pst.executeQuery();
				rs.next();
				String status=rs.getString(1);
				if(status.equals("密码正确")) {
					Statement query=con.createStatement();
				ResultSet rs2=query.executeQuery("call query_Login_Info");
				rs2.next();
				result[0]=rs2.getString(1);//读者类别
				result[1]=rs2.getString(2);	//管理员角色
				result[2]=rs2.getString(3);//读者姓名
				result[3]=rs2.getString(4);//读者状态
				query.close();
				con.close();
				if(result[3].equals("0")) {
					JOptionPane.showMessageDialog(null, "该用户已注销！");
					return  null;
				 }
				}
				else if(status.equals("密码错误")) {
					JOptionPane.showMessageDialog(null,"密码错误");
					frame.clearPassword();
					return null;
				}
				else {
					JOptionPane.showMessageDialog(null,"用户名不存在");
					frame.clearLoginInfo();	
					return null;
				}
		return result;
	}
	
	

	
}
