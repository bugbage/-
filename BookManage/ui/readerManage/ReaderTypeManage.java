package readerManage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BookManage.BookManage;
import Controller.BookManageMethod;
import Controller.ReaderTypeManageMethod;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

public class ReaderTypeManage extends JPanel {

	     private JPanel contentPane;
	      public static JTable table;
	      private static    JScrollPane scrollPane;
	      private static JLabel label_adminName;
      public static ReaderTypeManage s=new ReaderTypeManage();
       public static ReaderTypeManage getInstance() {
    	   return s;
       }
       private ReaderTypeManageMethod listener=new ReaderTypeManageMethod ();
        private ReaderTypeManage() {
		//super("读者类型管理");
		//setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\IDE\\BookManage\\icon\\登录(1).png"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 760);
		//contentPane = new JPanel();
		contentPane=this;
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		borrow borrow_info = borrow.getInstance();
		borrow_info.setBounds(10, 28, 389, 500);
		contentPane.add(borrow_info);
		
		 label_adminName = new JLabel("");
		label_adminName.setFont(new Font("楷体", Font.PLAIN, 14));
		add(label_adminName);
		
		JButton btnQuery = new JButton("查询");
		btnQuery.setBackground(new Color(255, 153, 0));
		btnQuery.setForeground(new Color(255, 255, 255));
		btnQuery.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnQuery.setBounds(143, 641, 97, 23);
		contentPane.add(btnQuery);
		btnQuery.addActionListener(listener);
		
		JButton btnAdd = new JButton("添加");
		btnAdd.setBackground(new Color(255, 153, 0));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnAdd.setBounds(383, 641, 97, 23);
		contentPane.add(btnAdd);
	    btnAdd.addActionListener(listener);
	
		
		
		JButton btnChange = new JButton("修改");
		btnChange.setBackground(new Color(255, 153, 0));
		btnChange.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnChange.setForeground(new Color(255, 255, 255));
		btnChange.setBounds(623, 641, 97, 23);
		contentPane.add(btnChange);
		btnChange.addActionListener(listener);
		
		JButton btnDelete = new JButton("删除");
		btnDelete.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnDelete.setBackground(new Color(255, 153, 0));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBounds(863, 641, 97, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(listener);
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(449, 35, 647, 493);
		contentPane.add(scrollPane);
		
		
	}
         public static void setTabel(Object info[][],Object[]o) {
        	 table = new JTable(info,o);
        	ReaderTypeManageMethod listener=new ReaderTypeManageMethod();
 			table.getSelectionModel().addListSelectionListener(listener);
     		scrollPane.setViewportView(table);
         }
         public static void setAdminName(String name) {
        	 label_adminName.setText("管理员："+name);
         }
}
