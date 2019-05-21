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

public class RoomDbUtil {

	private DataSource dataSource;

	public RoomDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<Integer> checkRoomAvailability(int no_of_rooms,java.util.Date check_in) {
		
		Connection myConn = null;
		Statement myStmt1 = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs1 = null;
		ResultSet myRs2 = null;
		
		List<Integer> room_ids = new ArrayList<>();
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = Helper.CHECK_AVAILABILITY_1;
			
			myStmt1 = myConn.createStatement();
			
			myRs1 = myStmt1.executeQuery(sql);
			
			int available_rooms = 0;
			int checkOut_rooms = 0;
			
			
			while(myRs1.next()) {
				
				if(available_rooms >= no_of_rooms) {
					
					break;
				}

				available_rooms++;
				room_ids.add(myRs1.getInt(1));
				
			}			
			
			if(available_rooms == no_of_rooms) {
				return room_ids;
			}
			else {
				
				try {
					String sql2 = Helper.CHECK_AVAILABILITY_2;
					
					myStmt2 = myConn.prepareStatement(sql2);
					
					java.sql.Date sDate = new java.sql.Date(check_in.getTime());
					
					myStmt2.setDate(1, sDate);
					
					myRs2 = myStmt2.executeQuery();
								
					System.out.println(sDate);
					
					while(myRs2.next()) {
						
						checkOut_rooms++;
						room_ids.add(myRs2.getInt(2));
						
						if(checkOut_rooms >= no_of_rooms) {
							break;
						}
						
					}
					
					available_rooms = checkOut_rooms + available_rooms;
					
					System.out.println("req out:" + no_of_rooms);
					System.out.println("chk out:" + checkOut_rooms);
					System.out.println("available:" + available_rooms);
					System.out.println("room_ids:" + room_ids);		
					
					return room_ids;
					
				}
				catch(Exception e) {
					e.printStackTrace();
					return room_ids;
				}
				finally{
					closeConnections(myConn,myStmt1,myStmt2,myRs1,myRs2);
				}
			}
				
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return room_ids;
			
		}
		finally{
			closeConnections(myConn,myStmt1,myStmt2,myRs1,myRs2);
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

	public boolean updateRoomAvailability(String command, List<Integer> available_rooms) {
		
		

		Connection myConn = null;
		PreparedStatement myStmt3 = null;
		List<Integer> available_room_ids = new ArrayList<>();
		
		available_room_ids = available_rooms;
		
		if(command.equals("reserve")) {
			try {
				
				myConn = dataSource.getConnection();
				
				int room_ids = available_room_ids.size() - 1;
				
				String sql_room_update = Helper.UPDATE_ROOM_AVAILABILITY;
				
				myStmt3 = myConn.prepareStatement(sql_room_update);
				
				for(int i = 0;i <= room_ids;i++) {
					
					myStmt3.setBoolean(1, false);
					myStmt3.setLong(2, available_room_ids.get(i));
					
					int affectedRooms = myStmt3.executeUpdate();
					
					if(affectedRooms == 0) {
						closeConnections(myConn,null,myStmt3,null,null);
						throw new SQLException("Updating room failed, no rows affected.");
					}
				}
				
				return true;
				
			}
			catch(Exception e) {
				
				e.printStackTrace();
				return false;
			}
			finally{
				closeConnections(myConn,null,myStmt3,null,null);
			}
		}
		else if(command.equals("free")) {
			try {
				
				myConn = dataSource.getConnection();
				
				int room_ids = available_room_ids.size() - 1;
				
				String sql_room_update = Helper.UPDATE_ROOM_AVAILABILITY;
				
				myStmt3 = myConn.prepareStatement(sql_room_update);
				
				for(int i = 0;i <= room_ids;i++) {
					
					myStmt3.setBoolean(1, true);
					myStmt3.setLong(2, available_room_ids.get(i));
					
					int affectedRooms = myStmt3.executeUpdate();
					
					if(affectedRooms == 0) {
						closeConnections(myConn,null,myStmt3,null,null);
						throw new SQLException("Updating room failed, no rows affected.");
					}
				}
				
				return true;
				
			}
			catch(Exception e) {
				
				e.printStackTrace();
				return false;
			}
			finally{
				closeConnections(myConn,null,myStmt3,null,null);
			}
		}
		return false;
		
		
	}


	public List<Integer> getRoomsReservedByUser(HttpServletRequest request) {
		
		String user_id = request.getParameter("user_id");
		System.out.println("userID:"+user_id);
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		List<Integer> reserved_room_ids = new ArrayList<>();
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql_search_rooms = Helper.SEARCH_ROOMS_RESERVED_BY_CUSTOMER;
			System.out.println("res rooms1:"+sql_search_rooms);
			myStmt = myConn.prepareStatement(sql_search_rooms);
			
			myStmt.setInt(1, Integer.parseInt(user_id));
			System.out.println("res rooms2:"+myStmt);
			myRs = myStmt.executeQuery();
			System.out.println("res rooms3:"+myRs);
			while(myRs.next()) {
				
				int room_id = myRs.getInt("room_id");
				System.out.println(room_id);
				
				reserved_room_ids.add(room_id);
				
			}
				
			System.out.println("res rooms4:"+reserved_room_ids);
			return reserved_room_ids;
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}
		finally{
			closeConnections(myConn,null,myStmt,null,null);
		}
		
	}
}
