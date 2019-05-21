package utils;

public class Helper {

	/*
	 * Database Configuration Parameters
	 */
	public static final String DB_URL = "jdbc:mysql://localhost:3306/";
	public static final String HOST_NAME = "localhost";
	public static final String DATABASE = "hrrs";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "";
	
	/*
	 * Database Tables
	 */
	public static final String TABLE_ROOMS = "rooms";
	public static final String TABLE_BOOKINGS = "bookings";
	public static final String TABLE_CUSTOMERS = "customers";
	
	/*
	 * SQL Queries
	 */
	public static final String CHECK_AVAILABILITY_1 = "SELECT * FROM " + TABLE_ROOMS + " WHERE availability = true";
	public static final String CHECK_AVAILABILITY_2 = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE check_out < ?";
	public static final String INSERT_BOOKING_INFO = "INSERT INTO " + TABLE_BOOKINGS + " (room_id,customer_id,check_in,check_out) VALUES (?,?,?,?)";
	public static final String INSERT_CUSTOMER_INFO = "INSERT INTO " + TABLE_CUSTOMERS + " (fullname,address,country,mobile,email,adult_visitors,child_visitors,payment_method,nights,rooms) VALUES (?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_ROOM_AVAILABILITY = "UPDATE " + TABLE_ROOMS + " SET availability = ? WHERE id = ?";
	public static final String SEARCH_BOOKING_INFO = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE customer_id = ?";
	public static final String SEARCH_CUSTOMER_INFO = "SELECT * FROM " + TABLE_CUSTOMERS + " WHERE id = ?";
	public static final String UPDATE_RESERVATION_INFO = "UPDATE " + TABLE_CUSTOMERS + " SET fullname = ?, address = ?, country = ?, mobile = ?, email = ?, adult_visitors = ?, child_visitors = ?, payment_method = ? WHERE id = ?";
	public static final String DELETE_CUSTOMER = "DELETE FROM " + TABLE_CUSTOMERS + " WHERE id = ?";
	public static final String DELETE_RESERVATION = "DELETE FROM " + TABLE_BOOKINGS + " WHERE customer_id = ?";
	public static final String SEARCH_ROOMS_RESERVED_BY_CUSTOMER = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE customer_id = ?";
	
	
}
