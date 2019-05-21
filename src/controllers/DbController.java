package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import utils.Helper;


public class DbController {

	private static String dbURL = Helper.DB_URL;
	private static String dbUser = Helper.USERNAME;
	private static String dbPassword = Helper.PASSWORD;
	private static String dbName = Helper.DATABASE;
	
	private static String room_table = Helper.TABLE_ROOMS;
	private static String bookings_table = Helper.TABLE_BOOKINGS;
	private static String customers_table = Helper.TABLE_CUSTOMERS;
	
	/**
	 * Creates the database connection to the specified database
	 * 
	 * @return con of type Connection (returns null if connection is
	 *         unsuccessful)
	 */
	public static Connection createConnection() {
		Connection con = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error: unable to load driver class!");
				System.exit(1);
			}
			con = DriverManager.getConnection(dbURL + dbName, dbUser, dbPassword);
		} catch (SQLException sqe) {
			System.out.println("Error: While Creating connection to database");
			sqe.printStackTrace();
		}
		return con;
	}
	/**
	 * Creates the database if it doesn't exist
	 * 
	 * @return status of type boolean
	 */
	public static boolean createDB() {
		boolean status = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

			stmt = conn.createStatement();

			String sql = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
			stmt.executeUpdate(sql);
			status = true;
		} catch (Exception e) {
			status = false;
			System.out.println("Error : While Creating Database");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println("Error : While Closing Connection");
			}
		}
		return status;
	}

	/**
	 * Creates the rooms table if it doesn't exist in the database
	 * 
	 * @return status of type boolean
	 */
	public static boolean createRoomsTable() {

		boolean status = false;
		Connection con = DbController.createConnection();

		try {
			Statement st = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS " + room_table 
					+ "("
					+ "id int NOT NULL AUTO_INCREMENT,"
					+ " category VARCHAR(250),"
					+ " capacity INT,"
					+ " availability BOOLEAN DEFAULT true,"
					+ " primary key (id)"
					+ ");";
			

			st.executeUpdate(sql);
			status = true;
			
		} catch (SQLException sqe) {
			System.out.println("Error : While Creating Table (Table Already Exists)");
			sqe.printStackTrace();
			status = false;
		}

		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error : While Closing Connection");
		}

		return status;
	}
	
	/**
	 * Creates the bookings table if it doesn't exist in the database
	 * 
	 * @return status of type boolean
	 */
	public static boolean createBookingsTable() {

		boolean status = false;
		Connection con = DbController.createConnection();

		try {
			Statement st = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS " + bookings_table 
					+ "("
					+ "id int NOT NULL AUTO_INCREMENT,"
					+ " room_id INT,"
					+ " customer_id INT,"
					+ " book_date DATETIME DEFAULT CURRENT_TIMESTAMP,"
					+ " check_in DATE,"
					+ " check_out DATE,"
					+ " primary key (id)"
					+ ");";
			

			st.executeUpdate(sql);
			status = true;
			
		} catch (SQLException sqe) {
			System.out.println("Error : While Creating Table (Table Already Exists)");
			sqe.printStackTrace();
			status = false;
		}

		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error : While Closing Connection");
		}

		return status;
	}
	
	/**
	 * Creates the bookings table if it doesn't exist in the database
	 * 
	 * @return status of type boolean
	 */
	public static boolean createCustomerTable() {

		boolean status = false;
		Connection con = DbController.createConnection();

		try {
			Statement st = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS " + customers_table
					+ "("
					+ "id int NOT NULL AUTO_INCREMENT,"
					+ " fullname VARCHAR(255),"
					+ " address VARCHAR(255),"
					+ " country VARCHAR(120),"
					+ " mobile INT,"
					+ " email VARCHAR(120),"
					+ " adult_visitors DATE,"
					+ " child_visitors DATE,"
					+ " payment_method VARCHAR(20),"
					+ " nights INT,"
					+ " rooms INT,"
					+ " primary key (id)"
					+ ");";
			

			st.executeUpdate(sql);
			status = true;
			
		} catch (SQLException sqe) {
			System.out.println("Error : While Creating Table (Table Already Exists)");
			sqe.printStackTrace();
			status = false;
		}

		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error : While Closing Connection");
		}

		return status;
	}
}
