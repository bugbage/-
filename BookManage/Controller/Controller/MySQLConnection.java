package Controller;

import java.sql.*;

import javax.swing.*;

import model.readerType;

public interface MySQLConnection {
	String user="root";
	String password="lihui104715";
	String uri="jdbc:mysql://localhost:3306/bookmanage? useSSL=true";
	String jdbc_diver = "com.mysql.cj.jdbc.Driver";
	public static Connection MysqlConnection() {
		  Connection  con = null;
		//加载JDBC
		try {
			Class.forName(jdbc_diver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//连接数据库
		try {
			con=DriverManager.getConnection(uri, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;	
	}
	public static String[]  query(String username) {
		String sql="select rdpwd,tb_readeType.rdType,";
		return null;
	}
		public static Statement createQuery()
		{    Statement query = null;
			Connection con=MysqlConnection();
			try {
				query=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return query;		
		}
	
}
