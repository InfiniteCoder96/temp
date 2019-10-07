package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import utils.Helper;

public class UserDbUtil {
	
	private DataSource dataSource;

	public UserDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Boolean validateUser(HttpServletRequest request) {

		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_validate_user = Helper.VALIDATE_USER;
			
			myStmt2 = myConn.prepareStatement(sql_validate_user);
			
			
			myStmt2.setString(1, request.getParameter("username"));
			myStmt2.setString(2, request.getParameter("password"));
				
			myRs = myStmt2.executeQuery();
			
			if(myRs.next()) {				
				return true;
			}
			else {
				return false;
			}
			
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return false;
		}
		finally{
			closeConnections(myConn,null,myStmt2,null,null);
		}
		
	}
	
	public boolean registerUser(HttpServletRequest request) {
		
		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_register_user = Helper.REGISTER_USER;
			
			myStmt2 = myConn.prepareStatement(sql_register_user);

			myStmt2.setString(1, request.getParameter("username"));
			myStmt2.setString(2, request.getParameter("password"));
			myStmt2.setString(3, request.getParameter("email"));
			myStmt2.setString(4, "user");

			int affectedRows = myStmt2.executeUpdate();
			
			if(affectedRows == 0) {
				closeConnections(myConn,null,myStmt2,null,null);
				throw new SQLException("Creating booking failed, no rows affected.");
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
}
