package model;

public class readerType {
 
	private int Type;
	private String TypeName;
	private int CanLendQty;
	private int CanLendDay;
	private int ContinueTimes;
	private double PunishRate;
	private int DateVaild;
	
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	public int getCanLendQty() {
		return CanLendQty;
	}
	public void setCanLendQty(int canLendQty) {
		CanLendQty = canLendQty;
	}
	public int getCanLendDay() {
		return CanLendDay;
	}
	public void setCanLendDay(int canLendDay) {
		CanLendDay = canLendDay;
	}
	public int getContinueTimes() {
		return ContinueTimes;
	}
	public void setContinueTimes(int continueTimes) {
		ContinueTimes = continueTimes;
	}
	public double getPunishRate() {
		return PunishRate;
	}
	public void setPunishRate(double punishRate) {
		PunishRate = punishRate;
	}
	public int getDateVaild() {
		return DateVaild;
	}
	public void setDateVaild(int dateVaild) {
		DateVaild = dateVaild;
	}
}
	