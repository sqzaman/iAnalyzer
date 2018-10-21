package mum.edu.cs523;

public class BtcRateDate {
	
	String updated;
	String updatedISO;
	String updateduk;
	public BtcRateDate(){		
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdatedISO() {
		return updatedISO;
	}
	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}
	public String getUpdateduk() {
		return updateduk;
	}
	public void setUpdateduk(String updateduk) {
		this.updateduk = updateduk;
	}
	@Override
	public String toString() {
		return "updated:" + this.updated + ", updatedISO:" + this.updatedISO
				+ ", updateduk:" + this.updateduk ;
	}
	

}
