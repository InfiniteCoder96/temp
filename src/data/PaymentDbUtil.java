package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import utils.Helper;

public class PaymentDbUtil {

	private DataSource dataSource;

	public PaymentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Boolean insertPayement(HttpServletRequest request, Long booked_user, List<Integer> available_rooms) {
		
		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		List<Integer> available_room_ids = new ArrayList<>();
		
		available_room_ids = available_rooms;
		
		
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_payment_insert = Helper.INSERT_PAYMENT;
			
			myStmt2 = myConn.prepareStatement(sql_payment_insert);
			
			int room_ids = available_room_ids.size() - 1;
			
			for(int i = 0;i <= room_ids;i++) {
				
				
				
				myStmt2.setLong(1, booked_user);
				myStmt2.setInt(2, available_room_ids.get(i));
				myStmt2.setString(3, request.getParameter("amount"));
				
				int affectedBookings = myStmt2.executeUpdate();
				
				if(affectedBookings == 0) {
					closeConnections(myConn,null,myStmt2,null,null);
					throw new SQLException("Creating booking failed, no rows affected.");
				}
				
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
