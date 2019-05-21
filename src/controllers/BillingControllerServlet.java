package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DatabaseAccess;
import models.Room_;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Servlet implementation class BillingControllerServlet
 */
@WebServlet("/BillingControllerServlet")
public class BillingControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseAccess dbAccess; 
	
	/**
	 * Connect database by initializing servlet
	 */
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		dbAccess = new DatabaseAccess(jdbcURL, jdbcUsername, jdbcPassword);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillingControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		System.out.println(command);
		try {
			 switch (command) {
			 case "calTotal":
				 getTotalFee(request, response);	
				 break;
			 case "checkOut":
				 checkOut(request, response);
				 break;
			 case "paymentMethod":
				 addPaymentMethod(request, response);
				 break;
			 case "modifyBill":
				 modifyBill(request, response);
				 break;
			 default:
				 
			 }
		}catch (Exception e) {
			throw new ServletException(e);
		} 
	}
	/**
	 * method for update and delete customer bills 
	 */
	private void modifyBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btn = request.getParameter("btn");
		switch(btn) {
		case "update":
			 update(request, response);	
			 break;
		case "delete":
			 delete(request, response);	
			 break;
		}
		System.out.println(btn);
		
	}
	
	/**
	 * method for delete customer bills
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int billID = Integer.parseInt(request.getParameter("billID"));
		Boolean done = deleteBill(billID);
		if(done) {
			System.out.println("bill deleted");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}
	/**
	 * method return boolean if bill deleted
	 */
	private Boolean deleteBill(int billID) {
		try {
			Connection con = dbAccess.connectDB();
			PreparedStatement st = con.prepareStatement("DELETE FROM customerbill WHERE billID=?"); 
			st.setInt(1, billID);

			st.executeUpdate(); 
			st.close(); 
            con.close(); 
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	/**
	 * method for update customer bills
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int billID = Integer.parseInt(request.getParameter("billID"));
		int roomFee = Integer.parseInt(request.getParameter("roomFee"));
		int drinkFee = Integer.parseInt(request.getParameter("drinkFee"));
		int foodFee = Integer.parseInt(request.getParameter("foodFee"));
		int laundryFee = Integer.parseInt(request.getParameter("landryFee"));
		int total = Integer.parseInt(request.getParameter("totCost"));
		String payment = request.getParameter("payment");
		
		Boolean done = updateBill(billID, roomFee, drinkFee, foodFee, laundryFee, total, payment);
		if (done){
			System.out.println("bill updated");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}
	/**
	 * method return boolean if bill updated
	 */
	private Boolean updateBill(int billID, int roomFee, int drinkFee, int foodFee, int laundryFee,
			int total, String payment) {
		
		try {
			Connection con = dbAccess.connectDB();
			PreparedStatement st = con.prepareStatement("UPDATE customerbill SET roomFee=?, drinkFee=?, foodFee=?, laundryFee=?, total=?, paymentType=? WHERE billID=?"); 
			st.setInt(1, roomFee);
			st.setInt(2, drinkFee);
			st.setInt(3, foodFee);
			st.setInt(4, laundryFee);
			st.setInt(5, total);
			st.setString(6, payment);
			st.setInt(7, billID);

			st.executeUpdate(); 
			st.close(); 
            con.close(); 
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * method for print jasper report
	 */
	@SuppressWarnings("unchecked")
	private void printBillReport(int bill, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("report createing"+ bill);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root","1234");

			JasperReport jasperReport = null;
			JasperDesign jasperDesign = null;
			Map parameters = new HashMap();
			String report = "C:/Users/ASUS/Desktop/4th yr 1sem/RESEARCH/OOP/HotelManagement/WebContent/CustomerBill.jrxml";
			parameters.put("billid", bill);
			String path = getServletContext().getRealPath("/WEB-INF/");
			jasperDesign = JRXmlLoader.load(report);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
			OutputStream outStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(byteStream.length);
			outStream.write(byteStream, 0, byteStream.length);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Method for add payment methods
	 */
	private void addPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int billID = Integer.parseInt(request.getParameter("billID"));
		String customerID = request.getParameter("customerId");
		String Card = request.getParameter("cardName");
		String cardNumber = request.getParameter("cardNumber");
		String exDate = request.getParameter("exDate");
		System.out.println(exDate);	
		
		try {
			Connection con = dbAccess.connectDB();
			PreparedStatement st = con.prepareStatement("insert into customercard (customerID, cardName, cardNumber, expireDate) values (?, ?, ?, ?)"); 
			st.setString(1, customerID);
			st.setString(2, Card);
			st.setString(3, cardNumber);
			st.setString(4, exDate);

			st.executeUpdate(); 
			st.close(); 
            con.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printBillReport(billID, request, response);
		
	}
	/**
	 * method for check out customer
	 */
	private void checkOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String btn = request.getParameter("btn");
		switch (btn) {
		case "checkOut":
			 justCheckOut(request, response);	
			 break;
		case "paymentMethod":
			 addPaymentAndCheckOut(request, response);	
			 break;
		}
	}
	/**
	 * method for check out customer and add payment methods for customer  
	 */
	private void addPaymentAndCheckOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int billId = 0;
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String today = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
		LocalDate date = LocalDate.parse(today, formatter);
		
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		String roomIDs[] = request.getParameterValues("roomIDs");
		int totRoomFee = Integer.parseInt(request.getParameter("roomFee"));
		int totDrinkFee = Integer.parseInt(request.getParameter("drinkFee"));
		int totFoodFee = Integer.parseInt(request.getParameter("foodFee"));
		int totLandryFee = Integer.parseInt(request.getParameter("landryFee"));
		int totCost = Integer.parseInt(request.getParameter("totCost"));
		String paymentType = request.getParameter("payment");
		
		try {
			Connection con = dbAccess.connectDB();
			PreparedStatement st = con.prepareStatement("insert into customerbill (customerID, roomFee, drinkFee, foodFee, laundryFee, total, paymentType) values (?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS); 
			st.setInt(1, customerId);
			st.setInt(2, totRoomFee);
			st.setInt(3, totDrinkFee);
			st.setInt(4, totFoodFee);
			st.setInt(5, totLandryFee);
			st.setInt(6, totCost);
			st.setString(7, paymentType);
			st.executeUpdate(); 
			ResultSet rs = st.getGeneratedKeys();
			
			if(rs.next()){
				billId = rs.getInt(1);
			}
			for(int i = 0; i< roomIDs.length; i++) {
				PreparedStatement stmt = con.prepareStatement("update customerroom set checkOutDate=? where customerID=? AND roomID=?"); 
				stmt.setObject(1, date);
				stmt.setInt(2, customerId);
				stmt.setString(3, roomIDs[i]);
				stmt.executeUpdate();
				System.out.println("room check out"+ roomIDs[i]);
			}
			st.close(); 
            con.close(); 
            System.out.println("customer check out ");
            System.out.println("bill id "+ billId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("paymentMethod.jsp");
		request.setAttribute("customerId", customerId);
		request.setAttribute("billID", billId);
		dispatcher.forward(request, response);
		
	}
	/**
	 * method for check out customer
	 */
	private void justCheckOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int billId = 0;
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String today = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
		LocalDate date = LocalDate.parse(today, formatter);
		
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		String roomIDs[] = request.getParameterValues("roomIDs");
		int totRoomFee = Integer.parseInt(request.getParameter("roomFee"));
		int totDrinkFee = Integer.parseInt(request.getParameter("drinkFee"));
		int totFoodFee = Integer.parseInt(request.getParameter("foodFee"));
		int totLandryFee = Integer.parseInt(request.getParameter("landryFee"));
		int totCost = Integer.parseInt(request.getParameter("totCost"));
		String paymentType = request.getParameter("payment");
		
		try {
			Connection con = dbAccess.connectDB();
			PreparedStatement st = con.prepareStatement("insert into customerbill (customerID, roomFee, drinkFee, foodFee, laundryFee, total, paymentType) values (?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS); 
			st.setInt(1, customerId);
			st.setInt(2, totRoomFee);
			st.setInt(3, totDrinkFee);
			st.setInt(4, totFoodFee);
			st.setInt(5, totLandryFee);
			st.setInt(6, totCost);
			st.setString(7, paymentType);
			st.executeUpdate(); 
			ResultSet rs = st.getGeneratedKeys();
			
			if(rs.next()){
				billId = rs.getInt(1);
			}
			for(int i = 0; i< roomIDs.length; i++) {
				PreparedStatement stmt = con.prepareStatement("update customerroom set checkOutDate=? where customerID=? AND roomID=?"); 
				stmt.setObject(1, date);
				stmt.setInt(2, customerId);
				stmt.setString(3, roomIDs[i]);
				stmt.executeUpdate();
				System.out.println("room check out"+ roomIDs[i]);
			}
			st.close(); 
            con.close(); 
            System.out.println("customer check out ");
            System.out.println("bill id "+ billId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printBillReport(billId, request, response);
	}
	/**
	 * method to calculate total cost for customer check outs
	 */
	private void getTotalFee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long totalRoomFee = 0;
		int totalDrinkFee = 0;
		int totalFoodFee = 0;
		int totalLaundryFee = 0;
		int cID = Integer.parseInt(request.getParameter("customeId"));
		String rooms[] = request.getParameterValues("checkCheckOut");
		String drinks[] = request.getParameterValues("drink");
		String drinkQ[] =request.getParameterValues("drinkQ");
		String foods[] = request.getParameterValues("food");
		String foodQ[] = request.getParameterValues("foodQ");
		String cloths[] = request.getParameterValues("cloth");
		String clothQ[] = request.getParameterValues("clothQ");
		
		if(rooms != null ) {
			totalRoomFee = getTotalRoomFee(rooms, cID);
		}else {
			totalRoomFee = 0;
		}
		if(drinks != null ) {
			totalDrinkFee = getTotalDrinkFee(drinks, drinkQ);
		}else {
			totalDrinkFee = 0;
		}
		if(foods != null ) {
			totalFoodFee = getTotalFoodFee(foods, foodQ);
		}else {
			totalFoodFee = 0;
		}
		if(cloths!= null ) {
			totalLaundryFee = getTotalLandryFee(cloths, clothQ);
		}else {
			totalLaundryFee = 0;
		}
		
//		for(String s : roomIDs) {
//			System.out.println(s);
//		}
		long totalCost = totalRoomFee + totalDrinkFee + totalFoodFee + totalLaundryFee;
		
		System.out.println("Total Room Fee : "+totalRoomFee);
		System.out.println("Total Drink Fee : "+totalDrinkFee);
		System.out.println("Total Food Fee : "+totalFoodFee);
		System.out.println("Total Laundry Fee : "+totalLaundryFee);
		System.out.println("Total Cost : "+totalCost);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("checkOut.jsp");
		request.setAttribute("customerId", cID);
		request.setAttribute("roomIDs", rooms);
		request.setAttribute("totalRoomFee", totalRoomFee);
		request.setAttribute("totalDrinkFee", totalDrinkFee);
		request.setAttribute("totalFoodFee", totalFoodFee);
		request.setAttribute("totalLaundryFee", totalLaundryFee);
		request.setAttribute("totalCost", totalCost);
		dispatcher.forward(request, response);
	}
	/**
	 * method to calculate total laundry cost of customer
	 */
	private int getTotalLandryFee(String[] cloths, String[] clothQ) {
		int totalLaundryFee = 0;
		List<String> list = new ArrayList<String>();
		for(String s: clothQ) {
			if(s != null && s.length()>0) {
				list.add(s);
			}
		}
		clothQ = list.toArray(new String[list.size()]);
		for(int i = 0; i< cloths.length; i++) {
			totalLaundryFee += Integer.parseInt(cloths[i])*Integer.parseInt(clothQ[i]);
		}
		System.out.println("Laundry Fee : "+totalLaundryFee);
		return totalLaundryFee;
	}
	/**
	 * method to calculate total food cost of customer
	 */
	private int getTotalFoodFee(String[] foods, String[] foodQ) {
		int totalFoodFee = 0;
		List<String> list = new ArrayList<String>();
		for(String s: foodQ) {
			if(s != null && s.length()>0) {
				list.add(s);
			}
		}
		foodQ = list.toArray(new String[list.size()]);
		for(int i = 0; i< foods.length; i++) {
			totalFoodFee += Integer.parseInt(foods[i])*Integer.parseInt(foodQ[i]);
		}
		System.out.println("Food Fee : "+totalFoodFee);
		return totalFoodFee;
	}
	/**
	 * method to calculate total beverages cost of customer
	 */
	private int getTotalDrinkFee(String[] drinks, String[] drinkQ) {
		int totaldrinkFee = 0;
		List<String> list = new ArrayList<String>();
		for(String s: drinkQ) {
			if(s != null && s.length()>0) {
				list.add(s);
			}
		}
		drinkQ = list.toArray(new String[list.size()]);
		for(int i = 0; i< drinks.length; i++) {
			totaldrinkFee += Integer.parseInt(drinks[i])*Integer.parseInt(drinkQ[i]);
		}
		System.out.println("Drink Fee : "+totaldrinkFee);
		return totaldrinkFee;
	}
	/**
	 * method to calculate total room cost of customer
	 */
	private long getTotalRoomFee(String [] rooms, int id) {
		List<Room_> room = new ArrayList<>();
		try {
			Connection con = dbAccess.connectDB();
			for(int i = 0;i<rooms.length;i++) {
				PreparedStatement st = con.prepareStatement("SELECT cr.roomID AS roomid, r.price AS Price, r.serviceChargePerDay AS ServiceChargePerDay, cr.checkInDate AS CheckInDate FROM room r, customerroom cr WHERE cr.checkOutDate IS NUll AND cr.roomID=? AND cr.customerID=?");
				st.setString(1, rooms[i]);
				st.setInt(2, id);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int custRoomID = rs.getInt("roomid");
					int roomprice = rs.getInt("Price");
					int serviceChargePerDay = rs.getInt("ServiceChargePerDay");
					String checkindate = rs.getString("CheckInDate");
					
					Room_ r = new Room_(custRoomID, roomprice, serviceChargePerDay, checkindate);
					room.add(r);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		long roomFee = 0;
		LocalDate localDate = LocalDate.now();
		for(Room_ r : room) {
			String today = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
			LocalDate firstDate = LocalDate.parse(r.getCheckInDate(), formatter);
			LocalDate secondDate = LocalDate.parse(today, formatter);
			long days = ChronoUnit.DAYS.between(firstDate, secondDate);
			if(days == 0) {
				roomFee += r.getPrice()+r.getServiceChargePerDay();
			}else {
				roomFee += (r.getPrice()+r.getServiceChargePerDay())*days;
			}
		}
		System.out.println("room fee : "+roomFee);
		return roomFee;
	}


}
