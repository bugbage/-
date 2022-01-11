package model;

import java.util.Date;

import Controller.MySQLConnection;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class reader {
	private int ID;
	private String name;
	private String pwd;
	private String sex;
	private int lendQty;
	private String status;
	private int roles;
	private int type;
	private String dept;
	private String phone;
	private String emaill;
	private Date date;
	private Image photo;
		public reader() {
			
	}

	
	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public int getLendQty() {
		return lendQty;
	}



	public void setLendQty(int lendQty) {
		this.lendQty = lendQty;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String string) {
		this.status = string;
	}



	public int getRoles() {
		return roles;
	}

	public void setRoles(int roles) {
		this.roles = roles;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}



	public String getDept() {
		return dept;
	}



	public void setDept(String dept) {
		this.dept = dept;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmaill() {
		return emaill;
	}



	public void setEmaill(String emaill) {
		this.emaill = emaill;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Image getPhoto() {
		return photo;
	}



	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	
public boolean compare(reader model) {
	boolean status=true;
	 if(this.ID==model.ID)
	 {
	status=false; 
	 }
	return status;	
}




	

}