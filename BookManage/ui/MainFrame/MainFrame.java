package MainFrame;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.*;
import java.sql.SQLException;
import model.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import BookManage.BookManage;
import BorrowManage.BorrowManage;
import Controller.BorrowManageMethod;
import ReaderMS.ReaderHomepage;
import ReaderMS.chose;
import ReaderMS.readerView;
import readerManage.ReaderTypeManage;
public class MainFrame  {
	private static String adminRoles="管理员";
	public static  String Name="人员";
	private static ReaderTypeManage readerType=ReaderTypeManage.getInstance();
	private static BorrowManage borrow= BorrowManage.getInstance();
	private static BookManage book=BookManage.getInstance();
	private  readerView s;
public static ReaderHomepage reader=new ReaderHomepage();
  public MainFrame(String admin,String name,int id){
	this.adminRoles=admin;
	this.Name=name;
	borrow.setAdminName(Name);
	
	switch(adminRoles) {
	case "8":
		try {
			this.setSystemAdminView(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
    case "4":
    	try {
			setBookManageView(id);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		break;
    case "1":
    	try {
			setReaderManageView(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	break;
    case "0":
    	try {
			setReaderView(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	break;
	}
	
	
}

public void setSystemAdminView(int id) throws SQLException {
    
	JFrame jf = new JFrame("图书管理系统");
    jf.setSize(1120, 820);
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jf.setLocationRelativeTo(null);
    final JTabbedPane tabbedPane = new JTabbedPane();
  
    reader.choses.updateReaderType();
    reader.readerInfo.updateType();
    tabbedPane.addTab("借阅证管理",reader);
    
    tabbedPane.addTab("读者类型管理",readerType);

    tabbedPane.addTab("借阅管理", borrow);
    tabbedPane.addTab("图书管理",book);
    reader model=BorrowManageMethod.queryReaderInfo(id);
    s= new readerView();
    s.setView(model);
    tabbedPane.addTab("个人信息",s );
    tabbedPane.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
        	 reader model=BorrowManageMethod.queryReaderInfo(id);
        	    try {
					s.setView(model);//刷新借阅信息
					reader.choses.updateReaderType();//读者管理栏读者类型栏更新
					reader.readerInfo.updateType();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        }
    });

    // 设置默认选中的选项卡
    tabbedPane.setSelectedIndex(0);
    jf.setContentPane(tabbedPane);
    jf.setVisible(true);
}


public void setBookManageView(int id) throws SQLException {
	    
	JFrame jf = new JFrame("图书管理系统");
    jf.setSize(1120, 820);
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jf.setLocationRelativeTo(null);
    final JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("图书管理",book);

    reader model=BorrowManageMethod.queryReaderInfo(id);
    readerView s= new readerView();
    s.setView(model);
    tabbedPane.addTab("个人信息",s );
    tabbedPane.setSelectedIndex(0);
    jf.setContentPane(tabbedPane);
    jf.setVisible(true);
    tabbedPane.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
        	 reader model=BorrowManageMethod.queryReaderInfo(id);
        	    try {
					s.setView(model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        }
    });
	}
public void setReaderManageView(int id) throws SQLException {
    
	JFrame jf = new JFrame("借阅证管理系统");
    jf.setSize(1120, 820);
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jf.setLocationRelativeTo(null);
    final JTabbedPane tabbedPane = new JTabbedPane();
    reader.choses.updateReaderType();
    reader.readerInfo.updateType();
    tabbedPane.addTab("借阅证管理",new ReaderHomepage());

    reader model=BorrowManageMethod.queryReaderInfo(id);
    s= new readerView();
    s.setView(model);
    tabbedPane.addTab("个人信息",s );
    tabbedPane.setSelectedIndex(0);
    jf.setContentPane(tabbedPane);
    jf.setVisible(true);
    tabbedPane.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
        	 reader model=BorrowManageMethod.queryReaderInfo(id);
        	    try {
					s.setView(model);
				    reader.choses.updateReaderType();
				    reader.readerInfo.updateType();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        }
    });
}
public void setReaderView(int id) throws SQLException {
	JFrame jf = new JFrame("借阅信息查询系统");
    jf.setSize(1120, 820);
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jf.setLocationRelativeTo(null);
    reader model=BorrowManageMethod.queryReaderInfo(id);
   readerView s= new readerView();
   s.setView(model);
    jf.setContentPane(s);
    jf.setVisible(true);    
}
}
      
	
	
