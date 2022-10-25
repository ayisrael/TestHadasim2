package serverSide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Vaccination {
	
	private long clientID;
	private Date dateVaccination;
	private int manufacturerID;
	private String manufacturerName;

	
	public long getClientID() {
		return clientID;
	}
	public int getManufacturerID() {
		return manufacturerID;
	}
	
	public String getDateVaccination() {
		String date = new SimpleDateFormat("yyyy/MM/dd").format(dateVaccination);

		return date;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setClientID(long clientID) {
		this.clientID = clientID;
	}
	public void setDateVaccination(String dateVaccination) {
		try {		
			this.dateVaccination =new SimpleDateFormat("yyyy/MM/dd").parse(dateVaccination); 
}
		 catch(Exception e) {
		    }
		//this.dateVaccination = dateVaccination;
	}

	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}


}
