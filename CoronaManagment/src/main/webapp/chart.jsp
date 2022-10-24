<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" import="serverSide.CoronaManager,serverSide.DatabaseConnection, java.sql.Connection,java.util.List, serverSide.Client, java.util.ArrayList"%>
 
<!DOCTYPE HTML>
<html>
<head>
<% 
//Connection con = DatabaseConnection.getConnection();
CoronaManager cm = new CoronaManager();
int vaccinated = cm.CheckHowManyAreVaccinated();//get the number of vaccinated client
int notVaccinated = cm.CheckHowManyAreNotVaccinated();//get the number of unvaccinated client

%>

</head>
<body>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script type="text/javascript">
window.onload = function () {
	var chart = new CanvasJS.Chart("chartContainer", {
		title:{
			text: "Vaccination diagram"              
		},
		data: [              
		{
			// Change type to "doughnut", "line", "splineArea", etc.
			type: "column",
			dataPoints: [
				
				{ label: "Vaccinated",  y: <%= vaccinated%>  },
				{ label: "Not vaccinated", y: <%= notVaccinated%>  }
				
			]
		}
		]
	});
	chart.render();
}
</script>
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
<table>
<tr>
<td><input type="button" value=" Back "  style="height:50px; width:200px" onclick="window.location='clientList.jsp'"></td>
</tr>
</table>
</body>
</html>    