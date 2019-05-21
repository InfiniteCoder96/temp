package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DatabaseAccess;
import models.Bar;
import models.CustomerBill;
import models.Customer_;
import models.Food;
import models.Laundry;
import models.Room_;

//import org.apache.jasper.tagplugins.jstl.core.ForEach;




/**
 * Servlet implementation class customerControllerServlet
 */
@WebServlet("/customerControllerServlet")
public class customerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseAccess dbAccess; 
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		dbAccess = new DatabaseAccess(jdbcURL, jdbcUsername, jdbcPassword);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customerControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			 switch (action) {
			 case "/showCustomer":
				 listCheckinCustomers(request, response);
				 break;
			 case "/show":
				 listCustomer(request, response);
				 break;
			 case "/billing":
				 billingForm(request, response);
				 break;
			 case "/customer":
				 searchCustomer(request, response);
				 break;
			 case "/customerEditBillSearch":
				 searchCustomerForEditBill(request, response);
				 break;
			 case "/editBill":
				 getCustomerBill(request, response);
				 break;
			 case "/getBill":
				 getBill(request, response);
				 break;
			 default:
				 
			 }
		}catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	/**
	 * method that show details in updatedelete.jsp for modify bill
	 */
	private void getBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int billid = Integer.parseInt(request.getParameter("billid"));
		List<CustomerBill> billList = billById(billid);
		request.setAttribute("billList", billList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("updateDeleteBill.jsp");
		dispatcher.forward(request, response);	
	}
	/**
	 * method return customer bills 
	 */
	private List<CustomerBill> billById(int billid) throws SQLException {
		Connection con = dbAccess.connectDB();
		List<CustomerBill> billList = new ArrayList<>();
		PreparedStatement statement = null;
		
		statement = con.prepareStatement("SELECT * FROM customerbill WHERE billID=?");
		statement.setInt(1, billid);	
		ResultSet resultsSet = statement.executeQuery();
		
		while (resultsSet.next()) {
			int billID = resultsSet.getInt("billID");
			int customerID = resultsSet.getInt("customerID");
			int roomFee = resultsSet.getInt("roomFee");
			int drinkFee = resultsSet.getInt("drinkFee");
			int foodFee = resultsSet.getInt("foodFee");
			int laundryFee = resultsSet.getInt("laundryFee");
			int total = resultsSet.getInt("total");
			String paymentType = resultsSet.getString("paymentType");
	
			CustomerBill bill = new CustomerBill(billID, customerID, roomFee, drinkFee, foodFee, laundryFee, total, paymentType);
			billList.add(bill);
		}
		resultsSet.close();
		statement.close();
		dbAccess.disconnectDB();
		
		return billList;
	}
	/**
	 * method show all customer bills
	 */
	private void getCustomerBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<CustomerBill> billList = CustomerBills(id);
		request.setAttribute("billList", billList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditBill.jsp");
		dispatcher.forward(request, response);	
	}
	
	/**
	 * method return all bills of single customer
	 */
	private List<CustomerBill> CustomerBills(int id) throws SQLException {
		Connection con = dbAccess.connectDB();
		List<CustomerBill> billList = new ArrayList<>();
		PreparedStatement statement = null;
		
		statement = con.prepareStatement("SELECT * FROM customerbill WHERE customerID=?");
		statement.setInt(1, id);	
		ResultSet resultsSet = statement.executeQuery();
		
		while (resultsSet.next()) {
			int billID = resultsSet.getInt("billID");
			int customerID = resultsSet.getInt("customerID");
			int roomFee = resultsSet.getInt("roomFee");
			int drinkFee = resultsSet.getInt("drinkFee");
			int foodFee = resultsSet.getInt("foodFee");
			int laundryFee = resultsSet.getInt("laundryFee");
			int total = resultsSet.getInt("total");
			String paymentType = resultsSet.getString("paymentType");
	
			CustomerBill bill = new CustomerBill(billID, customerID, roomFee, drinkFee, foodFee, laundryFee, total, paymentType);
			billList.add(bill);
		}
		resultsSet.close();
		statement.close();
		dbAccess.disconnectDB();
		
		return billList;
	}
	/**
	 * method show all customers
	 */
	private void searchCustomerForEditBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String cname= request.getParameter("custName");
		List<Customer_> customerList = searchAllCustomers(cname);
		request.setAttribute("customerList", customerList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customerList.jsp");
		dispatcher.forward(request, response);
		
	}
	/**
	 * method return matching customer by given name or string
	 */
	public List<Customer_> searchAllCustomers(String cname) throws SQLException {
		Connection con = dbAccess.connectDB();
		List<Customer_> customerList = new ArrayList<>();
		PreparedStatement statement = null;
		
		statement = con.prepareStatement("SELECT * FROM customers WHERE customerName LIKE ?");
		statement.setString(1, "%"+ cname + "%");	
		ResultSet resultsSet = statement.executeQuery();
		
		while (resultsSet.next()) {
			int id = resultsSet.getInt("customerID");
			String name = resultsSet.getString("customerName");
			String country = resultsSet.getString("country");
			String NICPassport = resultsSet.getString("NIC/Passport");
			int contact = resultsSet.getInt("contactNo");
			
			Customer_ customer = new Customer_(id, name, country, NICPassport, contact);
			customerList.add(customer);
		}
		resultsSet.close();
		statement.close();
		dbAccess.disconnectDB();
		
		return customerList;
		
	}
	/**
	 * method show all check in customers
	 */
	private void searchCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String cname= request.getParameter("custName");
		List<Customer_> customerList = searchCustomer(cname);
		request.setAttribute("customerList", customerList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer.jsp");
		dispatcher.forward(request, response);
		
	}
	/**
	 * method return all customers who are not yet check out and matching given name
	 */
	public List<Customer_> searchCustomer(String cname) throws SQLException {
		Connection con = dbAccess.connectDB();
		List<Customer_> customerList = new ArrayList<>();
		PreparedStatement statement = null;
		
//		statement = con.prepareStatement("SELECT * FROM customers WHERE customerName LIKE ?");
		statement = con.prepareStatement("SELECT DISTINCT c.* FROM customers c, customerroom cr WHERE c.customerID = cr.customerID AND cr.checkOutDate IS null AND c.customerName LIKE ?");
		statement.setString(1, "%"+ cname + "%");	
		ResultSet resultsSet = statement.executeQuery();
		
		while (resultsSet.next()) {
			int id = resultsSet.getInt("customerID");
			String name = resultsSet.getString("customerName");
			String country = resultsSet.getString("country");
			String NICPassport = resultsSet.getString("NIC/Passport");
			int contact = resultsSet.getInt("contactNo");
			
			Customer_ customer = new Customer_(id, name, country, NICPassport, contact);
			customerList.add(customer);
		}
		resultsSet.close();
		statement.close();
		dbAccess.disconnectDB();
		
		return customerList;
		
	}
	
	/**
	 * method show all costing available for customers
	 */
	private void billingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Room_> custRoom = customerRoom(id);
		List<Bar> barlist = drinkList();
		List<Food> foodlist = foodList();
		List<Laundry> laundrylist = laundryList();
		RequestDispatcher dispatcher = request.getRequestDispatcher("billing.jsp");
		request.setAttribute("customerId", id);
        request.setAttribute("custRoom", custRoom);
        request.setAttribute("barlist", barlist);
        request.setAttribute("foodlist", foodlist);
        request.setAttribute("laundrylist", laundrylist);
        dispatcher.forward(request, response);
		
	}
	/**
	 * method return all rooms that customer have booked
	 */
	public List<Room_> customerRoom(int id) {
		
		List<Room_> roomList = new ArrayList<>();
		try {
			Connection con = dbAccess.connectDB();
			String sql = "SELECT cr.roomID AS roomid, r.price AS Price, r.serviceChargePerDay AS ServiceChargePerDay, cr.checkInDate AS CheckInDate FROM room r, customerroom cr WHERE cr.checkOutDate IS NUll AND cr.roomID=r.roomID AND cr.customerID="+id;
			Statement stmt = con.createStatement();
			ResultSet resultsSet = stmt.executeQuery(sql);
			
			while (resultsSet.next()) {
				int custRoomID = resultsSet.getInt("roomid");
				int roomprice = resultsSet.getInt("Price");
				int serviceChargePerDay = resultsSet.getInt("ServiceChargePerDay");
				String checkindate = resultsSet.getString("CheckInDate");
				
				Room_ room = new Room_(custRoomID, roomprice, serviceChargePerDay, checkindate);
				roomList.add(room);
			}
			resultsSet.close();
			stmt.close();
			dbAccess.disconnectDB();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return roomList;
	}
	/**
	 * method return all beverages available
	 */
	public List<Bar> drinkList() {
		
		List<Bar> listDrink = new ArrayList<>();
		try {
			Connection con = dbAccess.connectDB();
			String sql = "SELECT * FROM bar";
			Statement stmt = con.createStatement();
			ResultSet resultsSet = stmt.executeQuery(sql);
			
			while (resultsSet.next()) {
				int drinkid = resultsSet.getInt("drinkID");
				String drinkname = resultsSet.getString("drinkName");
				int drinkprice = resultsSet.getInt("price");
				
				Bar drink = new Bar(drinkid, drinkname, drinkprice);
				listDrink.add(drink);
			}
			resultsSet.close();
			stmt.close();
			dbAccess.disconnectDB();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listDrink;
	}
	/**
	 * method return all foods available
	 */
	public List<Food> foodList() {
		
		List<Food> listFood = new ArrayList<>();
		try {
			Connection con = dbAccess.connectDB();
			String sql = "SELECT * FROM food";
			Statement stmt = con.createStatement();
			ResultSet resultsSet = stmt.executeQuery(sql);
			
			while (resultsSet.next()) {
				int foodid = resultsSet.getInt("foodID");
				String foodname = resultsSet.getString("foodName");
				int foodprice = resultsSet.getInt("price");
				
				Food food = new Food(foodid, foodname, foodprice);
				listFood.add(food);
			}
			resultsSet.close();
			stmt.close();
			dbAccess.disconnectDB();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listFood;
	}
	/**
	 * method return all laundry items available
	 */
	public List<Laundry> laundryList() {
		
		List<Laundry> listLaundry = new ArrayList<>();
		try {
			Connection con = dbAccess.connectDB();
			String sql = "SELECT * FROM laundry";
			Statement stmt = con.createStatement();
			ResultSet resultsSet = stmt.executeQuery(sql);
			
			while (resultsSet.next()) {
				int clothid = resultsSet.getInt("clothID");
				String cloth = resultsSet.getString("cloth");
				int washingprice = resultsSet.getInt("price");
				
				Laundry laundry = new Laundry(clothid, cloth, washingprice);
				listLaundry.add(laundry);
			}
			resultsSet.close();
			stmt.close();
			dbAccess.disconnectDB();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listLaundry;
	}
	/**
	 * method show all check in customers
	 */
	private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		List<Customer_> customerList = listCustomers();
		request.setAttribute("customerList", customerList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * method show all customers
	 */
	private void listCheckinCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		List<Customer_> customerList = customerRooms();
		request.setAttribute("customerList", customerList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customerList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * method return all customers not yet check out
	 */
	public List<Customer_> listCustomers() throws SQLException {
		Connection con = dbAccess.connectDB();
		List<Customer_> customerList = new ArrayList<>();
		
		String sql = "SELECT DISTINCT c.* FROM customers c, customerroom cr WHERE c.customerID = cr.customerID AND cr.checkOutDate IS null";
		Statement stmt = con.createStatement();
		ResultSet resultsSet = stmt.executeQuery(sql);
		
		while (resultsSet.next()) {
			int id = resultsSet.getInt("customerID");
			String name = resultsSet.getString("customerName");
			String country = resultsSet.getString("country");
			String NICPassport = resultsSet.getString("NIC/Passport");
			int contact = resultsSet.getInt("contactNo");
			
			Customer_ customer = new Customer_(id, name, country, NICPassport, contact);
			customerList.add(customer);
		}
		resultsSet.close();
		stmt.close();
		dbAccess.disconnectDB();
		
		return customerList;
		
	}
	/**
	 * method return all customers
	 */
	public List<Customer_> customerRooms() throws SQLException {
		Connection con = dbAccess.connectDB();
		List<Customer_> custList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM customers ";
			Statement stmt = con.createStatement();
			ResultSet resultsSet = stmt.executeQuery(sql);
			
			while (resultsSet.next()) {
				int customerid = resultsSet.getInt("customerID");
				String customername = resultsSet.getString("customerName");
				String country = resultsSet.getString("country");
				String NICPassport = resultsSet.getString("NIC/Passport");
				int contact = resultsSet.getInt("contactNo");
				Customer_ cust = new Customer_(customerid, customername, country, NICPassport, contact);
				custList.add(cust);
				//custList.forEach((n) -> System.out.println(n.getRoomId()));
			}
			resultsSet.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		dbAccess.disconnectDB();
		
		return custList;
		
	}
}
