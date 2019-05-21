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
 * Servlet implementation class delete_r
 */
@WebServlet("/delete_r")
public class delete_r extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Statement stmt = null;
	ResultSet rs = null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete_r() {
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
		
		String id = request.getParameter("r_id");

		
		String query  = "DELETE FROM reservation WHERE id = '" + id +"'";
		
		try {
			Connection conn = dbconnect.connect();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
			System.out.println("Succefully Delete reservation");
			response.sendRedirect("list.jsp");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
