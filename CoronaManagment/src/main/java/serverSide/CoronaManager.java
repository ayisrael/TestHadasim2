package serverSide;

//import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoronaManager {//All the CRUD (connection to mysql)
	
	public List<Client> getAllClients(){ // get all client from DB
		List<Client> allClient = new ArrayList<Client>();//List of all the clients
		
	    try{
	    	final String SELECT_ALL_CLIENT = "SELECT * FROM corona_db.client";//The query to mysql
	    	Connection con = DatabaseConnection.getConnection();
	    	PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_CLIENT);//connect to DB
	    	ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	 while (resultSet.next()) {             
	                Client client = new Client();
	                //get the details about client from DB and create 
	                client.setClientID(resultSet.getLong("clientID")); 
	                client.setLastName(resultSet.getString("lastName"));
	                client.setFirstName(resultSet.getString("firstName"));
	                client.setCity(resultSet.getString("city"));
	                client.setStreet(resultSet.getString("street"));
	                client.setHomeNum( resultSet.getInt("homeNum"));
	                client.setPhone(resultSet.getString("phone"));
	                client.setMobilePhone(resultSet.getString("mobilePhone"));
	                client.setDateOfBirth(resultSet.getString("dateOfBirth"));
	                allClient.add(client);//add the client to list
               
	                //System.out.println(obj);
	            }
	    	 
	    }
	    catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }


		return allClient;
	};
	
	public List<Vaccination> getAllVaccinationszClientId(long id){ // get all vaccination from DB by client ID 
		List<Vaccination> allVaccination = new ArrayList<Vaccination>();//List of Vaccination of the patient
		try{
			//the information from vaccination table with column for vaccine's name from manufacturer table
	    	final String SELECT_ALL_VACCINATION = "SELECT * FROM corona_db.vaccination ,corona_db.manufacturer" + 
	    			                              " WHERE  corona_db.vaccination.manufacturerID = corona_db.manufacturer.manufacturerID" +
	    			                              " AND clientID=?";//the query to mysql
	    	PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_VACCINATION);//connect to DB
	    	preparedStatement.setLong(1, id);//parametr to puery
	    	ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	 while (resultSet.next()) {	                           	                
	                Vaccination vacc = new Vaccination();
	                vacc.setClientID(resultSet.getLong("clientID"));
	                vacc.setManufacturerID(resultSet.getInt("manufacturerID")); 
	                vacc.setDateVaccination(resultSet.getString("dateVaccination"));
	                vacc.setManufacturerName(resultSet.getString("manufacturerName"));
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
		List<Recovering> allRecovering = new ArrayList<Recovering>();//List of dates of the client's illness
		try{
	    	final String SELECT_ALL_RECOVERING = "SELECT * FROM corona_db.recovering WHERE clientID=?";//The query to mysql
	    	PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_RECOVERING);//connect to DB
	    	preparedStatement.setLong(1, id);//The parameter to the query
	    	ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	 while (resultSet.next()) {	                
	                Recovering recover = new Recovering();
	                //add date of illness
	                recover.setClientID(resultSet.getLong("clientID"));
	                recover.setPositiveTestDate(resultSet.getString("positiveTestDate"));
	                recover.setRecoveringDate(resultSet.getString("recoveringDate"));
	                allRecovering.add(recover);//add to the list
	                          
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
			final String DELETE_RECOVERING_BY_ID = "DELETE FROM corona_db.recovering WHERE clientID=?";//The query to mysql
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_RECOVERING_BY_ID);//connect to DB
	    	preparedStatement.setLong(1, id);//The parameter to query
	    	preparedStatement.executeUpdate();
	    	final String DELETE_VACCINATION_BY_ID  = "DELETE FROM corona_db.vaccination  WHERE clientID=?";//The query to mysql
	        preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_VACCINATION_BY_ID);//connect to DB
	    	preparedStatement.setLong(1, id);//The parameter to query
	    	preparedStatement.executeUpdate();
	    	//Delete the client
	    	final String DELETE_CLIENT_BY_ID = "DELETE FROM corona_db.client  WHERE clientID=?";//The query to mysql
	        preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_CLIENT_BY_ID);//connect to DB
	    	preparedStatement.setLong(1, id);//The parameter to query
	    	preparedStatement.executeUpdate();
			
		}
		catch(Exception e){
			return e.getMessage();
		
		}
		//System.out.println("seccessfully");
		return "User removed seccessfully";
		}
	
	public String AddClient(Client client){ // Add new client to DB 
		
		try {
			final String ADD_NEW_CLIENT = "INSERT INTO corona_db.client VALUES(?,?, ?, ?,?,?, ?, ?,?)";//The query to mysql
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_NEW_CLIENT);//connect to DB
			//The parameters to puery
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
			return e.getMessage();
		
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
			vac = getAllVaccinationszClientId(vaccination.getClientID());//get all the details about the client's vaccinations
			if(vac.size()>3) {//It is not possible to be vaccinated more than four times
				throw new Exception("This client already vaccinated four times"); 
			}
			final String ADD_DATE_OF_VACCINATION = "INSERT INTO corona_db.vaccination VALUES(?,?,?)";//The query to mysql
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_DATE_OF_VACCINATION);//connect to DB
			preparedStatement.setLong(1, vaccination.getClientID());
			//The parameters to query
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
							+ "WHERE clientID=?";//The query to mysql
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_CLIENT);//connect to DB
	    	//parameters to query
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
			final String ADD_MANUFACTURER = "INSERT INTO corona_db.manufacturer VALUES(?,?)";//The query to mysql
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_MANUFACTURER);//connect to DB
			//parameters
			preparedStatement.setInt(1, manufacturerID);
	    	preparedStatement.setString(2, manufacturerName);
	    	preparedStatement.executeUpdate();
		}
		catch(Exception e){
			return e.getMessage();
		
		}
		//System.out.println("seccessfully");
		return "Manufacturer added seccessfully";
    }

    public Client getAllDetailsClientById(long client_ID){ // get all client's details from DB by client ID 
    	Client client = new Client();
		try{
	    	final String SELECT_ALL_CLIENT_DETAILS =  "SELECT * FROM corona_db.client WHERE clientID=?";//the query to mysql
	    	PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_CLIENT_DETAILS);//connect to DB
	    	preparedStatement.setLong(1, client_ID);
	    	ResultSet resultSet = preparedStatement.executeQuery();//The information fromDB
	    	resultSet.next();
	        //create the client
            client.setClientID(resultSet.getLong("clientID")); 
            client.setLastName(resultSet.getString("lastName"));
            client.setFirstName(resultSet.getString("firstName"));
            client.setCity(resultSet.getString("city"));
            client.setStreet(resultSet.getString("street"));
            client.setHomeNum(resultSet.getInt("homeNum"));
            client.setPhone(resultSet.getString("phone"));
            client.setMobilePhone(resultSet.getString("mobilePhone"));	    
            client.setDateOfBirth(resultSet.getString("dateOfBirth"));

	    }
	    catch(Exception e) {
	    	
	    }
		return client;
		
	}
    	
    public String AddRecoveringDate(Recovering rec) {//Add recovering date
		try {			
			final String ADD_RECOVERING_DATE = "INSERT INTO corona_db.Recovering VALUES(?,?,?)";//the query to mysql
			PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(ADD_RECOVERING_DATE);//connect to DB
			//the parameters to the query
			preparedStatement.setLong(1, rec.getClientID());
	    	preparedStatement.setString(2, rec.getPositiveTestDate());
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
    
    public int CheckHowManyAreVaccinated(){//Check how many client are  vaccinated
    	int count = 0;
    	List<Vaccination> allVaccination = new ArrayList<Vaccination>();
    	List<Client> allClient = new ArrayList<Client>();
    	allClient =getAllClients();//get all client
    	for (int i = 0; i < allClient.size(); i++) {
    		if(getAllVaccinationszClientId(allClient.get(i).getClientID()).size()>0) {//if client(i) has been vaccinted    			
    			count++;		
    		}
    	}
    	
    	return count;
    }
   
    public int CheckHowManyAreNotVaccinated() {//Check how many client are not vaccinated
    	int count = getAllClients().size()- CheckHowManyAreVaccinated();//all client minus the count of the client that vaccinated
    	return count;
    	
    }
   
  

    
    }


	
	
	
	

