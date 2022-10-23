package serverSide;

//import java.sql.PreparedStatement;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CoronaManager {
	
	public List<Client> getAllClients(){ // get all client from DB
		List<Client> allClient = new ArrayList<Client>();
		
	    try{
	    	final String SELECT_ALL_CLIENT = "SELECT * FROM corona_db.client";
	    	Connection con = DatabaseConnection.getConnection();
	    	PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_CLIENT);//connect to DB
	    	ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	 while (resultSet.next()) {

	                long clientID = resultSet.getLong("clientID");
	                String lastName = resultSet.getString("lastName");
	                String firstName = resultSet.getString("firstName");
	                String city = resultSet.getString("city");
	                String street = resultSet.getString("street");
	                int homeNum = resultSet.getInt("homeNum");
	                String phone = resultSet.getString("phone");
	                String mobilePhone = resultSet.getString("mobilePhone");
	                String dateOfBirth = resultSet.getString("dateOfBirth");

	                
	                
	                Client client = new Client();
	                client.setClientID(clientID); 
	                client.setLastName(lastName);
	                client.setFirstName(firstName);
	                client.setCity(city);
	                client.setStreet(street);
	                client.setHomeNum(homeNum);
	                client.setPhone(phone);
	                client.setMobilePhone(mobilePhone);
	                client.setDateOfBirth(dateOfBirth);
	                allClient.add(client);//add the client
	                
	                // Timestamp -> LocalDateTime
	                //obj.setCreatedDate(createdDate.toLocalDateTime());
	            
	                
	                //System.out.println(obj);
	            }
	    	 
	    }
	    catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }


		return allClient;
	};
	
	public List<Vaccination> getAllVaccinationszClientId(long id){ // get all vaccination from DB by client ID 
		List<Vaccination> allVaccination = new ArrayList<Vaccination>();
		try{
	    	final String SELECT_ALL_VACCINATION = "SELECT * FROM corona_db.vaccination ,corona_db.manufacturer" + 
	    			                              " WHERE  corona_db.vaccination.manufacturerID = corona_db.manufacturer.manufacturerID" +
	    			                              " AND clientID=?";//the information from vaccination table with column for vaccine's name from manufacturer table
	    	PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_VACCINATION);//connect to DB
	    	preparedStatement.setLong(1, id);
	    	ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	 while (resultSet.next()) {

	                long clientID = resultSet.getLong("clientID");
	                String dateVaccination = resultSet.getString("dateVaccination");
	                int manufacturerID = resultSet.getInt("manufacturerID");
	                String manufacturerName =resultSet.getString("manufacturerName");
	               	                
	                Vaccination vacc = new Vaccination();
	                vacc.setClientID(clientID);
	                vacc.setManufacturerID(manufacturerID); 
	                vacc.setDateVaccination(dateVaccination);
	                vacc.setManufacturerName(manufacturerName);
	                allVaccination.add(vacc);//add the vaccitation
	  
	                         
	                //System.out.println(vacc);
	            }
	    }
	    catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
		return allVaccination;
		
	}
	
	public List<Recovering> getAllRecoveringByClientId(long id){ // get all Recovering from DB by client ID 
		List<Recovering> allRecovering = new ArrayList<Recovering>();
		try{
	    	final String SELECT_ALL_RECOVERING = "SELECT * FROM corona_db.recovering WHERE clientID=?";
	    	PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_RECOVERING);//connect to DB
	    	preparedStatement.setLong(1, id);
	    	ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	 while (resultSet.next()) {
	 
	                long clientID = resultSet.getLong("clientID"); 
	                String PositiveTestDate = resultSet.getString("positiveTestDate");
	                String recoveringDate = resultSet.getString("recoveringDate");
    	                
	                Recovering recover = new Recovering();
	                recover.setClientID(clientID);
	                recover.setPositiveTestDate(PositiveTestDate);
	                recover.setRecoveringDate(recoveringDate);
	                allRecovering.add(recover);
	  
	                         
	                //System.out.println(recover);
	            }
	    	 
	    }
	    catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
		return allRecovering;
		
	}
	
	public String removeClient(long id){ // Remove client from DB by client ID 
		
		try {
			//To delete a client we will first have to delete him from all the other tables
			final String DELETE_RECOVERING_BY_ID = "DELETE FROM corona_db.recovering WHERE clientID=?";
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_RECOVERING_BY_ID);//connect to DB
	    	preparedStatement.setLong(1, id);
	    	preparedStatement.executeUpdate();
	    	final String DELETE_VACCINATION_BY_ID  = "DELETE FROM corona_db.vaccination  WHERE clientID=?";
	        preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_VACCINATION_BY_ID);//connect to DB
	    	preparedStatement.setLong(1, id);
	    	preparedStatement.executeUpdate();
	    	//Delete the client
	    	final String DELETE_CLIENT_BY_ID = "DELETE FROM corona_db.client  WHERE clientID=?";
	        preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_CLIENT_BY_ID);//connect to DB
	    	preparedStatement.setLong(1, id);
	    	preparedStatement.executeUpdate();
			
		}
		catch(Exception e){
			return "The user does not exist in the system";
		
		}
		//System.out.println("seccessfully");
		return "User removed seccessfully";
		}
	
	public String AddClient(Client client){ // Add new client to DB 
		
		try {
			final String ADD_NEW_CLIENT = "INSERT INTO corona_db.client VALUES(?,?, ?, ?,?,?, ?, ?,?)";
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_NEW_CLIENT);//connect to DB
			preparedStatement.setLong(1, client.getClientID());
	    	preparedStatement.setString(2, client.getLastName());
	    	preparedStatement.setString(3, client.getFirstName());
	    	preparedStatement.setString(4, client.getCity());
	    	preparedStatement.setString(5, client.getStreet());
			preparedStatement.setInt(6, client.getHomeNum());
			preparedStatement.setString(7, client.getPhone());
			preparedStatement.setString(8, client.getMobilePhone());
			preparedStatement.setString(9, client.getDateOfBirth());
	    	preparedStatement.executeUpdate();	
		}
		catch(Exception e){
			return "The user already exist in the system";
		
		}
		//System.out.println("seccessfully");
		return "User Added seccessfully";
		}

	public String AddDateOfVaccination(Vaccination vaccination) {//Add date of vaccination to BD long clientID, Date dateVaccination,int manufacturerID
		try {
			if(!(CheckingManufacturerExist(vaccination.getManufacturerID()))) {
				throw new Exception("This Manufacturer ID does not exist"); 
			}
			List<Vaccination> vac = new ArrayList<Vaccination>(); 
			vac = getAllVaccinationszClientId(vaccination.getClientID());
			if(vac.size()>3) {
				throw new Exception("This client already vaccinated four times"); 
			}
			final String ADD_DATE_OF_VACCINATION = "INSERT INTO corona_db.vaccination VALUES(?,?,?)";
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_DATE_OF_VACCINATION);//connect to DB
			preparedStatement.setLong(1, vaccination.getClientID());
			//java.sql.Date sDate = new java.sql.Date(vaccination.getDateVaccination().getTime());
	    	preparedStatement.setString(2, vaccination.getDateVaccination());
	    	preparedStatement.setInt(3, vaccination.getManufacturerID());
	    	preparedStatement.executeUpdate();
			
		}
		
		catch(Exception e){
			return e.getMessage();
		
		}
		System.out.println("seccessfully");
		return "Vaccination Added seccessfully";
		
		
	}
	
    public String UpdateClient(Client client){ // Update client by client ID 
		
		try {
			final String UPDATE_CLIENT = "UPDATE corona_db.client "
					+ "SET  lastName= ? ,firstName= ? ,city=? ,street=? , homeNum = ?,phone=?, mobilePhone=?  "
							+ "WHERE clientID=?";
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_CLIENT);//connect to DB
	    	preparedStatement.setString(1, client.getLastName());
	    	preparedStatement.setString(2, client.getFirstName());
	    	preparedStatement.setString(3, client.getCity());
	    	preparedStatement.setString(4, client.getStreet());
			preparedStatement.setInt(5, client.getHomeNum());
			preparedStatement.setString(6, client.getPhone());
			preparedStatement.setString(7, client.getMobilePhone());
			preparedStatement.setLong(8, client.getClientID());
	    	preparedStatement.executeUpdate();		
		}
		catch(Exception e){
			return e.getMessage();
		
		}
		//System.out.println("seccessfully");
		return "User updated seccessfully";
		}
	
    public String AddManufacturer(int manufacturerID, String manufacturerName) {//Add manufacturer
		try {
			final String ADD_MANUFACTURER = "INSERT INTO corona_db.manufacturer VALUES(?,?)";
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_MANUFACTURER);//connect to DB
			preparedStatement.setInt(1, manufacturerID);
	    	preparedStatement.setString(2, manufacturerName);
	    	preparedStatement.executeUpdate();

			
		}
		catch(Exception e){
			return "The manufacturer already exist in the system";
		
		}
		//System.out.println("seccessfully");
		return "Manufacturer added seccessfully";
    }

    public Client getAllDetailsClientById(long client_ID){ // get all client's details from DB by client ID 
    	Client client = new Client();
		try{
	    	final String SELECT_ALL_CLIENT_DETAILS =  "SELECT * FROM corona_db.client WHERE clientID=?";
	    	PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_CLIENT_DETAILS);//connect to DB
	    	preparedStatement.setLong(1, client_ID);
	    	ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	resultSet.next();
	    	long clientID = resultSet.getLong("clientID");
            String lastName = resultSet.getString("lastName");
            String firstName = resultSet.getString("firstName");
            String city = resultSet.getString("city");
            String street = resultSet.getString("street");
            int homeNum = resultSet.getInt("homeNum");
            String phone = resultSet.getString("phone");
            String mobilePhone = resultSet.getString("mobilePhone");
            String dateOfBirth = resultSet.getString("dateOfBirth");

            
            
            client.setClientID(clientID); 
            client.setLastName(lastName);
            client.setFirstName(firstName);
            client.setCity(city);
            client.setStreet(street);
            client.setHomeNum(homeNum);
            client.setPhone(phone);
            client.setMobilePhone(mobilePhone);	    
            client.setDateOfBirth(dateOfBirth);

	    }
	    catch(Exception e) {
	    	
	    }
		return client;
		
	}
    	
    public String AddRecoveringDate(Recovering rec) {//Add recovering date
		try {
			final String ADD_RECOVERING_DATE = "INSERT INTO corona_db.Recovering VALUES(?,?,?)";
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_RECOVERING_DATE);//connect to DB
			preparedStatement.setLong(1, rec.getClientID());
			//java.sql.Date positiveDate = new java.sql.Date(rec.getPositiveTestDate().getTime());
	    	preparedStatement.setString(2, rec.getPositiveTestDate());
	    	//java.sql.Date recDate = new java.sql.Date(rec.getRecoveringDate().getTime());
	    	preparedStatement.setString(3, rec.getRecoveringDate());
	    	preparedStatement.executeUpdate();
		
		}
		catch(Exception e){
			return e.getMessage();
		
		}
		//System.out.println("seccessfully");
		return "Recovering Date added seccessfully";
	}

    public boolean CheckingManufacturerExist(int manufacturerId) {//Checking if Manufacturer id is Exist
		try {
			final String CHECKING_MANUFACTURER_EXIST = "SELECT manufacturerID FROM corona_db.manufacturer WHERE  manufacturerID =?";
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(CHECKING_MANUFACTURER_EXIST);//connect to DB
			preparedStatement.setInt(1, manufacturerId);
			ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	while (resultSet.next()) {
	    		if (resultSet.getInt("manufacturerID")==manufacturerId){
		    		return true;
	    		}	
	    	}	   	    		
		}
		catch(Exception e){
		}
		return false;
	}
    
    /* public String updateRecovering(int clientID, Date positiveTestDate, Date recoveringDate) {//Update recovering date by client ID and positive test date
	try {
		final String UPDATE_RECOVERING = "UPDATE corona_db.recovering SET recoveringDate=? "
				+ "WHERE clientID=? AND positiveTestDate=?";
		PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_RECOVERING);//connect to DB
		preparedStatement.setDate(1, recoveringDate);
    	preparedStatement.setLong(2, clientID);
    	preparedStatement.setDate(3, positiveTestDate);
    	preparedStatement.executeUpdate();	
	}
	catch(Exception e){
		return "The manufacturer already exist in the system";
	
	}
	System.out.println("seccessfully");
	return "Manufacturer added seccessfully";
}*/
    
    }


	
	
	
	

