package model;

import java.util.*;

public class borrowInfo {
private  int borrowID;
private int rdID;
private int bkID;
private int IdContinueTimes ;
private Date IdDateOut;
private Date IdDateRetPlan;
private Date IdDateRetAct;
private  int IdOverDay;
private double IdOverMoney;
private double IdpPunishMoney;
private boolean IsHasReturn;
private String OperatorBorrow;
private String OperatorReturn;

public int getBorrowID() {
	return borrowID;
}
public void setBorrowID(int borrowID) {
	this.borrowID = borrowID;
}
public int getRdID() {
	return rdID;
}
public void setRdID(int rdID) {
	this.rdID = rdID;
}
public int getBkID() {
	return bkID;
}
public void setBkID(int bkID) {
	this.bkID = bkID;
}
public int getIdContinueTimes() {
	return IdContinueTimes;
}
public void setIdContinueTimes(int idContinueTimes) {
	IdContinueTimes = idContinueTimes;
}
public Date getIdDateOut() {
	return IdDateOut;
}
public void setIdDateOut(Date idDateOut) {
	IdDateOut = idDateOut;
}
public Date getIdDateRetPlan() {
	return IdDateRetPlan;
}
public void setIdDateRetPlan(Date idDateRetPlan) {
	IdDateRetPlan = idDateRetPlan;
}
public Date getIdDateRetAct() {
	return IdDateRetAct;
}
public void setIdDateRetAct(Date idDateRetAct) {
	IdDateRetAct = idDateRetAct;
}
public int getIdOverDay() {
	return IdOverDay;
}
public void setIdOverDay(int idOverDay) {
	IdOverDay = idOverDay;
}
public double getIdOverMoney() {
	return IdOverMoney;
}
public void setIdOverMoney(double idOverMoney) {
	IdOverMoney = idOverMoney;
}
public double getIdpPunishMoney() {
	return IdpPunishMoney;
}
public void setIdpPunishMoney(double idpPunishMoney) {
	IdpPunishMoney = idpPunishMoney;
}
public boolean getIsHasReturn() {
	return IsHasReturn;
}
public void setIsHasReturn(boolean isHasReturn) {
	IsHasReturn = isHasReturn;
}
public String getOperatorBorrow() {
	return OperatorBorrow;
}
public void setOperatorBorrow(String operatorBorrow) {
	OperatorBorrow = operatorBorrow;
}
public String getOperatorReturn() {
	return OperatorReturn;
}
public void setOperatorReturn(String operatorReturn) {
	OperatorReturn = operatorReturn;
}

}
