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
		String act = request.getParameter("actionToPerform");
		//String message = "null";
		
		
		if(act.equals("delete")) {
			client.setClientID( Long.parseLong(request.getParameter("clientId")));
			String message = cm.removeClient(client.getClientID());
			response.sendRedirect("clientList.jsp?clnId=" + client.getClientID() + "&message=" + message);  

		}
		else if(act.equals("addVaccination")) {
			Vaccination vacc = new Vaccination();
			vacc.setClientID(Long.parseLong(request.getParameter("clientIdVacc")));
			vacc.setDateVaccination(request.getParameter("dateVacc"));
			vacc.setManufacturerID(Integer.parseInt(request.getParameter("manufacturerID")));
			/*try {
			String stringDate =request.getParameter("dateVacc");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
			java.util.Date date = df.parse(stringDate);
			vacc.setDateVaccination(date);
			}
			catch(Exception e) {
				System.out.println( e);
			};*/
			//vacc.setDateVaccination(date);
			String message = cm.AddDateOfVaccination(vacc);
			response.sendRedirect("clientDetails.jsp?clnId=" + vacc.getClientID() + "&message=" + message);
		}
		else if(act.equals("addRec")) {
			Recovering rec = new Recovering();
			rec.setClientID(Long.parseLong(request.getParameter("clientIdRec")));
			rec.setPositiveTestDate(request.getParameter("positiveDate"));
			rec.setRecoveringDate(request.getParameter("dateOfRec"));
			String message = cm.AddRecoveringDate(rec);
			response.sendRedirect("clientDetails.jsp?clnId=" + rec.getClientID() + "&message=" + message);
			/*try {
			String stringDate =request.getParameter("positiveDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date positiveDate = df.parse(stringDate);
		    rec.setPositiveTestDate(positiveDate);
		    stringDate =request.getParameter("dateOfRec");
		    java.util.Date recDate = df.parse(stringDate);
		    rec.setRecoveringDate(recDate);
			}
			catch(Exception e) {
			};*/
			//vacc.setDateVaccination(date);

		}
		else if(act.equals("Back")) {
			response.sendRedirect("clientList.jsp" );  
		}
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
				
				
				if (act.equals("addNewClient")) {
					client.setDateOfBirth(request.getParameter("dateOfBirth"));
					//String stringDate =request.getParameter("dateOfBirth");
					String message = cm.AddClient(client);
					response.sendRedirect("clientList.jsp?clnId=" + client.getClientID() + "&message=" + message);  
					/*if(stringDate!=null) {
						DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
						java.util.Date dateOfBirth = df.parse(stringDate);
						client.setDateOfBirth(dateOfBirth);
					}*/
				}
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
		
		
		//PrintWriter writer = response.getWriter();
		  
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
