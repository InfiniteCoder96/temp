package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.dbconnect;


/**
 * Servlet implementation class update_r
 */
@WebServlet("/update_r")
public class update_r extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	Statement stmt = null;
	ResultSet rs = null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update_r() {
        super();
        // TODO Auto-generated constructor stub
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
		
		String id     = request.getParameter("id");
		String email     = request.getParameter("email");
		String fname     = request.getParameter("fname");
		String lname  = request.getParameter("lname");
		String mobile  = request.getParameter("mobile");
		String address   = request.getParameter("address");
		String in  = request.getParameter("check_in");
		String out   = request.getParameter("check_out");
		System.out.println("inside");
		String query = "UPDATE reservation SET  first_name = '" +fname +"', last_name = '" + lname +"', email = '" + email +"', mobile = '" + mobile +"', address = '" + address +"', check_in = '" + in +"', check_out = '" + out +"' WHERE id = '"+ id +"'";
		
		try {
			Connection conn = dbconnect.connect();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
			System.out.println("Details Update Successfully");
			response.sendRedirect("list.jsp");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
