package Controller;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.FileDialog;
import java.awt.HeadlessException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BookManage.BookManage;
import ReaderMS.photo;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.Image;
import java.awt.Toolkit;

import model.book;

public class BookManageMethod implements ListSelectionListener,ActionListener,MouseListener  {
	
	public BookManageMethod()
	{}
	//static BookManage frame;

	public static book bookmodel[];
	public static String address="C:\\Users\\86185\\Desktop\\1.jpg.jpg";
	static Object o[]= {"图书序号","图书编号","书名","作者","出版社","出版日期","书号","分类号","语言","页数","价格","入馆日期","内容简介","状态"};
	//封装BookManage的所有操作实现方法
	//获得页面对象
		static BookManage bookUI=BookManage.getInstance();
		//封装搜索栏的操作
		//获得搜索栏中的搜索信息,向数据库中开始查询，返回查询的结果集
		public static book[] getBookSearch() throws SQLException, IOException {
			
			String sql = null;
			
			//获得搜索栏中的信息
			String info[]=BookManage.getSearchInfo();
			//判断有哪些可以进行搜索的条件
			if(info[0].equals("")==false&&info[1].equals("")==false&&info[2].equals("")==false) {
				String  bookName=new String(info[0]);
				String bookAuthor=new String(info[1]);
				String bookID=new String (info[2]);	
				//定义查询的sql语句
			 sql ="call query_book_search('"+bookName+"',"+bookID+",'"+bookAuthor+"')";
				JOptionPane.showMessageDialog(null, "执行1");
			}
			else if(info[0].equals("")==false&&info[1].equals("")==false)
			{
				String  bookName=new String(info[0]);
				String bookAuthor=new String(info[1]);
				 sql ="call query_book_search_name('"+bookName+"','"+bookAuthor+"')";
					JOptionPane.showMessageDialog(null, "执行2");
			}
			else  if (info[0].equals("")==false){
				String  bookName=new String(info[0]);
				sql ="call query_book_search_('"+bookName+"')";
				JOptionPane.showMessageDialog(null, "执行3");
				
			}
			else 
			{
				//判断信息不够充分，不能查询
				JOptionPane.showMessageDialog(null, "信息不够充分不能查询，待系统升级后完善");
				return null;
			}
			
			//连接数据库查询数据
			Statement query=MySQLConnection.createQuery();
			ResultSet rs=query.executeQuery(sql);
			//开始读取数据；
			//判断读出的信息有几条
			int row=0;
			while(rs.next())
			{
				row++;
			}
			//游标回到第一行
			rs.first();
		//创建模型对象
			book bookmodel[]=new book[row];
			//开始读取信息
			for(int i=0;i<row;i++)
			{
				bookmodel[i]=new book();
				bookmodel[i].setID(rs.getInt("bkID"));
				bookmodel[i].setCode(rs.getString("bkCode"));
				bookmodel[i].setName(rs.getString("bkName"));
				bookmodel[i].setAuthor(rs.getString("bkAuthor"));
				bookmodel[i].setPress(rs.getString("bkPress"));
				bookmodel[i].setDatePress(rs.getDate("bkDatePress"));
				bookmodel[i].setISBN(rs.getString("bkISBN"));
				bookmodel[i].setCatelog(rs.getString("bkCatalog"));
				bookmodel[i].setLanguage(rs.getInt("bkLanguage"));
				bookmodel[i].setPage(rs.getInt("bkPages"));
				bookmodel[i].setPrice(rs.getDouble("bkPrice"));
				bookmodel[i].setDateln(rs.getDate("bkDateIn"));
				bookmodel[i].setBrief(rs.getString("bkBrief"));
				bookmodel[i].setStatus(rs.getString("bkStatus"));
				Blob picture= rs.getBlob("bkCover");
                InputStream in = picture.getBinaryStream();
                String path="D:\\eclipse\\IDE\\BookManage\\bookPhoto\\"+bookmodel[i].getName()+"__"+bookmodel[i].getAuthor()+".jpg";
                OutputStream out = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = in.read(buffer)) != -1){
                       out.write(buffer, 0, len);
                }
                 Image img = Toolkit.getDefaultToolkit().getImage(path);
                 bookmodel[i].setPhoto(img);
				rs.next();
			}
			return bookmodel;	
		}
		public static boolean updateModel(book model) throws SQLException, IOException {
			boolean status =true;
			String sql="call updateBook(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			 Connection con=MySQLConnection.MysqlConnection();
				PreparedStatement pst = con.prepareStatement(sql);
		        pst.setString(1,model.getCode());              //bkLanguage,bkPages,bkPrice,bkBrief,bkStatus
		        pst.setString(2,model.getName());
		        pst.setString(3,model.getAuthor());
		        pst.setString(4,model.getPress());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(model.getDatePress());
	            pst.setString(5, date);
	            pst.setString(6, model.getISBN());
	            pst.setString(7, model.getCatelog());
	            pst.setInt(8, model.getLanguage());
	            pst.setInt(9, model.getPage());
	            pst.setDouble(10, model.getPrice());
	            pst.setString(11, model.getBrief());
	            pst.setString(12, model.getStatus());
	            date=sdf.format(model.getDateln());
	            pst.setString(13, date);
	            InputStream in = new FileInputStream(address);
				pst.setBlob(14, in);
				pst.setInt(15, model.getID());
				status=pst.execute();//没有结果集就返回false
				in.close();
		        pst.close();
			return status;
		}
		public static boolean insertBookModel(book model) throws SQLException, IOException {
			boolean status=true;
			String sql="call insertBook(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			 Connection con=MySQLConnection.MysqlConnection();
				PreparedStatement pst = con.prepareStatement(sql);
		        pst.setString(1,model.getCode());              //bkLanguage,bkPages,bkPrice,bkBrief,bkStatus
		        pst.setString(2,model.getName());
		        pst.setString(3,model.getAuthor());
		        pst.setString(4,model.getPress());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(model.getDatePress());
	            pst.setString(5, date);
	            pst.setString(6, model.getISBN());
	            pst.setString(7, model.getCatelog());
	            pst.setInt(8, model.getLanguage());
	            pst.setInt(9, model.getPage());
	            pst.setDouble(10, model.getPrice());
	            pst.setString(11, model.getBrief());
	            pst.setString(12, model.getStatus());
	            date=sdf.format(model.getDateln());
	            pst.setString(13, date);
	            InputStream in = new FileInputStream(address);
				pst.setBlob(14, in);
				status=pst.execute();//没有结果集就返回false
				in.close();
		        pst.close();
		        con.close();
			return status;
		}
		public static void deleteBook(book model) throws SQLException {
			String sql="call deleteBook(?)";
			 Connection con=MySQLConnection.MysqlConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, model.getID());
				pst.execute();
				pst.close();
				con.close();
		}
		public static Object[][] toTable(book model[]){
			//获取对象的个数
			int row =model.length;
			//创建tbale显示内容
			Object result[][]=new Object[row][14];
			//开始封装
			for(int i=0;i<row;i++) {
				 int j=0;
					result[i][j++]=(Object)model[i].getID();
					result[i][j++]=(Object)model[i].getCode();
					result[i][j++]=(Object)model[i].getName();
					result[i][j++]=(Object)model[i].getAuthor();
					result[i][j++]=(Object)model[i].getPress();
					result[i][j++]=(Object)getDate(model[i].getDatePress());
					result[i][j++]=(Object)model[i].getISBN();
					result[i][j++]=(Object)model[i].getCatelog();
					result[i][j++]=(Object)getLanguage(model[i]);
					result[i][j++]=(Object)model[i].getPage();
					result[i][j++]=(Object)model[i].getPrice();
					result[i][j++]=(Object)getDate(model[i].getDateln());
					result[i][j++]=(Object)model[i].getBrief();
					result[i][j++]=(Object)model[i].getStatus();
			}
			return result;
		}
 public static String getLanguage(book model) {
	 String result=null;
	 switch(model.getLanguage()) {
	 case 0:result="中文";break;
	 case 1:result="英文";break;
	 case 2:result="日文";break;
	 case 3:result="俄文";break;
	 case 4:result="德文";break;
	 case 5:result="法文";break;
	 }
	 return  result;
 }
 
public static String getDate(Date date) {
	String result=null;
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 result = sdf.format(date);
	return result;
}

		public static boolean compare(book model,book modelArray[]) {
			boolean status =true;
			for(int i=0;i<modelArray.length;i++) {
				if(model.getID()==modelArray[i].getID()) {
					status=false;
					break;
				}
			}
			return status;
			
		}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			//获得选中的行
			int row =0;//默认为第一行
			row=BookManage.table.getSelectedRow();//选中行
			 row=row>-1?row:0;                                                                //选择相应的模型，传入右边窗体
			BookManage.getInstance().modelToView(BookManageMethod.bookmodel[row]);
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			String source=e.getActionCommand(); 
			Object info[][] = null;
			BookManage s=BookManage.getInstance();//获取页面对象
			int row=0;
			if(s.table!=null) {
				row=s.table.getSelectedRow();
				row=row>-1?row:0;
			}
			switch(source)
			{
		  case "修改":
        	  JOptionPane.showMessageDialog(null, "执行");
        	  //获取页面信息
				try { 
					book viewModel=s.viewToModel();
					if(viewModel==null) {
						   JOptionPane.showMessageDialog(null, "非法操作","警告",JOptionPane.WARNING_MESSAGE);	
						   break;
					}
					if(viewModel.getID()==bookmodel[row].getID()) {
					  bookmodel[row]=viewModel;
					  updateModel(bookmodel[row]);      //预留更新数据库接口
 	                  info=toTable(bookmodel);            //刷新视图
		              BookManage.setTable(info, o);        	//显示
					}
					else
					{
				  JOptionPane.showMessageDialog(null,"非法操作，更改图书的序号","警告",JOptionPane.WARNING_MESSAGE);
					}
				
				}catch (ParseException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
        	          
			break;	
		  case "上传封面":
			  JFileChooser fc=new JFileChooser("D:\\");
	          int val=fc.showOpenDialog(null);    //文件打开对话框
	          if(val==fc.APPROVE_OPTION)
	          {
	              //正常选择文件
	            address =fc.getSelectedFile().toString(); 
	            Image img = Toolkit.getDefaultToolkit().getImage(address);   //绘制图片
	           img.getScaledInstance(163, 263, Image.SCALE_DEFAULT);//图片缩放
	             photo p=s.photo;
	              p.setImage(img);
	              SwingUtilities.updateComponentTreeUI(p);
	          }
	          else
	          {
	           JOptionPane.showMessageDialog(null,"没有选择照片");
	          }
			  break;
			  
		  case "添加图书":
			    try {
					if(s.viewToModel()!=null) {
						JOptionPane.showMessageDialog(null, "执行1");
						book newModel=s.viewToModel();//获取视图模型对象
						if(compare(newModel,bookmodel)) {
							JOptionPane.showMessageDialog(null, "执行2");
						book newBookModel[]=new book[bookmodel.length+1];
						for(int i=0;i<bookmodel.length;i++) {
							newBookModel[i]=bookmodel[i];
						}
						newBookModel[bookmodel.length]=newModel;
						bookmodel=newBookModel;
						if(insertBookModel(newModel)==false){//传入数据库
							JOptionPane.showMessageDialog(null, "插入成功");
						 info=toTable(bookmodel);            //刷新视图
			      		BookManage.setTable(info, o);	//显示                           
						}
						else {
							JOptionPane.showMessageDialog(null, "未执行");
						}
					}
					
				} 
					else {
						 JOptionPane.showMessageDialog(null, "非法操作","警告",JOptionPane.WARNING_MESSAGE);	
					}
			    }catch (ParseException | SQLException | HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			        
			  break;
		  case "删除":
			   if(s.table!=null) {
				   try {
					deleteBook(bookmodel[row]);
					 s.setClear();
					bookmodel=BookManageMethod.getBookSearch();    //刷新视图
				 info=toTable(bookmodel);//封装成表格信息	
					BookManage.setTable(info, o);//显示
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			   }
			   else {
				   JOptionPane.showMessageDialog(null, "非法操作","警告",JOptionPane.WARNING_MESSAGE);	 
			   }
			  break;
		  case "清除":
			  s.setClear();
			  break;
		}	
			
	}
		@Override
		public void mouseClicked(MouseEvent e) {
            if(e.getSource()==BookManage.getInstance().book_search) {
				JOptionPane.showMessageDialog(null, "监听");	
		try {
			book array[]=BookManageMethod.getBookSearch();
			if(array!=null) {
			  bookmodel=array;//获取页面封装的模型对象	
			Object info[][]=toTable(bookmodel);//封装成表格信息	
			BookManage.setTable(info, o);//显示
			}
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
            }
            
            else if(e.getSource()==BookManage.getInstance().book_excel) {
				JOptionPane.showMessageDialog(null,"打印信息！");
				FileDialog fd;
					fd = new FileDialog(new JFrame(), "保存图书信息", FileDialog.SAVE);
				      fd.setVisible(true);  
				     String stringfile = fd.getDirectory()+fd.getFile()+".xlsx";  
			         try {
						PaintMethod.exportTable(BookManage.table, new File(stringfile));
			         } catch (IOException ex) {
			             ex.printStackTrace();
			         }
				}
            
		
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
}

