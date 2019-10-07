package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import models.Booking;
import utils.Helper;

public class BookingDbUtil {

	private DataSource dataSource;

	public BookingDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public boolean insertBooking(HttpServletRequest request,Long booked_user, List<Integer> available_rooms) throws ParseException {
		
		java.util.Date check_in = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("checkIn"));
		java.util.Date check_out = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("checkOut"));
		
		java.sql.Date check_in_Date = new java.sql.Date(check_in.getTime());
		java.sql.Date check_out_Date = new java.sql.Date(check_out.getTime());
		
		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;
		List<Integer> available_room_ids = new ArrayList<>();
		
		available_room_ids = available_rooms;
		
		
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_booking_insert = Helper.INSERT_BOOKING_INFO;
			
			myStmt2 = myConn.prepareStatement(sql_booking_insert);
			
			int room_ids = available_room_ids.size() - 1;
			
			for(int i = 0;i <= room_ids;i++) {
				
				myStmt2.setInt(1, available_room_ids.get(i));
				myStmt2.setLong(2, booked_user);
				myStmt2.setDate(3, check_in_Date);
				myStmt2.setDate(4, check_out_Date);
				
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
	
	private void closeConnections(Connection myConn, Statement myStmt1, Statement myStmt2, ResultSet myRs1,
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

	public Booking getBooking(HttpServletRequest request) {
		
		String reservation_id = request.getParameter("reservation_id");
		
		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_search_booking = Helper.SEARCH_BOOKING_INFO;
			
			myStmt2 = myConn.prepareStatement(sql_search_booking);
			
			
				
				myStmt2.setInt(1, Integer.parseInt(reservation_id));
				
				
				myRs = myStmt2.executeQuery();
				
			Booking booking = null;
			
			while(myRs.next()) {
				
				int room_id = myRs.getInt(2);
				Date book_date = myRs.getDate(4);
				Date check_in = myRs.getDate(5);
				Date check_out = myRs.getDate(6);
				
				booking = new Booking(room_id, Integer.parseInt(reservation_id), book_date, check_in, check_out);
				
				
				
			}
				
			
			return booking;
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}
		finally{
			closeConnections(myConn,null,myStmt2,null,null);
		}
		
		
		
	}

	public Boolean deleteReservation(HttpServletRequest request) {


		String user_id = request.getParameter("user_id");
		
		Connection myConn = null;
		PreparedStatement myStmt2 = null;
		
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_delete_booking = Helper.DELETE_RESERVATION;
			
			myStmt2 = myConn.prepareStatement(sql_delete_booking);
			
			
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

	public List<Booking> getAllBookings() {
		
		Connection myConn = null;
		Statement myStmt2 = null;
		ResultSet myRs = null;
		
		List<Booking> all_bookings = new ArrayList<>();
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_all_bookings = Helper.GET_ALL_BOOKING_INFO;
			
			myStmt2 = myConn.createStatement();
			
			myRs = myStmt2.executeQuery(sql_all_bookings);
			
			
			while(myRs.next()) {
				
				Booking booking = new Booking();
				
				booking.setId(myRs.getInt(1));
				booking.setRoom_id(myRs.getInt(2));
				booking.setCustomer_id(myRs.getInt(3));
				booking.setBook_date(myRs.getDate(4));
				booking.setCheck_in(myRs.getDate(5));
				booking.setCheck_out(myRs.getDate(6));
				
				all_bookings.add(booking);
				
			}
			
			return all_bookings;
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}
		finally{
			closeConnections(myConn,null,myStmt2,null,null);
		}
		
	}
	
	
}
