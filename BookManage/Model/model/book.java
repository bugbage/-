package model;

import java.util.Date;

import java.awt.*;

public class book {
	private int ID;
	private String Code;
	private String name;
	private String Author;
	private String Press;
	private Date DatePress;
	private String ISBN;
	private String Catelog;
	private int Language;
	private int Page;
	private double Price;
	private Date Dateln;
	private String Brief;
	private String Status;
	private Image photo;
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getPress() {
		return Press;
	}
	public void setPress(String press) {
		Press = press;
	}
	public Date getDatePress() {
		return DatePress;
	}
	public void setDatePress(Date date) {
		this.DatePress = date;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getCatelog() {
		return Catelog;
	}
	public void setCatelog(String catelog) {
		Catelog = catelog;
	}
	public int getLanguage() {
		return Language;
	}
	public void setLanguage(int language) {
		Language = language;
	}
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public Date getDateln() {
		return Dateln;
	}
	public void setDateln(Date dateln) {
		Dateln = dateln;
	}
	public String getBrief() {
		return Brief;
	}
	public void setBrief(String brief) {
		Brief = brief;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public static Object[][] toTable(book model[]){
		//获取对象的个数
		int row =model.length;
		//创建tbale显示内容
		Object result[][]=new Object[row][14];
		//开始封装
		for(int i=0;i<row;i++) {
			 int j=0;
				result[i][j++]=(Object)model[i].ID;
				result[i][j++]=(Object)model[i].Code;
				result[i][j++]=(Object)model[i].name;
				result[i][j++]=(Object)model[i].Author;
				result[i][j++]=(Object)model[i].Press;
				result[i][j++]=(Object)model[i].DatePress;
				result[i][j++]=(Object)model[i].ISBN;
				result[i][j++]=(Object)model[i].Catelog;
				result[i][j++]=(Object)model[i].Language;
				result[i][j++]=(Object)model[i].Page;
				result[i][j++]=(Object)model[i].Price;
				result[i][j++]=(Object)model[i].Dateln;
				result[i][j++]=(Object)model[i].Brief;
				result[i][j++]=(Object)model[i].Status;
		}

		return result;	
	}
}
