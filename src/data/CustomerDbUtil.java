package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import models.Customer;
import utils.Helper;

public class CustomerDbUtil {

	private DataSource dataSource;
	
	public CustomerDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Long insertCustomer(HttpServletRequest request, HttpSession session) {
		
		long booked_user = 0;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_user_insert = Helper.INSERT_CUSTOMER_INFO;
			
			myStmt = myConn.prepareStatement(sql_user_insert,Statement.RETURN_GENERATED_KEYS);
			
			myStmt.setString(1, request.getParameter("fullname"));
			myStmt.setString(2, request.getParameter("address"));
			myStmt.setString(3, request.getParameter("country"));
			myStmt.setInt(4, Integer.parseInt(request.getParameter("mobile_no")));
			myStmt.setString(5, request.getParameter("email"));
			myStmt.setInt(6, Integer.parseInt(request.getParameter("adults")));
			myStmt.setInt(7, Integer.parseInt(request.getParameter("childs")));
			myStmt.setString(8, request.getParameter("payment_method"));
			myStmt.setInt(9, Integer.parseInt(request.getParameter("nights")));
			myStmt.setInt(10, Integer.parseInt(request.getParameter("rooms")));
			
			int affectedCustomers = myStmt.executeUpdate();
			
			if(affectedCustomers == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = myStmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	
	            	booked_user = generatedKeys.getLong(1);
	            	System.out.println(booked_user);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			
			session.setAttribute("USER_ID", booked_user);
			return booked_user;
			
		} 
		catch(Exception e) {
			e.printStackTrace();
			return booked_user;
		}
		finally{
			closeConnections(myConn,null,myStmt2,null,null);
		}
	}
	
	private void closeConnections(Connection myConn, Statement myStmt1, PreparedStatement myStmt2, ResultSet myRs1,
			ResultSet myRs2) {


		try {
			
			if(myConn != null) {
				myConn.close();
			}
			
			if(myRs1 != null) {
				myRs1.close();			
			}
			
			if(myRs2 != null) {
				myRs2.close();
			}
			
			if(myStmt1 != null) {
				myStmt1.close();
			}
			
			if(myStmt2 != null) {
				myStmt2.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Customer getCustomer(HttpServletRequest request) {
		
		String customer_id = request.getParameter("reservation_id");
		
		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_search_customer = Helper.SEARCH_CUSTOMER_INFO;
			
			myStmt2 = myConn.prepareStatement(sql_search_customer);
			
			myStmt2.setInt(1, Integer.parseInt(customer_id));
			
			myRs = myStmt2.executeQuery();
				
			Customer customer = null;
			
			while(myRs.next()) {
				
				String fullname = myRs.getString("fullname");
				String address = myRs.getString("address");
				String country = myRs.getString("country");
				int mobile = myRs.getInt("mobile");
				String email = myRs.getString("email");
				int adult_visitors = myRs.getInt("adult_visitors");
				int child_visitors = myRs.getInt("child_visitors");
				String payment_method = myRs.getString("payment_method");
				int nights = myRs.getInt("nights");
				int rooms = myRs.getInt("rooms");
				
				customer = new Customer(fullname, address, country, mobile, email, adult_visitors, child_visitors, payment_method, nights, rooms);
				
				
				
			}
				
			
			return customer;
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}
		finally{
			closeConnections(myConn,null,myStmt2,null,null);
		}
	}

	public Boolean updateCustomerInfo(HttpServletRequest request) {


		String customer_id = request.getParameter("user_id");
		
		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_update_customer = Helper.UPDATE_RESERVATION_INFO;
			
			myStmt2 = myConn.prepareStatement(sql_update_customer);
			
			myStmt2.setString(1, request.getParameter("fullname"));
			myStmt2.setString(2, request.getParameter("address"));
			myStmt2.setString(3, request.getParameter("country"));
			myStmt2.setInt(4, Integer.parseInt(request.getParameter("mobile_no")));
			myStmt2.setString(5, request.getParameter("email"));
			myStmt2.setInt(6, Integer.parseInt(request.getParameter("adults")));
			myStmt2.setInt(7, Integer.parseInt(request.getParameter("childs")));
			myStmt2.setString(8, request.getParameter("payment_method"));
			myStmt2.setInt(9, Integer.parseInt(customer_id));
			
			int affectedCustomers = myStmt2.executeUpdate();
			
			if(affectedCustomers == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}
				
			
			return true;
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return false;
		}
		finally{
			closeConnections(myConn,null,myStmt2,null,null);
		}
		
	}

	public Boolean deleteCustomer(HttpServletRequest request) {
		
		String user_id = request.getParameter("user_id");
		
		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_delete_customer = Helper.DELETE_CUSTOMER;
			
			myStmt2 = myConn.prepareStatement(sql_delete_customer);
			
			
			myStmt2.setInt(1, Integer.parseInt(user_id));
				
				
			int affectedCustomers = myStmt2.executeUpdate();
			
			if(affectedCustomers == 0) {
				throw new SQLException("Deleting reservation failed, no rows affected.");
			}
				
			
			return true;
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return false;
		}
		finally{
			closeConnections(myConn,null,myStmt2,null,null);
		}
		
	}
}
