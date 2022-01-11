package ReaderMS;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.*;
import com.ibm.icu.text.SimpleDateFormat;
import java.util.Date;

import Controller.MySQLConnection;
import Controller.ReaderManageMethod;
import Controller.ReaderTypeManageMethod;
import model.reader;
import model.readerType;

import javax.swing.DefaultComboBoxModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
public class ReaderInfo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField readerInfo_ID;
	private JTextField readerInfo_name;
	private JTextField readerInfo_pwd;
	private JTextField readerInfo_lendQty;
	private JTextField readerInfo_status;
	private JTextField readerInfo_phone;
	private JTextField readerInfo_email;
	private JTextField readerInfo_date;
	private	JComboBox readerInfo_type;
	public 	JComboBox readerInfo_dept;
	private	JComboBox readerInfo_sex;
	private	JComboBox readerInfo_rolse;
	private String address;
	public static photo photo;

	/**
	 * Create the panel.
	 */
	  private static ReaderInfo s = new ReaderInfo();
	  public static Image img;
	  private 		String typeArray[];
	public static ReaderInfo getReaderInfo() {
		 SwingUtilities.updateComponentTreeUI(photo);
		return s;
	}
	private  ReaderInfo() {
		setBackground(new Color(204, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		ReaderManageMethod listener=new ReaderManageMethod();
		
		JLabel label_reader = new JLabel("读者信息");
		label_reader.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		label_reader.setBounds(0, 0, 58, 15);
		add(label_reader);
		
		JLabel label_id = new JLabel("借书证号");
		label_id.setBounds(10, 60, 58, 15);
		add(label_id);
		
		JLabel label_name = new JLabel("姓名");
		label_name.setBounds(10, 100, 58, 15);
		add(label_name);
		
		JLabel label_pwd = new JLabel("密码");
		label_pwd.setBounds(10, 140, 58, 15);
		add(label_pwd);
		
		JLabel label_sex = new JLabel("性别");
		label_sex.setBounds(10, 180, 58, 15);
		add(label_sex);
		
		JLabel label_status = new JLabel("证件状态");
		label_status.setBounds(10, 260, 58, 15);
		add(label_status);
		
		JLabel label_dept = new JLabel("单位");
		label_dept.setBounds(10, 380, 58, 15);
		add(label_dept);
		
		JLabel label_phone = new JLabel("电话号码");
		label_phone.setBounds(10, 420, 58, 15);
		add(label_phone);
		
		JLabel label_emaill = new JLabel("电子邮件");
		label_emaill.setBounds(10, 460, 58, 15);
		add(label_emaill);
		
		JLabel label_date = new JLabel("办证日期");
		label_date.setBounds(10, 500, 58, 15);
		add(label_date);
		
		JLabel label_cate = new JLabel("读者类别");
		label_cate.setBounds(10, 340, 58, 15);
		add(label_cate);
		
		JLabel label_qty = new JLabel("已借本数");
		label_qty.setBounds(10, 220, 58, 15);
		add(label_qty);
		
		readerInfo_ID = new JTextField();
		readerInfo_ID.setEditable(false);
		readerInfo_ID.setBounds(88, 54, 84, 21);
		add(readerInfo_ID);
		readerInfo_ID.setColumns(10);
		
		readerInfo_name = new JTextField();
		readerInfo_name.setBounds(88, 94, 84, 21);
		add(readerInfo_name);
		readerInfo_name.setColumns(10);
		
		readerInfo_pwd = new JTextField();
		readerInfo_pwd.setBounds(88, 134, 84, 21);
		add(readerInfo_pwd);
		readerInfo_pwd.setColumns(10);
		
		readerInfo_lendQty = new JTextField();
		readerInfo_lendQty.setEditable(false);
		readerInfo_lendQty.setBounds(88, 214, 84, 21);
		add(readerInfo_lendQty);
		readerInfo_lendQty.setColumns(10);
		
		readerInfo_status = new JTextField();
		readerInfo_status.setEditable(false);
		readerInfo_status.setBounds(88, 254, 84, 21);
		add(readerInfo_status);
		readerInfo_status.setColumns(10);
		
		readerInfo_phone = new JTextField();
		readerInfo_phone.setBounds(88, 414, 311, 21);
		add(readerInfo_phone);
		readerInfo_phone.setColumns(10);
		
		readerInfo_email = new JTextField();
		readerInfo_email.setBounds(88, 454, 311, 21);
		add(readerInfo_email);
		readerInfo_email.setColumns(10);
		
		readerInfo_date = new JTextField();
		readerInfo_date.setEditable(false);
		readerInfo_date.setBounds(88, 494, 311, 21);
		add(readerInfo_date);
		readerInfo_date.setColumns(10);
		
		readerInfo_type = new JComboBox();
	    typeArray=queryReaderTypeArray(); //查询类别表中的所有类型
		readerInfo_type.setModel(new DefaultComboBoxModel(typeArray));
	                               
		readerInfo_type.setBackground(SystemColor.window);
		readerInfo_type.setBounds(88, 332, 311, 23);
		add(readerInfo_type);
		
		 readerInfo_dept = new JComboBox();
		readerInfo_dept.setModel(new DefaultComboBoxModel(new String[] {"", "计算机科学学院", "石油工程学院", "机械工程学院", "电子信息学院", "数信学院", "医学院", "化学与环境工程学院", "地球物理与石油资源学院", "地球科学学院", "经济与管理学院", "法学院", "人文与新媒体学院", "物理与光电学院", "城市建设学院", "外国语学院", "农学院"}));
		readerInfo_dept.setBackground(SystemColor.window);
		readerInfo_dept.setBounds(88, 372, 311, 23);
		add(readerInfo_dept);
		
		 readerInfo_sex = new JComboBox();
		readerInfo_sex.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		readerInfo_sex.setBackground(SystemColor.window);
		readerInfo_sex.setBounds(88, 172, 81, 23);
		add(readerInfo_sex);
		
		JLabel label_role = new JLabel("读者角色");
		label_role.setBounds(10, 300, 58, 15);
		add(label_role);
		
	    readerInfo_rolse = new JComboBox();
		readerInfo_rolse.setModel(new DefaultComboBoxModel(new String[] {"读者", "借书证管理员", "借阅管理员", "系统管理员"}));
		readerInfo_rolse.setBackground(SystemColor.window);
		readerInfo_rolse.setBounds(88, 292, 84, 23);
		add(readerInfo_rolse);
		
		JButton button_update = new JButton("确认变更");
		button_update.setFocusPainted(false);
		button_update.setBorderPainted(false);
		button_update.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_update.setForeground(new Color(255, 255, 255));
		button_update.setBackground(new Color(255, 153, 0));
		button_update.setBounds(172, 547, 97, 23);
		add(button_update);
		button_update.addActionListener(listener);
		
		
		JButton button_sure = new JButton("确认办证");
		button_sure.setFocusPainted(false);
		button_sure.setBorderPainted(false);
		button_sure.setForeground(new Color(255, 255, 255));
		button_sure.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_sure.setBackground(new Color(255, 153, 0));
		button_sure.setBounds(36, 547, 97, 23);
		add(button_sure);
		button_sure.addActionListener(listener);
		
		JButton button_clear = new JButton("清空");
		button_clear.setFocusPainted(false);
		button_clear.setBorderPainted(false);
		button_clear.setForeground(new Color(255, 255, 255));
		button_clear.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_clear.setBackground(new Color(255, 153, 0));
		button_clear.setBounds(302, 547, 97, 23);
		add(button_clear);
		button_clear.addActionListener(listener);
		
		JButton button_photo = new JButton("上传照片");
		button_photo.addActionListener(listener);
		button_photo.setForeground(Color.WHITE);
		button_photo.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		button_photo.setFocusPainted(false);
		button_photo.setBorderPainted(false);
		button_photo.setBackground(new Color(255, 153, 0));
		button_photo.setBounds(277, 292, 97, 23);
		add(button_photo);
		
		 photo = new photo();
		photo.setBounds(219, 46, 185, 225);
		add(photo);
	}
	//Object o[]= {"编号","类别","姓名","性别","单位","电话号码","电子邮箱","登记日期","证件状态","读者密码","管理角色"};
public  static String[]  queryReaderTypeArray()  {
	readerType model[] = null;
	try {
		model = ReaderTypeManageMethod.queryReaderType();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String[] typeName=new String[model.length];
   for(int i=0;i<model.length;i++) 
	typeName[i]=model[i].getTypeName(); 
	return typeName;
}
	public static int toSexType(String s) {
		int type=0;
		 switch(s)
		  {
		   case "男":type=0;break;
		   case "女":type=1;break;
		  }
		return type;
	}
	public static int toDeptType(String s) {
		int type=0;
		   switch(s)
		   {
		   case "计算机科学学院" :type=0;break;
		   case "石油工程学院":type=1;break;
		   case  "机械工程学院":type=2;break;       
		   case "电子信息学院":type=3;break;
		   case "数信学院":type=4;break;
		   case "医学院":type=5;break;
		   case "化学与环境工程学院":type=6;break;
		   case "地球物理与石油资源学院":type=7;break;
		   case "地球科学学院":type=8;break;
		   case "经济与管理学院":type=9;break;
		   case "法学院":type=10;break;
		   case "人文与新媒体学院":type=11;break;
		   case "物理与光电学院":type=12;break;
		   case "城市建设学院":type=13;break;
		   case "外国语学院":type=15;break;
		   case "农学院":type=16;break;
		   }
		return type+1;
	}
	public void updateType() {
	    typeArray=queryReaderTypeArray(); //查询类别表中的所有类型
			readerInfo_type.setModel(new DefaultComboBoxModel(typeArray));
	}
public static String toDate(Date date) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	return formatter.format(date);
	
}	
	public  void  modelToView(reader model) throws SQLException {
	 readerInfo_ID.setText(String.valueOf(model.getID()));
	 readerInfo_type.setSelectedItem((Object)ReaderManageMethod.getRolse(model));
	 readerInfo_name.setText(model.getName());
	 readerInfo_sex.setSelectedIndex(toSexType(model.getSex()));
	 readerInfo_dept.setSelectedIndex(toDeptType(model.getDept()));
	 readerInfo_phone.setText(model.getPhone());
	 readerInfo_email.setText(model.getEmaill());
	 readerInfo_date.setText(toDate(model.getDate()));
	 readerInfo_status.setText(ReaderManageMethod.getStatus(model));
	 readerInfo_pwd.setText(model.getPwd());
	 readerInfo_rolse.setSelectedItem(ReaderManageMethod.getRolse(model));	
	 readerInfo_lendQty.setText(String.valueOf(model.getLendQty()));
	 ReaderInfo s=ReaderInfo.getReaderInfo();//获得页面对象
	  photo p=s.photo;
      p.setImage(model.getPhoto());
      SwingUtilities.updateComponentTreeUI(p);
	}
	
	 public  int  generateUserInfo() throws SQLException {
		 int id=20210000;
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String sql="call queryID";
	    	Statement query=MySQLConnection.createQuery();
	    	ResultSet rs=query.executeQuery(sql);
	    	while(rs.next()) {
	    		id+=rs.getInt(1);
	    	}
	    	rs.close();
	    	readerInfo_ID.setText(String.valueOf(id));
	    	readerInfo_date.setText(formatter.format(new Date()));
	    	readerInfo_status.setText("有效");
	    	readerInfo_lendQty.setText("0");
	    	return id;
	 }
	 public void clearView() {
		 readerInfo_ID.setText("");
		 readerInfo_name.setText("");
		 readerInfo_phone.setText("");
		 readerInfo_email.setText("");
		 readerInfo_date.setText("");
		 readerInfo_status.setText("");
		 readerInfo_pwd.setText("");	
		 readerInfo_lendQty.setText("");
	 }
	public  reader viewTModel() throws SQLException, ParseException {
		reader model=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(readerInfo_ID.getText().equals(readerInfo_pwd.getText())) {
			JOptionPane.showMessageDialog(null, "非法操作","警告",JOptionPane.WARNING_MESSAGE);
			return null;
		}
	    if(inspectPwd(readerInfo_pwd.getText())) {
	    model=new reader();
		model.setType(toStoreType((String)readerInfo_type.getSelectedItem()));
		model.setName(readerInfo_name.getText());
		model.setSex((String)readerInfo_sex.getSelectedItem());
		model.setDept((String)readerInfo_dept.getSelectedItem());
	    model.setEmaill(readerInfo_email.getText());
	    model.setPhone(readerInfo_phone.getText());
	    model.setRoles(toStoreRolesType((String)readerInfo_rolse.getSelectedItem()));
	    model.setPwd(readerInfo_pwd.getText()); 
	    img = Toolkit.getDefaultToolkit().getImage(address);//获得图片
	    img.getScaledInstance(163, 263, Image.SCALE_SMOOTH);
	    model.setPhoto(img);
	    if(readerInfo_date.getText().equals(""))
	    {      
	        model.setID(s.generateUserInfo());
	    	model.setDate(new Date());
	    	model.setLendQty(0);
	    	model.setStatus("1");
	    }
	    else
	    {	
	    	Date date =formatter.parse(readerInfo_date.getText());
	    	model.setID(Integer.parseInt(readerInfo_ID.getText()));
	    	model.setDate(date);
	    	model.setLendQty(Integer.parseInt(readerInfo_lendQty.getText()));
	    	model.setStatus(toStoreStatusType(readerInfo_status.getText()));  	
	    }
	    } 
		return model;
		
	}
	public  static  boolean inspectPwd(String password) {
		// 判断密码是否合法,规定密码的最大位数位十二位,密码是数字和字母和符号组成，不能全是数字，或字母
		boolean isDigit = false;
		boolean isLetter = false;
		boolean status = true;
		char a[] = password.toCharArray();
		for (char c : a) {
			if (Character.isDigit(c))
				isDigit = true;
			if (Character.isLetter(c))
				isLetter = true;
		}
		status = isDigit && isLetter && (password.length() <= 12);
		if (status == false) 
			JOptionPane.showMessageDialog(null, "密码的格式不正确！", "警告", JOptionPane.WARNING_MESSAGE);
		return status;
	}
	public static int toStoreRolesType(String roles) {
		int result=2;
		switch(roles) {
		case "读者":result=0;break;
		case "借书证管理员" :result=1;break;
		case "借阅管理员":result=4;break;
		case "系统管理员":result=8;break;
		default :    result=2;break;
		}
		return result;

	}
	public static String toStoreStatusType(String status) {
		String result=new String();
		switch(status)
		{
		case "有效": result="1";break;
		case "注销":result="0";break;
		case "挂失":result="2";break;
		default :result=null;break;
		}
		return result;
		
	}
	
	public static int  toStoreType(String Stringtype) throws SQLException {
		 int result=0;
		readerType typemodel[]=ReaderTypeManageMethod.queryReaderType();
		int readertype[]=new int[typemodel.length];
		String[] typeName=new String[typemodel.length];
	   for(int i=0;i<typemodel.length;i++) {
		readertype[i]=typemodel[i].getType();
		typeName[i]=typemodel[i].getTypeName();
		if(Stringtype.equals(typeName[i]))
		 {
	      result=readertype[i];
	      break;
	       	}
	   }
		return result;
		
	}

//将图片保存；
}