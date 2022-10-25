
package serverSide;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * All Recovering details 
 */
public class Recovering {
	private long clientID;
	private Date positiveTestDate;
	private Date recoveringDate;	
	

	
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

	
	public boolean CheckDates(String positiveDate, String recoveringDate, long clientId) {
		try {		
			Date recovering =new SimpleDateFormat("yyyy/MM/dd").parse(recoveringDate); 			
			//The range of dates that can be entered in the 'recoveringDate' field 
			Date positive1= new SimpleDateFormat("yyyy/MM/dd").parse(positiveDate); 
			Date minimumDate = positive1;
			minimumDate.setDate(minimumDate.getDate() + 5); 
			Date positive2= new SimpleDateFormat("yyyy/MM/dd").parse(positiveDate);
			 Date maximumDate = positive2;
			 maximumDate.setDate(maximumDate.getDate() + 30); 
			if( recovering.compareTo(minimumDate) < 0){		
				return false;
			}
			if( maximumDate.compareTo(recovering) < 0){		
				return false;
			}
		    //Checking whether the client did not recover less than 90 days ago from the date of positive test that entered
			CoronaManager cm = new CoronaManager();
			List<Recovering> allRecovering = new ArrayList<Recovering>();//List of dates of the client's illness
			allRecovering = cm.getAllRecoveringByClientId(clientId);//get all the client's illness dates 
			for (int i = 0; i < allRecovering.size(); i++) {
				Date notBefore= new SimpleDateFormat("yyyy/MM/dd").parse(allRecovering.get(i).getRecoveringDate()); 				
				notBefore.setDate(notBefore.getDate() + 90); 						
				if(recovering.compareTo(notBefore) < 0 ) {					
					return false;
				}
				
			}
			}
		
		 catch(Exception e) {
			 e.getMessage();
		    }
		return true;
	}
	


}
