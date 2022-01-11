package ReaderMS;

import java.awt.*;
import javax.swing.*;
import Controller.ReaderManageMethod;

public class chose extends JPanel  {

	private static final long serialVersionUID = 1L;
	private static  JTable table;
	private static JTextField search_name;
	private static JComboBox search_type;
	private static  JComboBox search_dept;
	public static	 JLabel label_search;
	public static JLabel label_excel;
	private String typeArray[];

	public static chose s=new chose();
	public static chose getChose()
	{
	     return s;
	}
	private chose() {
		setBackground(new Color(153, 204, 255));
		setBorder(null);
		setLayout(null);
		
		JLabel label_readerType = new JLabel("读者类别");
	
		label_readerType.setFont(new Font("楷体", Font.PLAIN, 14));
		label_readerType.setBounds(23, 34, 58, 15);
		add(label_readerType);
		
		search_type = new JComboBox();
		//String typeArray[]=ReaderInfo.queryReaderTypeArray();
		typeArray=ReaderInfo.queryReaderTypeArray();
		search_type.setModel(new DefaultComboBoxModel(typeArray));
		search_type.setFont(new Font("楷体", Font.PLAIN, 12));
		search_type.setForeground(Color.BLACK);
		search_type.setBackground(SystemColor.window);
		search_type.setBounds(116, 26, 167, 23);
		add(search_type);
		
		JLabel label_readerDept = new JLabel("读者单位");
		label_readerDept.setFont(new Font("楷体", Font.PLAIN, 14));
		label_readerDept.setBounds(333, 34, 58, 15);
		add(label_readerDept);
		
	    search_dept = new JComboBox();
		search_dept.setModel(new DefaultComboBoxModel(new String[] {"计算机科学学院", "石油工程学院", "机械工程学院", "电子信息学院", "数信学院", "医学院", "化学与环境工程学院", "地球物理与石油资源学院", "地球科学学院", "经济与管理学院", "法学院", "人文与新媒体学院", "物理与光电学院", "城市建设学院", "外国语学院", "农学院"}));
		search_dept.setFont(new Font("楷体", Font.PLAIN, 12));
		search_dept.setBackground(Color.WHITE);
		search_dept.setBounds(401, 26, 146, 23);
		add(search_dept);
		
		JLabel label_readerName = new JLabel("姓名");
		label_readerName.setFont(new Font("楷体", Font.PLAIN, 14));
		label_readerName.setBounds(573, 34, 58, 15);
		add(label_readerName);
		
		search_name = new JTextField();
		search_name.setFont(new Font("楷体", Font.PLAIN, 12));
		search_name.setBackground(Color.WHITE);
		search_name.setBounds(620, 28, 139, 21);
		add(search_name);
		search_name.setColumns(10);
		
	    label_search = new JLabel("查找");
		label_search.setIcon(new ImageIcon("D:\\eclipse\\IDE\\BookManage\\icon\\搜索，放大镜(1)xxx.png"));
		label_search.setFont(new Font("楷体", Font.PLAIN, 14));
		label_search.setBounds(797, 34, 68, 15);
		add(label_search);
		 ReaderManageMethod listener=new  ReaderManageMethod();
       	 label_search.addMouseListener(listener);
		
		
		 label_excel = new JLabel("导出");
		label_excel.setIcon(new ImageIcon("D:\\eclipse\\IDE\\BookManage\\icon\\Excel(1).png"));
		label_excel.setFont(new Font("楷体", Font.PLAIN, 14));
		label_excel.setBounds(875, 34, 58, 15);
		add(label_excel);
		label_excel.addMouseListener(listener);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\86185\\Desktop\\搜索，放大镜(1)xxx.png"));
		lblNewLabel.setBounds(785, 30, 38, 19);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\86185\\Desktop\\Excel(1).png"));
		lblNewLabel_1.setBounds(891, 30, 38, 19);
		add(lblNewLabel_1);
	}
	
	public   void updateReaderType() {
		 typeArray=ReaderInfo.queryReaderTypeArray();
		search_type.setModel(new DefaultComboBoxModel(typeArray));
	}
	public static String[] getInfo(){
		String result[]=new String[3];
		result[0]=search_type.getSelectedItem().toString();
		result[1]=search_dept.getSelectedItem().toString();
         result[2]=search_name.getText();
			return result;		
	}
}
