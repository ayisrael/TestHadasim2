package serverSide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 * All client details 
 */
public class Client {
	private long clientID;
	private String lastName;
	private String firstName;
	private String city;
	private String street;
	private int homeNum;
	private String phone;
	private String mobilePhone;
	private Date dateOfBirth;
	
	List<Recovering> recovery = new ArrayList<Recovering>();
	List<Vaccination> vaccination = new ArrayList<Vaccination>();
	
	
	public long getClientID() {
		return clientID;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getCity() {
		return city;
	}
	public String getStreet() {
		return street;
	}
	public int getHomeNum() {
		return homeNum;
	}
	public String getPhone() {
		return phone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public List<Recovering> getRecovery() {
		return recovery;
	}
	public List<Vaccination> getVaccination() {
		return vaccination;
	}
	public String getDateOfBirth() {
		String date = new SimpleDateFormat("yyyy/MM/dd").format(dateOfBirth);

		return date;
		//return dateOfBirth;
	}
	public void setClientID(long clientID) {
		this.clientID = clientID;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setHomeNum(int homeNum) {
		this.homeNum = homeNum;
	}
	public void setRecovery(List<Recovering> recovery) {
		this.recovery = recovery;
	}
	public void setVaccination(List<Vaccination> vaccination) {
		this.vaccination = vaccination;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public void setDateOfBirth(String dateOfBirth) {
		try {
			this.dateOfBirth =new SimpleDateFormat("yyyy/MM/dd").parse(dateOfBirth); 
			}
		 catch(Exception e) {
		    }
		//this.dateOfBirth = dateOfBirth;
	}

	
	
	
}
