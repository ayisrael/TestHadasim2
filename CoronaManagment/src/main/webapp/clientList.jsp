<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" import="serverSide.CoronaManager,serverSide.DatabaseConnection, java.sql.Connection,java.util.List, serverSide.Client, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Home screen</title>
</head>
<body>
<% 
String message = request.getParameter("message");
if(message==null){
	 message="";
}
 //Connection con = DatabaseConnection.getConnection();
 CoronaManager cm = new CoronaManager();
 List<Client> clients =new  ArrayList<Client>();
 clients=cm.getAllClients();//get all client from DB
 %>

<table style="width:100%">
  <tr>
        <th align="left">Client ID: </th>
		<th align="left">Last name: </th>
		<th align="left">First name </th>
		<th align="left">Date of Birth </th>
		<th align="left">City </th>
		<th align="left">Street</th>
		<th align="left"> Home number</th>
		<th align="left">Phone </th>
		<th align="left">Mobile phone</th>
		
 </tr>
 
 
 <%for(int i = 0; i < clients.size(); i+=1) { %>
            <tr>  
            <td><a href="clientDetails.jsp?clnId=<%= clients.get(i).getClientID() %>"><%= clients.get(i).getClientID() %></a></td>
            <td><%= clients.get(i).getLastName() %></td>
            <td><%= clients.get(i).getFirstName() %></td>
            <td><%= clients.get(i).getDateOfBirth() %></td>
            <td><%= clients.get(i).getCity() %></td>
            <td><%= clients.get(i).getStreet() %></td>
            <td><%= clients.get(i).getHomeNum() %></td>
            <td><%= clients.get(i).getPhone() %></td>
            <td><%= clients.get(i).getMobilePhone() %></td>
            
            </tr>
       <% } %>
 <table>
<tr>
		<td colspan="8"><input type="button" value="Add new client" style="height:50px; width:200px" title="Add new client" onclick="window.location='addNewClient.jsp'"/></td>
		<td colspan="8"><input type="button" value="Chart" style="height:50px; width:200px" title="How many people are vaccinated" onclick="window.location='chart.jsp'"/></td>
</tr>
</table>


<p>
	<%=message%>
	</p>

</body>
</html>