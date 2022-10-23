package serverSide;

import java.sql.Date;

//import java.sql.Date;

public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnection.getConnection();// open connection
		CoronaManager man = new  CoronaManager();
		 man.getAllClients();
		 //man.getAllVaccinationByClientId(12345);
		 //man.removeClient(12349);
		 //man.AddClient(12002, "laainman", "Ari", null, null, 0, 0, 0);
		//String str="2022-03-14";  
		//Date date=Date.valueOf(str);
		//man.AddDateOfVaccination(12345 ,date, 1212);
		//man.UpdateClient(12001, null, null, null, null, 0, 0, 0);
		 //man.AddManufacturer(1010, "covid-19");
		 //String str="2022-09-10"; 
		 //Date date=Date.valueOf(str);
		 //String stri="2022-09-15"; 
		 //Date datei=Date.valueOf(stri);
		 //man.updateRecovering(12001,date , datei);
		 //Vaccination vac= new Vaccination();
		 //vac.setdate("2022/09/09");
		 //System.out.println()
	}

}
