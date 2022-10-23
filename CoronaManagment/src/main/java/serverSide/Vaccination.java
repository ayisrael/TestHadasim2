package serverSide;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.sql.Date;

public class Vaccination {
	
	private long clientID;
	private Date dateVaccination;
	private int manufacturerID;
	private String manufacturerName;
	//private Date date;

	
	public long getClientID() {
		return clientID;
	}
	public void setClientID(long clientID) {
		this.clientID = clientID;
	}
	
	public String getDateVaccination() {
		String date = new SimpleDateFormat("yyyy/MM/dd").format(dateVaccination);

		return date;
	}
	public void setDateVaccination(String dateVaccination) {
		try {		
			this.dateVaccination =new SimpleDateFormat("yyyy/MM/dd").parse(dateVaccination); 
}
		 catch(Exception e) {
		    }
		//this.dateVaccination = dateVaccination;
	}
	public int getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	
	



}
