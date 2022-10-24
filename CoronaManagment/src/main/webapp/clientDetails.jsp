<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" import="serverSide.CoronaManager,serverSide.Vaccination,serverSide.Recovering, serverSide.DatabaseConnection, java.sql.Connection,java.util.List, serverSide.Client, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Client Details</title>
<script type="text/javascript">
//Makes the data available for editing
function enableEdit() {
	document.getElementById("lname").readOnly = false;
	document.getElementById("fname").readOnly = false;
	document.getElementById("city").readOnly = false;
	document.getElementById("street").readOnly = false;
	document.getElementById("homeNum").readOnly = false;
	document.getElementById("phone").readOnly = false;
	document.getElementById("mobilePhone").readOnly = false;
	document.getElementById("update").style.visibility = 'hidden';
	SubmitUpdate.style.display='block';
}
//Allows  to add a vaccine
function enableAddVacc(act){
	addVac.style.display='block';
	addVacButton.style.visibility ='hidden';
	saveNewVacc.style.display='block';
}
//Allows  to add Dates of illnes 
function enableAddRec(){
	addRec.style.display='block';
	addRecButton.style.visibility ='hidden';
	saveNewRec.style.display='block';
}

//insert the name of action to "value" for the servlet
function changeAction(act) {
	document.getElementById("actionToPerform").value= act;
	cdForm.submit();
}


</script>
</head>
<body>

<% 
 long id = Long.parseLong(request.getParameter("clnId"));
 String message = request.getParameter("message");
 if(message==null){
	 message="";
 }

 //Connection con = DatabaseConnection.getConnection();
 CoronaManager cm = new CoronaManager();
 Client client= cm.getAllDetailsClientById(id);//get the details
 List<Vaccination> vacc =new  ArrayList<Vaccination>();
 vacc= cm.getAllVaccinationszClientId(id);//get all the client's vaccinations
 List<Recovering> recovering =new  ArrayList<Recovering>();
 recovering= cm.getAllRecoveringByClientId(id);//get all client's illness date

 %>
	<form name="cdForm" method=post action=CoronaMngServlet>
	<table>
		<tr>
			<td align="left" >Client ID:</td>
			<td><input type="text" readonly  name="clientId" id="clientId" value="<%= client.getClientID()%>" pattern="[0-9]{9}"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left">Last name:</td>
		<td> <input type="text" readonly required name="lname" id="lname" value="<%= client.getLastName()%>" pattern="[A-Za-z]{1,20}"/> <br/></td>
		</tr>
		
		
		<tr>
		<td align="left"> First name:</td>
        <td><input type="text" readonly required name="fname" id="fname" value="<%= client.getFirstName()%>" pattern="[A-Za-z]{1,20}"/> <br/></td>
		</tr>
		
		<tr>
			<td align="left">Date of birth:</td>
			<td> <input type="text" name="dateOfBirth" readonly  id="dateOfBirth" value="<%= client.getDateOfBirth()%>"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left">City:</td>
        <td><input type="text" readonly name="city" id="city" required value="<%= client.getCity()%>" pattern="[A-Za-z\s]{1,20}"/> <br/> </td>
		</tr>
	
		<tr>
		<td align="left"> Street:</td>
        <td><input type="text" readonly name="street" id="street" required value="<%= client.getStreet()%>" pattern="[A-Za-z\s]{1,20}"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left"> Home number:</td>
        <td><input type="text" readonly name="homeNum" id="homeNum" required value="<%= client.getHomeNum()%>" pattern="[0-9]+"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left">Phone </td>
        <td><input type="text" readonly  name="phone" id="phone" required value="<%= client.getPhone()%>" pattern="[0-9]{9}"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left">Mobile phone</td>
        <td><input type="text" readonly name="mobilePhone" id="mobilePhone" required value="<%= client.getMobilePhone()%>" pattern="[0-9]{10}"/> <br/></td>
		</tr>
		<tr> 
		<td>
        <input type="text" readonly name="actionToPerform" id="actionToPerform" value="update" style="display:none"/> <br/>		
		</td>
		</tr>
	<tr>
	<td>
    <input type="button" id="update" value="Update" style="height:30px; width:180px" onclick="enableEdit()"/>
	<input type="submit" id="SubmitUpdate" value=" Save details"  style="height:30px; width:150px" hidden="true"/>
    </td>
   </tr>	
	</table>

    
	<!-- input type="submit" id="saveButton" value="Save update" hidden="true"/-->
</form>


<table style="width:50%">
  <tr>
        <th align="left">Date of receipt of vaccination :</th>
		<th align="left"> Manufacturer ID:</th>
		<th align="left">Manufacturer name: </th>
		
 </tr>
 
 <%for(int i = 0; i < vacc.size(); i+=1) { %>
            <tr>  
            <td><%= vacc.get(i).getDateVaccination()%></td>
            <td><%= vacc.get(i).getManufacturerID() %></td>
            <td><%= vacc.get(i).getManufacturerName() %></td>               
            </tr>
       <% } %>
       
</table>

<div id="addVac" style="display:none">
<form name="vaccForm" id="vaccForm" method=post action=CoronaMngServlet >
	<table>
	<tr>
	<td><input type="text" readonly name="clientIdVacc" id="clientIdVacc" pattern="[A-Za-z]{1,20}" style="display:none" value="<%= client.getClientID()%>"/> <br/></td>  
	</tr>
	
	<tr>
	<td> Date of receipt of vaccination:</td>
	<td> <input type="text" required name="dateVacc" id="dateVacc" pattern="\d{4}/\d{1,2}/\d{1,2}"/> <br/></td>  
	</tr>
	
	<tr>
	<td>Manufacturer ID:</td>
	<td> <input type="text" required name="manufacturerID" id="manufacturerID" pattern="[0-9]+"/> <br/></td>  
	</tr>
	</table>
	<table>
	<tr>
	<td>
	<input type="text" readonly  name="actionToPerform" id="actionToPerform" value="addVaccination" style="display:none"/> <br/>
    <input type="submit" id="saveNewVacc" value="Save details  " style="height:30px; width:150px" hidden="true" />
	</td>
	</tr>
	</table>

</form>
</div>
<div>    
       <input type="button" id="addVacButton" value=" Add vaccination receipt date " style="height:30px; width:200px" onclick="enableAddVacc('addVacc')" />   
</div>



<table style="width:50%">
  <tr>
        <th align="left">Date of receiving a positive result:  </th>
		<th align="left">Date of recovery:</th>		
 </tr>
 
 <%for(int i = 0; i < recovering.size(); i+=1) { %>
            <tr>  
            <td><%= recovering.get(i).getPositiveTestDate() %></td>
            <td><%= recovering.get(i).getRecoveringDate() %></td>
            </tr>
       <% } %>
       
</table>
<div id="addRec" style="display:none">
<form name="RecForm" id="RecForm" method=post action=CoronaMngServlet >
	<table>
	<tr>
	<td><input type="text" readonly name="clientIdRec" id="clientIdRec"  style="display:none" value="<%= client.getClientID()%>"/> <br/></td>
	</tr>
	<tr>
	<td>Date of receiving a positive result: </td>
	<td><input type="text" name="positiveDate" id="positiveDate" pattern="\d{4}/\d{1,2}/\d{1,2}" required/> <br/></td>
	</tr>
	<tr>
	<td>Date of recovery:</td>
	<td><input type="text" name="dateOfRec" id="dateOfRec" pattern="\d{4}/\d{1,2}/\d{1,2}" required/> <br/></td>
	</tr>	
	</table>
	<input type="text" readonly name="actionToPerform" id="actionToPerform" value="addRec" style="display:none"/> <br/>
    <input type="submit" id="saveNewRec" value="Save details  " style="height:30px; width:150px" hidden="true" />
</form>
</div>
<input type="button" id="addRecButton" value="Add dates of illness " style="height:30px; width:200px" onclick="enableAddRec()" />

<table>
<tr>
<td><input type="button" value=" Back "  style="height:50px; width:200px" onclick="window.location='clientList.jsp'"></td>
<td><input type="button" value="Delete client" style="height:50px; width:200px" onclick="changeAction('delete')"/></td>
</tr>
</table>

<div>    
</div>




<p>
	<%=message%>
	</p>	
</body>
</html>

