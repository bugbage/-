package Controller;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import   MainFrame.MainFrame;
import javax.swing.JOptionPane;

import BorrowManage.BorrowManage;
import model.book;
import model.borrowInfo;
import model.reader;
import model.readerType;
public class BorrowManageMethod implements ActionListener  {
  private static book bookmodel[];
  private static reader readermodel;
  private static borrowInfo borrowmodel[];
  private static readerType typeModel;
	public static void main(String args[]) {
		BorrowManage  frame=BorrowManage.getInstance();
		frame.setVisible(true);
	}
	public static Object[] o= {"借阅顺序号","读者序号","图书序号","续借次数","借书日期","应还日期","实际还书日期","超期天数","超期金额","罚款金额","是否已经还书","借书操作员","还书操作员"};
	static Object o1[]= {"图书序号","图书编号","书名","作者","出版社","出版日期","书号","分类号","语言","页数","价格","入馆日期","内容简介","状态"};
  public  static reader queryReaderInfo(int id) {
	  reader model=new reader();
	  try {
			String sql="call queryReaderInfo(?)";
			Connection con=MySQLConnection.MysqlConnection();
			PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1,id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()==false) {
					JOptionPane.showMessageDialog(null, "数据库中没有该用户");
				return null;	
				}	
				model.setID(rs.getInt("rdID"));     
				 model.setType(rs.getInt("rdType"));
				 model.setName(rs.getString("rdName"));
				 model.setSex(rs.getString("rdSex"));
				 model.setDept(rs.getString("rdDept"));
				 model.setPhone(rs.getString("rdphone"));
				 model.setEmaill(rs.getString("rdEmail"));
				 model.setStatus(rs.getString("rdStatus"));
				 model.setPwd(rs.getString("rdPwd"));
				 model.setRoles(rs.getInt("rdAdminRoles"));
				 model.setLendQty(rs.getInt("rdBorrowQty"));
				 model.setDate(rs.getDate("rdDateReg"));
					Blob picture= rs.getBlob("rdPhoto");
                   InputStream in = picture.getBinaryStream();
                   String path="D:\\eclipse\\IDE\\BookManage\\userPhoto\\"+model.getName()+".jpg";
                   OutputStream out = new FileOutputStream(path);
                   byte[] buffer = new byte[1024];
                   int len = 0;
                   while((len = in.read(buffer)) != -1){
                          out.write(buffer, 0, len);
                   }
                    Image img = Toolkit.getDefaultToolkit().getImage(path);
                    model.setPhoto(img);
			} catch (SQLException | IOException e1) {
				
				e1.printStackTrace();
			}
	  
	return model;  
  }
  public static borrowInfo[]  getBorrowInfo(reader model) throws SQLException {
	 
	  if(model.getLendQty()<=0) {
		  return null;
	  }
		  String sql="call queryBorrow(?)";
		  Connection con=MySQLConnection.MysqlConnection();
			PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				pst.setInt(1,model.getID());
				ResultSet rs=pst.executeQuery();
				if(rs.next()==false) {
					JOptionPane.showMessageDialog(null, "数据库中没有该用户");
					return null;
				}
				int row=1;
				while(rs.next()) {
					row++;
				}
				rs.first();
				borrowInfo modelArray[]=new borrowInfo[row];
				 for(int i=0;i<row;i++) {
					 modelArray[i]=new borrowInfo();
					 modelArray[i].setRdID(rs.getInt("rdID"));
					 modelArray[i].setBkID(rs.getInt("bkID"));
					 modelArray[i].setBorrowID(rs.getInt("BorrowID"));
					 modelArray[i].setIdContinueTimes(rs.getInt("IdContinueTimes"));
					 modelArray[i].setIdDateOut(rs.getDate("IdDateOut"));
					 modelArray[i].setIdDateRetPlan(rs.getDate("IdDateRetPlan"));
					 modelArray[i].setIdDateRetAct(rs.getDate("IdDateRetAct"));
					 modelArray[i].setIdOverDay(rs.getInt("IdOverDay"));
					 modelArray[i].setIdOverMoney(rs.getDouble("IdOverMoney"));
					 modelArray[i].setIdpPunishMoney(rs.getDouble("IdPunishMoney"));
					 modelArray[i].setIsHasReturn(rs.getBoolean("IsHasReturn"));
					 modelArray[i].setOperatorBorrow(rs.getString("OperatorBorrow"));
					 modelArray[i].setOperatorReturn(rs.getString("OperatorReturn"));
					 rs.next();
				 }	
				 con.close();
				 pst.close();
	return modelArray;
  }
  public static readerType getReaderType(reader model) throws SQLException {
	  readerType result=new readerType();
	  String sql="call getReaderType(?)";
	  Connection con=MySQLConnection.MysqlConnection();
		PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			pst.setInt(1,model.getType());
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				result.setType(rs.getInt("rdType"));
				result.setTypeName(rs.getString("rdTypeName"));
				result.setCanLendQty(rs.getInt("CanLendQty"));
				result.setCanLendDay(rs.getInt("CanLendDay"));
				result.setContinueTimes(rs.getInt("CanContinueTimes"));
				result.setPunishRate(rs.getDouble("PunishRate"));
				result.setDateVaild(rs.getInt("DateValid"));
			}
			pst.close();
			con.close();
	return result;
	  
  }
  public static book[] getBookInfo(String  bkName) throws SQLException, IOException {
	  String sql="call getBookInfo(?)";
	  Connection con=MySQLConnection.MysqlConnection();
		PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			pst.setString(1,bkName);
			ResultSet rs=pst.executeQuery();
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "数据库中没有这本书");
				return null;
			}
			int row=1;
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
  public static book getBookmodel_ID(borrowInfo model) throws SQLException, IOException {
	  book result=new book();
	  String sql="call gerBookInfo_ID(?)";
	  Connection con=MySQLConnection.MysqlConnection();
			PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				pst.setInt(1,model.getBkID());
				ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					result.setID(rs.getInt("bkID"));
					result.setCode(rs.getString("bkCode"));
					result.setName(rs.getString("bkName"));
					result.setAuthor(rs.getString("bkAuthor"));
					result.setPress(rs.getString("bkPress"));
					result.setDatePress(rs.getDate("bkDatePress"));
					result.setISBN(rs.getString("bkISBN"));
					result.setCatelog(rs.getString("bkCatalog"));
					result.setLanguage(rs.getInt("bkLanguage"));
					result.setPage(rs.getInt("bkPages"));
					result.setPrice(rs.getDouble("bkPrice"));
					result.setDateln(rs.getDate("bkDateIn"));
					result.setBrief(rs.getString("bkBrief"));
					result.setStatus(rs.getString("bkStatus"));
					Blob picture= rs.getBlob("bkCover");
	                InputStream in = picture.getBinaryStream();
	                String path="D:\\eclipse\\IDE\\BookManage\\bookPhoto\\"+result.getName()+"__"+result.getAuthor()+".jpg";
	                OutputStream out = new FileOutputStream(path);
	                byte[] buffer = new byte[1024];
	                int len = 0;
	                while((len = in.read(buffer)) != -1){
	                       out.write(buffer, 0, len);
	                }
	                 Image img = Toolkit.getDefaultToolkit().getImage(path);
	                 result.setPhoto(img);
				}
				
	return result;
	  
  }
  public static Object[][] toTable(borrowInfo model[]) {
	  int row=model.length;
	  Object result[][]=new Object[row][13];
	  for(int i=0;i<row;i++) {
		  int j=0;
		  result[i][j++]=model[i].getBorrowID();
		  result[i][j++]=model[i].getRdID();
		  result[i][j++]=model[i].getBkID();
		  result[i][j++]=model[i].getIdContinueTimes();
		  result[i][j++]=model[i].getIdDateOut();
		  result[i][j++]=model[i].getIdDateRetPlan();
		  result[i][j++]=model[i].getIdDateRetAct();
		  result[i][j++]=model[i].getIdOverDay(); 
		  result[i][j++]=model[i].getIdOverMoney();
		  result[i][j++]=model[i].getIdpPunishMoney();
		  result[i][j++]=model[i].getIsHasReturn();
		  result[i][j++]=model[i].getOperatorBorrow();
		  result[i][j++]=model[i].getOperatorReturn();
	  }
	return result;
  }
  public static void borowBook(reader readermodel,book bookmodel[],int row) throws SQLException, IOException {
		//判断读者的证件是否有效
		if(readermodel.getStatus().equals("1")==false) {
			JOptionPane.showMessageDialog(null,"读者的证件状态无效");
			return ;
		}
		//判断图书是否在馆
		if(!bookmodel[row].getStatus().equals("在馆")) {
			JOptionPane.showMessageDialog(null,"图书无法借阅");
			return ;
		}
		//判断可借阅数量和已借阅数量
		String info[]=BorrowManage.getCanLendDay(readermodel);
		if(readermodel.getLendQty()==Integer.parseInt(info[0])) {
			JOptionPane.showMessageDialog(null,"以达到最大借阅量");
			return ;
		}
		else {
			//设置图书、读者信息表
		bookmodel[row].setStatus("借出");
		int qty=readermodel.getLendQty()+1;
		readermodel.setLendQty(qty);
		//产生借阅信息
		borrowInfo model=new borrowInfo();
		model.setRdID(readermodel.getID());
		model.setBkID(bookmodel[row].getID());
		model.setIdDateOut(new Date());
		int day=Integer.parseInt(info[1]);
		Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。    
		cal.add(Calendar.DAY_OF_MONTH, +day);//取当前日期的后一天.  
        model.setIdDateRetPlan(cal.getTime());
        model.setOperatorBorrow(MainFrame.Name);
        
		String sql="call insertBorrowInfo(?,?,?,?,?)";
		Connection con=MySQLConnection.MysqlConnection();
		PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		pst.setInt(1, model.getRdID());
		pst.setInt(2, model.getBkID());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date =sdf.format(model.getIdDateOut());
		pst.setString(3, date);
		 date =sdf.format(model.getIdDateRetPlan());
		pst.setString(4, date);
		pst.setString(5,model.getOperatorBorrow());
	   pst.executeQuery();                      //数据库插入信息
		  pst.close();                  
		  con.close();                      //刷新视图
		  BorrowManage.getInstance().setReaderInfo(readermodel);//刷新读者栏
		 Object[][]info1=BookManageMethod.toTable(bookmodel);  //刷新图书信息栏
		 BorrowManage.getInstance().setBookTable(info1, o1);  
		 borrowmodel = getBorrowInfo(readermodel);                 //刷新借阅栏
		     info1=toTable(borrowmodel);                           //封装借阅信息
		     BorrowManage.getInstance().setBorrowTable(info1, o);       //刷新借阅栏视图
		}
  }
  
  public static void continueBorrow(borrowInfo borrowArray[],reader model,int row) throws SQLException {
	  if(borrowArray==null) {
			JOptionPane.showMessageDialog(null,"非法操作");  
			return ;
		  }
	  //判断是否选中行
	  if(row<=-1) {
          JOptionPane.showMessageDialog(null,"未选中行");
          return ;
	  }
	  //判断当前时间是否还在有效的借阅期
	  Date star=new Date();
	  Date end=borrowArray[row].getIdDateRetPlan();
	  Date nextDay=star;
	  int day=0;
	  while(nextDay.before(end)) {
		  Calendar cld = Calendar.getInstance();
	        cld.setTime(star);
	        cld.add(Calendar.DATE, 1);
	        star = cld.getTime();
	        //获得下一天日期字符串
	         nextDay = star; 
	        day++;  
	  }
	  if(day>0) {
		  
		  JOptionPane.showMessageDialog(null,"距离还书日期还有"+day+"天，无需续借");
		  return ;
	  }
	  if(day==0) {
		  //判断是否超期
		  Calendar cld = Calendar.getInstance();
	        cld.setTime(borrowArray[row].getIdDateRetPlan());
	        cld.add(Calendar.DATE, 1);
	        star = cld.getTime();
		  if(star.before(new Date())) {
			  JOptionPane.showMessageDialog(null, "你已经超期，请还书后再续借");
			  return ;
		  }
		  else {
			  //获得读者类型模型
			  typeModel=getReaderType(readermodel);
			 int canLendDay=typeModel.getCanLendDay();
			  int continueTime=typeModel.getContinueTimes();
			  System.out.println(continueTime);
			  System.out.println(borrowArray[row].getIdContinueTimes());
			  if(borrowArray[row].getIdContinueTimes()+1<=continueTime) {
				  borrowArray[row].setIdContinueTimes(borrowArray[row].getIdContinueTimes()+1);
					Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。    
					cal.add(Calendar.DAY_OF_MONTH, +canLendDay);//取当前日期的后一天.  
					 borrowArray[row].setIdDateOut(new Date());
					 borrowArray[row].setIdDateRetPlan(cal.getTime());
					 borrowArray[row].setOperatorBorrow(MainFrame.Name);
					  String sql="call continueBorrow(?,?,?,?)";                  //更新到数据库
					  Connection con=MySQLConnection.MysqlConnection();
						PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
						pst.setInt(1, borrowArray[row].getBorrowID());
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String s =sdf.format(borrowArray[row].getIdDateRetPlan());
						pst.setString(2,s);
						s=sdf.format(borrowArray[row].getIdDateOut());
						pst.setString(3,s);
						pst.setString(4, borrowArray[row].getOperatorBorrow());
						pst.executeQuery();       
					    con.close();
					    pst.close();
					    borrowmodel = getBorrowInfo(readermodel);                 //刷新借阅栏
					    Object info[][]=toTable(borrowmodel);                           //封装借阅信息
					     BorrowManage.getInstance().setBorrowTable(info, o);       //刷新借阅栏视图
			  }
			  else {
				  JOptionPane.showMessageDialog(null, "达到最大续借次数");
			  }
		  }
	  }
	 
  }
  public static  void returnBook(book bookmodel,reader readermodel, borrowInfo borrowArray[],int borrowRow,String adminName) throws SQLException {
	  if(borrowRow<=-1) {
		  JOptionPane.showMessageDialog(null, "未选中");
		  return ;
	  }
	 if(borrowArray==null) {
		 JOptionPane.showMessageDialog(null,"无效操作");
		 return ;
	 }
	 else {
		 //设置读者信息
		  readermodel.setLendQty(readermodel.getLendQty()-1);
		 //设置图书的信息
		 bookmodel.setStatus("在馆");
		 //设置借阅的信息
		 Date date=new Date();
		 int day=0;
		 Date star=borrowArray[borrowRow].getIdDateOut();
		 Date nextDay=star;
		 while(nextDay.before(date)){//当明天不在结束时间之前是终止循环
	        	Calendar cld = Calendar.getInstance();
	 	        cld.setTime(star);
	 	        cld.add(Calendar.DATE, 1);
	 	        star = cld.getTime();
	 	        //获得下一天日期
	 	         nextDay = star; 
	 	        day++;
	        }
		 //获得超期天数
		 typeModel=getReaderType(readermodel);
		 int normalDay=typeModel.getCanLendDay();//可借阅时间
		if(day<=normalDay) {
			//正常时间还书
			borrowArray[borrowRow].setIdDateRetAct(date);
			borrowArray[borrowRow].setIdpPunishMoney(0);
			borrowArray[borrowRow].setIdOverDay(0);
			borrowArray[borrowRow].setIsHasReturn(true);
			borrowArray[borrowRow].setIdOverMoney(0);
			borrowArray[borrowRow].setOperatorReturn(adminName);
		}
		else {
			//超期天数
			day=day-normalDay-1;
			//计算超期的钱数
			double rate=typeModel.getPunishRate();
			double money=rate*day;
			borrowArray[borrowRow].setIdDateRetAct(date);
			borrowArray[borrowRow].setIdpPunishMoney(money);
			borrowArray[borrowRow].setIdOverDay(day);
			borrowArray[borrowRow].setIdOverMoney(money);
			borrowArray[borrowRow].setIsHasReturn(true);
	     	borrowArray[borrowRow].setOperatorReturn(adminName);
	 }
		//更新数据到数据库
		String sql="call returnBook(?,?,?,?,?,?,?)";
		Connection con=MySQLConnection.MysqlConnection();
		PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		pst.setInt(1, readermodel.getID());
		pst.setInt(2, bookmodel.getID());
		pst.setInt(3, borrowArray[borrowRow].getBorrowID());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String s =sdf.format(borrowArray[borrowRow].getIdDateRetAct());
		pst.setString(4,s);
		pst.setString(5, borrowArray[borrowRow].getOperatorReturn());
		pst.setDouble(6, borrowArray[borrowRow].getIdpPunishMoney());
		pst.setDouble(7,borrowArray[borrowRow].getIdOverMoney());
	  pst.executeUpdate();
	  con.close();
	  pst.close();
		//刷新视图
		  BorrowManage.getInstance().setReaderInfo(readermodel);                //刷新读者信息栏
		     book array[]=new book[1];
		      array[0]=bookmodel;
		  	 Object[][]info1=BookManageMethod.toTable(array);       //刷新图书信息栏
			 BorrowManage.getInstance().setBookTable(info1, o1);  
			 info1=toTable(borrowmodel);                           //封装借阅信息
		     BorrowManage.getInstance().setBorrowTable(info1, o);       //刷新借阅栏视图
	 }  
  }
	@Override
	public void actionPerformed(ActionEvent e) {
		String source=e.getActionCommand();
		BorrowManage s=BorrowManage.getInstance();//获得页面对象
		Object info[][]=null;
		Object clear[][]=new Object[0][13];
		switch(source) {
		case "查找":
		int readerId=s.getReaderID();//获得页面输入的读者ID
		 s.setBorrowTable(clear, o);                     //清空表格
		if(readerId!=0) {
			reader model=queryReaderInfo(readerId);
			if(model!=null) {
			readermodel= model;
			JOptionPane.showMessageDialog(null, s.setReaderInfo(readermodel));
			 borrowInfo[] model1;
			try {
				model1 = getBorrowInfo(readermodel);
				if(model1!=null) {
					 borrowmodel=model1;
					// borrowmodel= getBorrowInfo(readermodel);
					 info=toTable(borrowmodel);//封装借阅信息
					      s.setBorrowTable(info, o);           //刷新视图
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 
		}
		}
		break;
		case "查找 ":
			String Bookinfo=s.getBookName();
			 s.setBookTable(clear,o);                     //清空表格
			if(Bookinfo!=null) {
				try {
					book model[]=getBookInfo(Bookinfo);
					if(model!=null) {
					bookmodel=model;//获得图书信息
					  info=BookManageMethod.toTable(bookmodel);  //封装成表格
					  s.setBookTable(info, o1);           //刷新视图
					}
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case "借书":
			if(readermodel==null||bookmodel==null) {
				JOptionPane.showMessageDialog(null, "非法操作");
			}
			else {
				//获得选中的图书
				if(s.table_book!=null) {
			  int row=s.table_book.getSelectedRow(); 
			  if(row!=-1) {
				  try {
					borowBook(readermodel,bookmodel,row);
			     	} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				   }
				  }
			  else {
				  JOptionPane.showMessageDialog(null, "未选择图书");
			  }
			
	       }
		
			}
			break;
		case "还书":
			if(readermodel==null||borrowmodel==null)
			{
				JOptionPane.showMessageDialog(null, "非法操作");
				break;
			}
			if(s.table==null) {
				JOptionPane.showMessageDialog(null, "非法操作");
				break;
			}
			else {
				int row=s.table.getSelectedRow();
				try {
					if(row==-1) {
						JOptionPane.showMessageDialog(null, "非法操作");
						break;
					}
					book model=getBookmodel_ID(borrowmodel[row]);
					if(borrowmodel[row].getIsHasReturn()) {
						JOptionPane.showMessageDialog(null, "该图书已经还书");
						break;
					}
					returnBook(model,readermodel,borrowmodel,row,MainFrame.Name);
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case "续借":
			if(s.table==null) {
				JOptionPane.showMessageDialog(null, "非法操作");
				break;
			}
			else {
				int row=s.table.getSelectedRow();
				try {
					continueBorrow(borrowmodel,readermodel,row);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			break;

       }
   }
}