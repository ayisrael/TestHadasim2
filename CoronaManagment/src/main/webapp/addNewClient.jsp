<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Add new client</title>
<script type="text/javascript">
function changeAction(act) {
	document.getElementById("actionToPerform").value= act;
	cdForm.submit();
}

</script>

</head>
<body>
	<form name="cdForm" method=post action=CoronaMngServlet>
	<table>
			<tr>
			<td align="left" >Client ID:</td>
			<td><input type="text"  name="clientId" id="clientId"  pattern="[0-9]{9}"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left">Last name:</td>
		<td> <input type="text"  name="lname" id="lname" pattern="[A-Za-z]{1,20}"/> <br/></td>
		</tr>
		
		
		<tr>
		<td align="left"> First name:</td>
        <td><input type="text"  name="fname" id="fname" pattern="[A-Za-z]{1,20}"/> <br/></td>
		</tr>
		
		<tr>
			<td align="left">Date of birth:</td>
			<td> <input type="text" name="dateOfBirth"  id="dateOfBirth"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left">City:</td>
        <td><input type="text"  name="city" id="city"  pattern="[A-Za-z\s]{1,20}"/> <br/> </td>
		</tr>
	
		<tr>
		<td align="left"> Street:</td>
        <td><input type="text"  name="street" id="street"  pattern="[A-Za-z\s]{1,20}"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left"> Home number:</td>
        <td><input type="text"  name="homeNum" id="homeNum"pattern="[0-9]+"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left">Phone </td>
        <td><input type="text"   name="phone" id="phone" pattern="[0-9]{9}"/> <br/></td>
		</tr>
		
		<tr>
		<td align="left">Mobile phone</td>
        <td><input type="text"  name="mobilePhone" id="mobilePhone"  pattern="[0-9]{10}"/> <br/></td>
		</tr>
		<tr> 
		<td>
        <input type="text"  name="actionToPerform" id="actionToPerform" value="addNewClient" style="display:none"/> <br/>		
		</td>
		</tr>		
	</table>
	<table> 
	<tr>
	<td>
	<input type="submit" id="saveButton" style="height:50px; width:200px" title="�����" value="Add"/>
    </td>
    <td><input type="button" value="Beck  "  style="height:50px; width:200px" onclick="changeAction('Back')"></td>
   </tr>
	</table>

</form>

	
</body>
</html>