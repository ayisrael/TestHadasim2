
package serverSide;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * All Recovering details 
 */
public class Recovering {
	private long clientID;
	private Date positiveTestDate;
	private Date recoveringDate;	
	
	public boolean CheckDates(String positiveDate, String recoveringDate) {
		try {		
			Date positive= new SimpleDateFormat("yyyy/MM/dd").parse(positiveDate); 
			Date recovering =new SimpleDateFormat("yyyy/MM/dd").parse(recoveringDate); 
			if(positive.compareTo(recovering) < 0){
				return true;		
			}
			}
		 catch(Exception e) {
			 e.getMessage();
		    }
		return false;
	}
	
	public long getClientID() {
		return clientID;
	}


	public String getPositiveTestDate() {
		String date = new SimpleDateFormat("yyyy/MM/dd").format(positiveTestDate);

		return date;
		//return positiveTestDate;
	}


	public String getRecoveringDate() {
		String date = new SimpleDateFormat("yyyy/MM/dd").format(recoveringDate);

		return date;
		//return recoveringDate;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}

	public void setPositiveTestDate(String positiveTestDate) {
		try {		
			this.positiveTestDate =new SimpleDateFormat("yyyy/MM/dd").parse(positiveTestDate); 
			}
		 catch(Exception e) {
		    }
		//this.positiveTestDate = positiveTestDate;
	}

	public void setRecoveringDate(String recoveringDate) {
		try {		
			this.recoveringDate =new SimpleDateFormat("yyyy/MM/dd").parse(recoveringDate); 
			}
		 catch(Exception e) {
		    }
		//this.recoveringDate = recoveringDate;
	}

	

	


}
