package serverSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import entities.*;

//import com.google.gson.Gson;

/**
 * Servlet implementation class CoronaMngServlet
 */
@WebServlet("/CoronaMngServlet")
public class CoronaMngServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CoronaMngServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//CoronaManager cm = new CoronaManager();
		//Gson gson = new Gson();
		//response.setContentType("application/json");
	   // PrintWriter out = response.getWriter();
	    //out.println(gson.toJson(cm.getAllClients()));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		CoronaManager cm = new CoronaManager();
		Client client = new Client();
		String act = request.getParameter("actionToPerform");//get the name of action to be preformed;  
		//String message = "null";
		
		//delete 
		if(act.equals("delete")) {
			client.setClientID( Long.parseLong(request.getParameter("clientId")));
			String message = cm.removeClient(client.getClientID());
			response.sendRedirect("clientList.jsp?clnId=" + client.getClientID() + "&message=" + message);  

		}
		//add vaccination to client
		else if(act.equals("addVaccination")) {
			Vaccination vacc = new Vaccination();
			vacc.setClientID(Long.parseLong(request.getParameter("clientIdVacc")));
			vacc.setDateVaccination(request.getParameter("dateVacc"));
			vacc.setManufacturerID(Integer.parseInt(request.getParameter("manufacturerID")));
			String message = cm.AddDateOfVaccination(vacc);
			response.sendRedirect("clientDetails.jsp?clnId=" + vacc.getClientID() + "&message=" + message);
		}
		//add illness dates to client
		else if(act.equals("addRec")) {
			try {
				Recovering rec = new Recovering();
				//check if the date is correct
				boolean bool =rec.CheckDates((request.getParameter("positiveDate")), (request.getParameter("dateOfRec")));
				if(bool == false){
					throw new Exception("The date of recovery must be later than the date of receiving a positive result");
				}
				rec.setClientID(Long.parseLong(request.getParameter("clientIdRec")));
				rec.setPositiveTestDate(request.getParameter("positiveDate"));
				rec.setRecoveringDate(request.getParameter("dateOfRec"));
				String message = cm.AddRecoveringDate(rec);
				response.sendRedirect("clientDetails.jsp?clnId=" + rec.getClientID() + "&message=" + message);
			}
			catch(Exception e){
				response.sendRedirect("clientDetails.jsp?clnId=" + request.getParameter("clientIdRec") + "&message=" + e.getMessage());

			}
			

		}
	//update or add new client
		else {
			try {
				client.setClientID( Long.parseLong(request.getParameter("clientId")));
				client.setLastName(request.getParameter("lname"));
				client.setFirstName(request.getParameter("fname"));
				client.setCity(request.getParameter("city"));
				client.setStreet(request.getParameter("street"));
				client.setHomeNum( Integer.parseInt(request.getParameter("homeNum")));
				client.setPhone(request.getParameter("phone"));
				client.setMobilePhone(request.getParameter("mobilePhone"));
				
				//add new client
				if (act.equals("addNewClient")) {
					client.setDateOfBirth(request.getParameter("dateOfBirth"));
					String message = cm.AddClient(client);
					response.sendRedirect("clientList.jsp?clnId=" + client.getClientID() + "&message=" + message);  					
				}//update
				else if(act.equals("update")){//update
					String message = cm.UpdateClient(client);
					response.sendRedirect("clientDetails.jsp?clnId=" + client.getClientID() + "&message=" + message);  
				}
				else {
					System.out.println("ERROR");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
		
		  
		//writer.close();
		}
		/*
		 * String htmlRespone = "<html><script type=\"text/javascript\">\r\n" +
		 * "window.navigate(\"clientDetails.jsp?clnId=12000\");\r\n" + "</script>\r\n" +
		 * "</html>";
		 * 
		 * // return response writer.println(htmlRespone);
		 */
	}

}