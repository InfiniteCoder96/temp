package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.dbconnect;
import models.Reservation;



/**
 * Servlet implementation class reservation
 */
@WebServlet("/reservation")
public class reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection conn = null;
	
    public reservation() {
        super();
        conn = dbconnect.connect();
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
		doGet(request, response);
		
		Reservation resv = new Reservation();
		resv.setFirstName(request.getParameter("fname"));
		resv.setLastName(request.getParameter("lname"));
		resv.setEmail(request.getParameter("email"));
		resv.setMobile(request.getParameter("mobile"));
		resv.setAddress(request.getParameter("address"));
		resv.setCheckIn(request.getParameter("check_in"));
		resv.setCheckOut(request.getParameter("check_out"));
		
		if(resv.getFirstName().isEmpty() || resv.getLastName().isEmpty() || resv.getEmail().isEmpty() || resv.getMobile().isEmpty() || resv.getAddress().isEmpty() || resv.getCheckIn().isEmpty() || resv.getCheckOut().isEmpty()) {
			System.out.println("Please Fil the Feilds");
		}
		else {
			
			String query = "INSERT INTO reservation(first_name,	last_name, email, mobile, check_in, check_out, address) "
					+ "VALUES('"+ resv.getFirstName() +"', '"+ resv.getLastName() +"', '"+ resv.getEmail() +"', '"+ resv.getMobile() +"', '"+ resv.getCheckIn() +"', '"+ resv.getCheckOut() +"', '"+ resv.getAddress() +"')";
			
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
				response.sendRedirect("index.jsp");
				
				System.out.println("Reservation Successfully");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
