package BookManage;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ibm.icu.text.SimpleDateFormat;

import Controller.BookManageMethod;
import model.book;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import ReaderMS.photo;

public class BookManage extends JPanel {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtAuthor;
	private JTextField txtPress;
	private JTextField txtDatePress;
	private JTextField txtISBN;
	private JTextField txtPage;
	private JTextField txtPrice;
	private JTextField txtDateln;
	private static JTextField book_name;
	private static JTextField book_author;
	private static JTextField book_id;
	private static JPanel panel_1;
	public JLabel book_search;
	public static JScrollPane scrollPane_table;
	public JLabel book_excel;
	private JComboBox cbxStatus;
	private JTextArea txtBrief;
	public static JTable table;
   private 	JComboBox cbxLanguage;
   public 	photo photo;
   public static BookManage bookManage=new BookManage();
   private JTextField txtCatelog;
   public  BookManageMethod listener=new BookManageMethod();
   
    public static BookManage getInstance() {
	return bookManage;
}
	private BookManage() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\IDE\\BookManage\\icon\\登录(1).png"));
		//setTitle("图书管理");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane=this;
		setBounds(100, 100,  1120, 760);
		//contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		
		contentPane.setLayout(null);
	
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(540, 64, 556, 681);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("图书信息");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel.setBounds(0, 0, 58, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("图书序号");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(29, 32, 58, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("图书作者");
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(29, 173, 58, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("出版社名");
		lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(29, 220, 58, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("标准ISBN");
		lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(29, 314, 58, 15);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("名称分类");
		lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(29, 361, 58, 15);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("所属语种");
		lblNewLabel_6.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(29, 408, 58, 15);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("图书页数");
		lblNewLabel_7.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(29, 455, 58, 15);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("图书价格");
		lblNewLabel_8.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(29, 502, 58, 15);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("入馆日期");
		lblNewLabel_9.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(29, 549, 58, 15);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("图书状态");
		lblNewLabel_10.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(29, 596, 58, 15);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_13 = new JLabel("图书名称");
		lblNewLabel_13.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_13.setBounds(29, 126, 58, 15);
		panel.add(lblNewLabel_13);
		
		JLabel lblNewLabel_15 = new JLabel("图书编号");
		lblNewLabel_15.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_15.setBounds(29, 79, 58, 15);
		panel.add(lblNewLabel_15);
		
		JLabel lblNewLabel_14 = new JLabel("出版日期");
		lblNewLabel_14.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_14.setBounds(29, 267, 58, 15);
		panel.add(lblNewLabel_14);
		
		txtID = new JTextField();
		txtID.setBackground(SystemColor.window);
		txtID.setBounds(127, 26, 184, 21);
		panel.add(txtID);
		txtID.setColumns(10);
		
		txtCode = new JTextField();
		txtCode.setBackground(SystemColor.window);
		txtCode.setBounds(127, 73, 184, 21);
		panel.add(txtCode);
		txtCode.setColumns(10);
		
		
		txtName = new JTextField();
		txtName.setBackground(SystemColor.window);
		txtName.setBounds(127, 120, 184, 21);
		panel.add(txtName);
		txtName.setColumns(10);
		
		
		txtAuthor = new JTextField();
		txtAuthor.setBackground(SystemColor.window);
		txtAuthor.setBounds(127, 167, 184, 21);
		panel.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		
		txtPress = new JTextField();
		txtPress.setBackground(SystemColor.window);
		txtPress.setBounds(127, 214, 184, 21);
		panel.add(txtPress);
		txtPress.setColumns(10);
		
		
		txtDatePress = new JTextField();
		txtDatePress.setBackground(SystemColor.window);
		txtDatePress.setBounds(127, 261, 184, 21);
		panel.add(txtDatePress);
		txtDatePress.setColumns(10);
	
		
		txtISBN = new JTextField();
		txtISBN.setBackground(SystemColor.window);
		txtISBN.setBounds(127, 308, 184, 21);
		panel.add(txtISBN);
		txtISBN.setColumns(10);
		
		
		txtPage = new JTextField();
		txtPage.setBackground(SystemColor.window);
		txtPage.setBounds(127, 453, 184, 21);
		panel.add(txtPage);
		txtPage.setColumns(10);
		
		
		txtPrice = new JTextField();
		txtPrice.setBackground(SystemColor.window);
		txtPrice.setBounds(127, 500, 184, 21);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
	
		
		txtDateln = new JTextField();
		txtDateln.setBackground(SystemColor.window);
		txtDateln.setBounds(127, 547, 184, 21);
		panel.add(txtDateln);
		txtDateln.setColumns(10);
		
		
	     cbxStatus = new JComboBox();
		cbxStatus.setModel(new DefaultComboBoxModel(new String[] {"在馆", "借出", "遗失", "变卖", "销毁"}));
		cbxStatus.setBackground(SystemColor.window);
		cbxStatus.setBounds(127, 594, 184, 23);
		panel.add(cbxStatus);
		cbxStatus.setOpaque(false);
	     cbxLanguage = new JComboBox();
		cbxLanguage.setModel(new DefaultComboBoxModel(new String[] {"中文", "英文", "日文", "俄文", "德文", "法文"}));
		cbxLanguage.setBackground(SystemColor.window);
		cbxLanguage.setBounds(127, 404, 184, 23);
		panel.add(cbxLanguage);
	    cbxLanguage.setOpaque(false);
		JLabel lblNewLabel_11 = new JLabel("图书简介");
		lblNewLabel_11.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(341, 10, 58, 15);
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("图书封面");
		lblNewLabel_12.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(340, 290, 58, 15);
		panel.add(lblNewLabel_12);
		
		JButton btnAdd = new JButton("添加图书");
		btnAdd.setBackground(new Color(255, 153, 0));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("华文琥珀", Font.PLAIN, 12));
		btnAdd.setBounds(33, 648, 97, 23);
		panel.add(btnAdd);
		btnAdd.addActionListener(listener);
		
		JButton btn_change = new JButton("修改");
		btn_change.setBackground(new Color(255, 153, 0));
		btn_change.setForeground(new Color(255, 255, 255));
		btn_change.setFont(new Font("华文琥珀", Font.PLAIN, 12));
		btn_change.setBounds(163, 648, 97, 23);
		panel.add(btn_change);
		btn_change.addActionListener(listener);
		
		JButton btn_delete = new JButton("删除");
		btn_delete.setBackground(new Color(255, 153, 0));
		btn_delete.setForeground(new Color(255, 255, 255));
		btn_delete.setFont(new Font("华文琥珀", Font.PLAIN, 12));
		btn_delete.setBounds(293, 648, 97, 23);
		panel.add(btn_delete);
		btn_delete.addActionListener(listener);
		
		JButton btn_clear = new JButton("清除");
		btn_clear.setBackground(new Color(255, 153, 0));
		btn_clear.setForeground(new Color(255, 255, 255));
		btn_clear.setFont(new Font("华文琥珀", Font.PLAIN, 12));
		btn_clear.setBounds(423, 648, 97, 23);
		panel.add(btn_clear);
		btn_clear.addActionListener(listener);
		
		JButton btnUpphoto = new JButton("上传封面");
		btnUpphoto.setForeground(new Color(255, 255, 255));
		btnUpphoto.setBackground(new Color(255, 153, 0));
		btnUpphoto.setFont(new Font("华文琥珀", Font.ITALIC, 12));
		btnUpphoto.setBounds(421, 615, 97, 23);
		panel.add(btnUpphoto);
		btnUpphoto.addActionListener(listener);
		
		txtCatelog = new JTextField();
		txtCatelog.setBounds(127, 355, 184, 21);
		panel.add(txtCatelog);
		txtCatelog.setColumns(10);
		
		 txtBrief = new JTextArea();
		 txtBrief.setRows(20);
		txtBrief.setBounds(344, 29, 184, 241);
		panel.add(txtBrief);
		
		 photo = new photo();
		photo.setBounds(341, 349, 184, 221);
		panel.add(photo);
		
		
		 panel_1 = new JPanel();
		 panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 64, 508, 681);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
	 scrollPane_table = new JScrollPane();
		scrollPane_table.setBounds(0, 0, 508, 681);
		panel_1.add(scrollPane_table);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 204, 255));
		panel_2.setBounds(10, -2, 1086, 42);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_17 = new JLabel("图书名称");
		lblNewLabel_17.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_17.setBounds(49, 10, 58, 15);
		panel_2.add(lblNewLabel_17);
		
		book_name = new JTextField();
		book_name.setBounds(118, 4, 121, 21);
		panel_2.add(book_name);
		book_name.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("图书作者");
		lblNewLabel_18.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_18.setBounds(285, 10, 58, 15);
		panel_2.add(lblNewLabel_18);
		
		book_author = new JTextField();
		book_author.setBounds(353, 4, 121, 21);
		panel_2.add(book_author);
		book_author.setColumns(10);
		
		JLabel lblNewLabel_19 = new JLabel("图书序号");
		lblNewLabel_19.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_19.setBounds(515, 10, 58, 15);
		panel_2.add(lblNewLabel_19);
		
		 book_search = new JLabel("查询");
		book_search.setIcon(new ImageIcon("D:\\eclipse\\IDE\\BookManage\\icon\\搜索，放大镜(1)xxx.png"));
		book_search.setFont(new Font("楷体", Font.PLAIN, 12));
		book_search.setBounds(776, 10, 58, 15);
		panel_2.add(book_search);
		book_search.addMouseListener(listener);
		
		 book_excel = new JLabel("导出Excel");
		book_excel.setIcon(new ImageIcon("D:\\eclipse\\IDE\\BookManage\\icon\\Excel(1).png"));
		book_excel.setFont(new Font("楷体", Font.PLAIN, 12));
		book_excel.setBounds(845, 10, 90, 15);
		panel_2.add(book_excel);
		book_excel.addMouseListener(listener);
		
		book_id = new JTextField();
		book_id.setBounds(583, 4, 121, 21);
		panel_2.add(book_id);
		book_id.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 508, 669);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_16 = new JLabel("查询结果");
		lblNewLabel_16.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_16.setBounds(10, 50, 58, 15);
		contentPane.add(lblNewLabel_16);
		
	}
	/*public  void setOpaque(boolean  status) {
		txtID.setOpaque(status);
		txtCode.setOpaque(status);
		txtName.setOpaque(status);
		txtAuthor.setOpaque(status);
		txtPress.setOpaque(status);
		txtDatePress.setOpaque(status);
		txtISBN.setOpaque(status);
		txtPage.setOpaque(status);
		txtPrice.setOpaque(status);
		txtDateln.setOpaque(status);
		cbxLanguage.setOpaque(status);
		cbxStatus.setOpaque(status);
		 scrollPane_table.setOpaque(status);
		 contentPane.setOpaque(status);
		 book_name.setOpaque(status);
		 book_author.setOpaque(status);
		 book_id.setOpaque(status);
		
	}*/
	public void setClear() {
		txtID.setText("");
		txtCode.setText("");
		txtName.setText("");
		txtAuthor.setText("");
		txtPress.setText("");
		txtDatePress.setText("");
		txtISBN.setText("");
		txtPage.setText("");
		txtPrice.setText("");
		txtDateln.setText("");
		 txtBrief.setText("");
		 txtCatelog.setText("");
	}
	public static  String [] getSearchInfo() {
		String info[]=new String[3];
		info[0]=book_name.getText();
		info[1]=book_author.getText();
		info[2]=book_id.getText();
		return info;
		
	}
	public  static  void setTable(Object info[][],Object o[]) {
		 table=new JTable(info,o);
		BookManageMethod listener=BookManage.getInstance().listener;
			table.getSelectionModel().addListSelectionListener(listener);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	 scrollPane_table.setViewportView(table);
	}
	public  void modelToView(book model) {
		if(model!=null&&model.getName().equals("")==false) {
		txtID.setText(String.valueOf(model.getID()));
		txtCode.setText(model.getCode());
		txtName.setText(model.getName());
		txtAuthor.setText(model.getAuthor());
		txtPress.setText(model.getPress());
		   SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd"); 
			  String s=formatter.format(model.getDatePress());
		txtDatePress.setText(s);
		txtISBN.setText(model.getISBN());
		txtCatelog.setText(model.getCatelog());
	    txtBrief.setText(model.getBrief());
		cbxLanguage.setSelectedIndex(model.getLanguage());
		txtPage.setText(String.valueOf(model.getPage()));
		txtPrice.setText(String.valueOf(model.getPrice()));
		 String date=formatter.format(model.getDateln());
		txtDateln.setText(date);
		 cbxStatus.setSelectedItem(model.getStatus());
		   BookManage s1=BookManage.getInstance();           //获得页面对象                    
		  photo p=s1.photo;
		  String path="D:\\eclipse\\IDE\\BookManage\\bookPhoto\\"+model.getName()+"__"+model.getAuthor()+".jpg";
		Image img = Toolkit.getDefaultToolkit().getImage(path);   //绘制图片
	      img.getScaledInstance(163, 263, Image.SCALE_DEFAULT);//图片缩放
	      p.setImage(img);
	      SwingUtilities.updateComponentTreeUI(p);
		}
	}
   public book viewToModel() throws ParseException {
	   //创建一个模型
	   book model=new book();
	   try {
	   model.setID(Integer.parseInt(txtID.getText()));
	   model.setCode(txtCode.getText());
	   model.setAuthor(txtAuthor.getText());
	   model.setISBN(txtISBN.getText());
	   model.setLanguage(cbxLanguage.getSelectedIndex());
	   model.setBrief(txtBrief.getText());
	   model.setCatelog(txtCatelog.getText());
	   SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd"); 
	   model.setDateln(formatter.parse(txtDateln.getText()));
	   model.setName(txtName.getText());
	   model.setStatus((String)cbxStatus.getSelectedItem());
	   model.setPage(Integer.parseInt(txtPage.getText()));
	    model.setDatePress(formatter.parse(txtDatePress.getText()));
	    model.setPress(txtPress.getText());
	    model.setPrice(Double.parseDouble(txtPrice.getText()));
	    Image img = Toolkit.getDefaultToolkit().getImage(BookManageMethod.address);//获得图片
	    img.getScaledInstance(163, 263, Image.SCALE_SMOOTH);
	    model.setPhoto(img);
	   }
	   catch(Exception e) {
		   return null;   
	   }
	return model;
	   
   }

}
