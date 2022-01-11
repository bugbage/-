package Controller;

import java.awt.FileDialog;
import java.awt.Image;
import  java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
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
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.DuplicateFormatFlagsException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ReaderMS.ReaderHomepage;
import ReaderMS.ReaderInfo;
import ReaderMS.chose;
import ReaderMS.photo;
import ReaderMS.text;
import model.reader;
import model.readerType;

public class ReaderManageMethod implements MouseListener ,ActionListener {

	public static ReaderManageMethod s=new ReaderManageMethod();
	public ReaderManageMethod()
	{	
	}
	public static ReaderManageMethod getInstance() {
		return s;
	}
	private static reader model[];
	static ReaderHomepage frames;
	public static String address="C:\\Users\\86185\\Desktop\\1.jpg.jpg";
	Object o[]= {"编号","类别","姓名","性别","单位","电话号码","电子邮箱","登记日期","证件状态","读者密码","管理角色","已借书数量"};
	public static void main(String args[]) {
		frames=new ReaderHomepage();
			frames.setVisible(true);
	}
	
	
	public  static reader[] getModelArray(String[] viewInfo) throws SQLException, IOException {
		String type=viewInfo[0];
		String dept=viewInfo[1];
		String name=viewInfo[2];
		ResultSet rs = null;
		String sql=null;
		int mysql_type=0;
		int row=0;
		readerType typemodel[]=ReaderTypeManageMethod.queryReaderType();
		int readertype[]=new int[typemodel.length];
		String[] typeName=new String[typemodel.length];
	   for(int i=0;i<typemodel.length;i++) {
		readertype[i]=typemodel[i].getType();
		typeName[i]=typemodel[i].getTypeName();
		if(type.equals(typeName[i]))
		 {
	      mysql_type=readertype[i];
	      break;
	       	}
	   }
	    if(name.equals("")==false) {
	   sql="call query_search("+"'"+dept+"',"+"'"+name+"',"+mysql_type+")";
	     JOptionPane.showMessageDialog(null,"不为空执行");	
	    }
	    else
	    {
	    	JOptionPane.showMessageDialog(null,"执行");
	    	 sql="call query_search_nullName('"+dept+"',"+mysql_type+")";
	    }
	    Statement query=MySQLConnection.createQuery();
			rs = query.executeQuery(sql);
			 while(rs.next()) {
				 row++;//获得行的数目
			 }
			// Object o[]= {"编号","类别","姓名","性别","单位","电话号码","电子邮箱","登记日期","证件状态","读者密码","管理角色"};
			 reader model[]=new reader[row];//定义模型数组
			 rs.first();//游标回到第一行；
			 for(int i=0;i<row;i++) {
				 model[i]=new reader();
				 model[i].setID(rs.getInt("rdID"));     
				 model[i].setType(rs.getInt("rdType"));
				 model[i].setName(rs.getString("rdName"));
				 model[i].setSex(rs.getString("rdSex"));
				 model[i].setDept(rs.getString("rdDept"));
				 model[i].setPhone(rs.getString("rdphone"));
				 model[i].setEmaill(rs.getString("rdEmail"));
				 model[i].setStatus(rs.getString("rdStatus"));
				 model[i].setPwd(rs.getString("rdPwd"));
				 model[i].setRoles(rs.getInt("rdAdminRoles"));
				 model[i].setLendQty(rs.getInt("rdBorrowQty"));
				 model[i].setDate(rs.getDate("rdDateReg"));
					Blob picture= rs.getBlob("rdPhoto");
                    InputStream in = picture.getBinaryStream();
                    String path="D:\\eclipse\\IDE\\BookManage\\userPhoto\\"+model[i].getName()+".jpg";
                    OutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len = in.read(buffer)) != -1){
                           out.write(buffer, 0, len);
                    }
                     Image img = Toolkit.getDefaultToolkit().getImage(path);
                     model[i].setPhoto(img);
				 rs.next();
                    }
			 query.close();
		return model;	
	}
	public static Object[][] showTable(reader model[]) throws SQLException {
		int row=model.length;
		Object info[][] = new Object[row][12];
		if(model!=null) {
		for (int i = 0; i < row; i++) {
			int j=0;
			info[i][j++] =  model[i].getID();
			info[i][j++] = getType(model[i]);
			info[i][j++] =  model[i].getName();
			info[i][j++] =  model[i].getSex();
			info[i][j++] =  model[i].getDept();
			info[i][j++] =  model[i].getPhone();
			info[i][j++] =  model[i].getEmaill();
			info[i][j++] = model[i].getDate();
			info[i][j++] = getStatus(model[i]);
			info[i][j++] =  model[i].getPwd();
			info[i][j++] =  getRolse(model[i]);
			info[i][j++] =  model[i].getLendQty();
		}
		}
		return info;
	}
	
	public  static String getType(reader readerModel) throws SQLException {
		readerType model[]=ReaderTypeManageMethod.queryReaderType();
		int type[]=new int[model.length];
		String Name=new String();
		String[] typeName=new String[model.length];
	   for(int i=0;i<model.length;i++) {
		type[i]=model[i].getType();
		typeName[i]=model[i].getTypeName();
		if(readerModel.getType()==type[i])
		 {
	      Name=typeName[i];
	      break;
	       	}
	   }
		return Name;	
	}
	 public static String getStatus(reader readerModel) {
		 String result=new String();
		 if(readerModel!=null) {
			try { int status=Integer.parseInt(readerModel.getStatus());
			 switch(status) {
			 case 0: result="注销";break;
			 case 1: result="有效";break;
			 case 2:result="挂失";break;
			 default :result ="未知身份";break;
			 }
			}
			catch(Exception e) {
				result="未知身份";
			}
		 }
		return result; 
	 }
	 public static String getRolse(reader readerModel ) {
		 String result=new String();
		 if(readerModel!=null) {
			 int type=readerModel.getRoles();
			 switch(type) {
			 case 0:result = "读者";break;
				case 1:result = "借书证管理员";break;
				case 4:result = "借阅管理员";break;
				case 8:result = "系统管理员";	break;
				default:result = "未知身份";	break; 
			 }
		 }
		return result;	 
	 }
	
	
	public static boolean updateStatus(int ID,String status) {
		String sql="select updatestatus("+ID+",'"+status+"')";
		Statement query=MySQLConnection.createQuery();
		try {
			query.executeQuery(sql);
			query.close();
			JOptionPane.showMessageDialog(null, "更新成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
	
	public static boolean insertReader(reader model) throws SQLException, IOException {
		boolean status=false;
		String sql="call insertReader(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		  Connection con=MySQLConnection.MysqlConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, model.getID());
			pst.setString(2, model.getName());
			pst.setString(3, model.getSex());
			pst.setInt(4,model.getType());
	        pst.setString(5, model.getDept());
			pst.setString(6, model.getPhone());
			pst.setString(7, model.getEmaill());
		    pst.setString(8, model.getStatus());
		  pst.setInt(9, model.getLendQty());
		   pst.setString(10, model.getPwd());
		  pst.setInt(11, model.getRoles());
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(model.getDate());
			pst.setString(12, date);
			 InputStream in = new FileInputStream(address);
			pst.setBlob(13, in);
			 status=pst.execute();
			 // in.close();
		return status;
		
	}
    public static boolean updateModel(reader model) throws SQLException, FileNotFoundException {
    boolean status=false;
    	String sql="call updateReader(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		  Connection con=MySQLConnection.MysqlConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, model.getID());
			pst.setString(2, model.getName());
			pst.setString(3, model.getSex());
			pst.setInt(4,model.getType());
	        pst.setString(5, model.getDept());
			pst.setString(6, model.getPhone());
			pst.setString(7, model.getEmaill());
		    pst.setString(8, model.getStatus());
		  pst.setInt(9, model.getLendQty());
		   pst.setString(10, model.getPwd());
		  pst.setInt(11, model.getRoles());
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(model.getDate());
			pst.setString(12, date);
			 InputStream in = new FileInputStream(address);
			pst.setBlob(13, in);
			 status=pst.execute();
    	return status;
    }
	//模型空判断
    public static  boolean nullEstimate(reader model[]) {
    	boolean status =model==null;
    	if(status) {
    		JOptionPane.showMessageDialog(null, "非法操作！","警告",JOptionPane.WARNING_MESSAGE);
    	}
		return status;	
    }
    
    @Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==chose.getChose().label_search) {	
 		Object info[][];
		try {
			String []viewInfo=chose.getInfo();//获取视图信息	
			model=getModelArray(viewInfo);//封装成模型对象
			info = showTable(model);//输出为表格
			 text.setTable(info, o); 
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
		
	}
		else if(e.getSource()==chose.label_excel) {
			FileDialog fd;
			if(model!=null)
			{
				fd = new FileDialog(new JFrame(),"保存读者信息", FileDialog.SAVE);
			      fd.setVisible(true);  
			     String stringfile = fd.getDirectory()+fd.getFile()+".xlsx";  
		         try {
		        PaintMethod.exportTable(text.table, new File(stringfile));
		         } catch (IOException ex) {
		             ex.printStackTrace();
		         }
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		ReaderInfo s=ReaderInfo.getReaderInfo();//获得页面对象
		int row=0;
		if(text.getText().table!=null) {
		 row=text.getText().table.getSelectedRow();//获得选中的对象
	       row=row>-1?row:0;                      //所选中的行号，默认为第一行
		}
	 	  Object info[][] = null;  //表格中的信息 
	 	  
		String source=((ActionEvent) e).getActionCommand();//获取事件源
		switch(source) {                                  //判断事件源
	case "挂失":	
		if(nullEstimate(model)==false) {
		if(model[row].getStatus().equals("0")) {
			JOptionPane.showMessageDialog(null,"该读者已经注销");
		}
	else if(model[row].getStatus().equals("2")) {
			JOptionPane.showMessageDialog(null,"状态已经为挂失");
		}
		else {
		 model[row].setStatus("2");                  //设置无效
	     updateStatus(model[row].getID(),"挂失");  //数据变更传入数据库    
	try {
		   info = showTable(model);                //刷新视图
		    text.setTable(info, o);
	            } catch (SQLException e1) {
		           e1.printStackTrace();
	              }  	
		    }
		      }
		break;	
		
	case "注销":	
		if(nullEstimate(model)==false) {
		if(model[row].getStatus().equals("0")) {
			JOptionPane.showMessageDialog(null,"该读者已经注销");
		}
		else if(model[row].getLendQty()>0) {
			JOptionPane.showMessageDialog(null, "读者不能进行注销，有未归还图书");
		}
		else {
		model[row].setStatus("0");//设置注销
		updateStatus(model[row].getID(),"注销");//数据变更传入数据库
			try {
				info = showTable(model);//刷新视图
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			text.setTable(info, o);
		}	
		  }
		break;
		
	case "解除挂失":
		if(nullEstimate(model)==false) {
		if(model[row].getStatus().equals("0")) {
			JOptionPane.showMessageDialog(null,"该读者已经注销");
		}
	else if(model[row].getStatus().equals("1")) {
			JOptionPane.showMessageDialog(null,"状态已经为有效");
		}
		else {
		model[row].setStatus("1");	//设置有效
		updateStatus(model[row].getID(),"有效");//数据变更传入数据库	
       try {
		info=showTable(model);//刷新视图
		text.setTable(info, o);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		}
		 }
		break;
		
	case "变更借书证":
		if(nullEstimate(model)==false) {
	          try {
				s.modelToView(model[row]);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}                                //传入获取的模型对象
	      JOptionPane.showMessageDialog(null,"执行");
		}
		break;
		
	case "确认变更":
			try {
				 reader newModel=s.viewTModel();
				if(newModel!=null) {
					model[row]=newModel;
					updateModel(model[row]);    //预留更新数据库
	          info=showTable(model);//刷新视图
	          text.setTable(info, o);
               }
			} catch (FileNotFoundException | SQLException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		break;
		
	case "上传照片":
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
		
	case "确认办证":
			try {  
				reader newModel=s.viewTModel();//获得模型
				if(newModel!=null) {
					insertReader(newModel);
					JOptionPane.showMessageDialog(null, "插入成功");
				}
				else {
					JOptionPane.showMessageDialog(null, "非法操作！","警告",JOptionPane.WARNING_MESSAGE);
				}
					
			} catch (IOException | SQLException | ParseException e1) {
				JOptionPane.showMessageDialog(null, "非法操作！","警告",JOptionPane.WARNING_MESSAGE); 
				if(e1 instanceof DuplicateFormatFlagsException){
				}	
			}
		break;
	case   "清空":
		s.clearView();
		break;
	case "办理借书证":
			try {
				s.generateUserInfo();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		break;
		}
	}
  
}
