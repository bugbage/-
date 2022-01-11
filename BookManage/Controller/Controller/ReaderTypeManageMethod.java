package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BookManage.BookManage;
import ReaderMS.text;
import model.readerType;
import readerManage.ReaderTypeManage;
import readerManage.borrow;

import java.awt.event.*;

public class ReaderTypeManageMethod implements ActionListener,ListSelectionListener {
public static readerType  model[]=null;
public static ReaderTypeManage frame;
static Object o[]= {"读者类别","类别名称","可借阅数量","可借阅天数","可续借的次数","罚款率","证书有效期"};
	//读者类别管理
public static void main(String args[]) {
	    frame= ReaderTypeManage.getInstance();
		frame.setVisible(true);
}
		public static readerType[] queryReaderType() throws SQLException {
			Statement query=MySQLConnection.createQuery();
			String sql="call query_readertype()";
			int row=0;
			ResultSet rs=query.executeQuery(sql);
			while(rs.next())
			{
				row++;
			}
			rs.first();
			readerType[] model=new readerType[row];
			for(int i=0;i<row;i++)
			{
				model[i]=new readerType();
				model[i].setType(rs.getInt(1));
				model[i].setTypeName(rs.getString(2));
				model[i].setCanLendQty(rs.getInt(3));
				model[i].setCanLendDay(rs.getInt(4));
				model[i].setContinueTimes(rs.getInt(5));
				model[i].setPunishRate(rs.getDouble(6));
				model[i].setDateVaild(rs.getInt(7));
				rs.next();
			}		
			return model;	
		}
		public static Object[][]  ModelArrayToView(readerType model[]){
			 int row=model.length;
			Object info[][]=new Object[row][7];
			for(int i=0;i<row;i++) {
				int j=0;
				info[i][j++]=(Object)model[i].getType();
				info[i][j++]=(Object)model[i].getTypeName();
				info[i][j++]=(Object)model[i].getCanLendQty();
				info[i][j++]=(Object)model[i].getCanLendDay();
				info[i][j++]=(Object)model[i].getContinueTimes();
				info[i][j++]=(Object)model[i].getPunishRate();
				info[i][j++]=(Object)model[i].getDateVaild();
			}
			return info;
}
		public static Object[]  ModelToView(readerType model){
			Object info[]=new Object[7];
				int j=0;
				info[j++]=(Object)model.getType();
				info[j++]=(Object)model.getTypeName();
				info[j++]=(Object)model.getCanLendQty();
				info[j++]=(Object)model.getCanLendDay();
				info[j++]=(Object)model.getContinueTimes();
				info[j++]=(Object)model.getPunishRate();
				info[j++]=(Object)model.getDateVaild();
		      	return info;
		}
		
		public static boolean compare(readerType model,readerType[] readerArray) {
			boolean status=true;
			for(int i=0;i<readerArray.length;i++) {
				 if(model.getType()==readerArray[i].getType()||model.getTypeName().equals(readerArray[i].getTypeName())) {//判断读者类别和类别名不能重复
					    borrow.getInstance().setView(new readerType());
					    JOptionPane.showMessageDialog(frame, "类别和类别名不能重复","警告",JOptionPane.WARNING_MESSAGE);
					  status=false;
					    break;
			        }
			}
			return status;	
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String source =e.getActionCommand();
			Object info[][]=null;
			int row=0;
			 if(frame.table!=null) {            //所选中的行号，默认为第一行
				row =frame.table.getSelectedRow();//获得选中的行
				     row= row>-1?row:0; 
			 }
			switch(source) {
		 case "查询":
       	  try {
				model=queryReaderType();
				info=ModelArrayToView(model);
				ReaderTypeManage.setTabel(info,o);	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
       	  break;
		 case"修改":
			 if(model!=null) {
				try {
				deleteReaderType(model[row]);
				readerType newModel=new readerType();
				if(borrow.getInstance().viewToModel()!=null) {//不为空是前提
					newModel=borrow.getInstance().viewToModel(); //判断类别号是否更改
					if(newModel.getType()==model[row].getType()) {
						model[row]=borrow.getInstance().viewToModel();
						insertReaderType(model[row]);
						 model=ReaderTypeManageMethod.queryReaderType();
						 info=ReaderTypeManageMethod.ModelArrayToView(model);//刷新视图
							ReaderTypeManage.setTabel(info,o);
					}
					else {
						  JOptionPane.showMessageDialog(frame, "非法操作，更改类别名","警告",JOptionPane.WARNING_MESSAGE);	
					}
				}	 
				} catch (SQLException e2) {
					e2.printStackTrace();
				}  
			 
			     }
			 break;
		 case "添加":
			 try {
			 if(borrow.getInstance().viewToModel()!=null) {
				 readerType add=borrow.getInstance().viewToModel();//获得增加的模型
				 model=queryReaderType();
			 readerType newModelArray[]=new readerType[model.length+1];
			 if( compare(add,model)) {
			 for(int i=0;i<model.length;i++)
			 {
				newModelArray[i]=new readerType();
				 newModelArray[i]=model[i]; 
			  }	
	             newModelArray[model.length]=add;
	             model=newModelArray;
				 
				      insertReaderType(add); //更新数据库	                    
		    }
				 }
			 } catch (SQLException e1) {
					e1.printStackTrace();
				}                            
				 info=ReaderTypeManageMethod.ModelArrayToView(model);//刷新视图	
			  ReaderTypeManage.setTabel(info,o); 
			  
		 break;
		 case "删除":
				try {
					deleteReaderType(model[row]);
					System.out.println(row);
					model=ReaderTypeManageMethod.queryReaderType();
					info=ReaderTypeManageMethod.ModelArrayToView(model);
					ReaderTypeManage.setTabel(info,o);      //刷新视图
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	 
				 
		 break;
		}
			}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			//获得选中的行
			int row =0;//默认为第一行
			 row=frame.table.getSelectedRow();//选中行
			 row=row>-1?row:0;
			 borrow.getInstance().setView(model[row]);
			// ReaderTypeManage.setTabel(info,o);
		}	
		
public  void insertReaderType(readerType model) throws SQLException {
	String sql="insert into tb_readerType values(?,?,?,?,?,?,?)";
	  Connection con=MySQLConnection.MysqlConnection();
	PreparedStatement pst = con.prepareStatement(sql);
	pst.setInt(1, model.getType());
	pst.setString(2, model.getTypeName());
	pst.setInt(3, model.getCanLendQty());
	pst.setInt(4, model.getCanLendDay());
	pst.setInt(5, model.getContinueTimes());
	pst.setDouble(6, model.getPunishRate());
	pst.setInt(7, model.getDateVaild());
    pst.executeUpdate();
    con.close();
   pst.close();
}
public  void deleteReaderType(readerType model) throws SQLException {
	  String sql="delete from tb_readerType where rdType=?";
	  Connection con=MySQLConnection.MysqlConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, model.getType());
		pst.executeUpdate();
		con.close();
		pst.close();
}
}