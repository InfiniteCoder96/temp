package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import data.BookingDbUtil;
import data.CustomerDbUtil;
import data.PaymentDbUtil;
import data.RoomDbUtil;
import models.Booking;
import models.Customer;

/**
 * Servlet implementation class reservation_controller
 */
@WebServlet("/reservation_controller")
public class reservation_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private RoomDbUtil roomDbUtil;
    private BookingDbUtil bookingDbUtil;
    private CustomerDbUtil customerDbUtil;
    private PaymentDbUtil paymentDbUtil;
    
    @Resource(name="jdbc/web_HRRS")
    private DataSource dataSource;
    
    
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try{
			roomDbUtil = new RoomDbUtil(dataSource);
			bookingDbUtil = new BookingDbUtil(dataSource);
			customerDbUtil = new CustomerDbUtil(dataSource);
			paymentDbUtil = new PaymentDbUtil(dataSource);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String Command = request.getParameter("command");
			
			if(Command == null) {
				Command = "LIST";
			}
			else {
				
				switch(Command){
					case "DB_MIGRATE":
						databaseMigration(request,response);
						break;
					case "ALL":
						getAllBookings(request,response);
						break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void getAllBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Booking> all_bookings = bookingDbUtil.getAllBookings();
		
		request.setAttribute("ALL_BOOKINGS", all_bookings);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/adminDashboard.jsp");
		dispatcher.forward(request, response);
		
	}

	private void databaseMigration(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Boolean db_status = DbController.createDB();
			Boolean bookingTable_status = DbController.createBookingsTable();
			Boolean customerTable_status = DbController.createCustomerTable();
			Boolean roomsTable_status = DbController.createRoomsTable();
			Boolean usersTable_status = DbController.createUsersTable();
			Boolean paymentTable_status = DbController.createPayemntsTable();
			
			if(db_status && bookingTable_status && customerTable_status && roomsTable_status && usersTable_status && paymentTable_status) {
				System.out.println("<======// Database Migrated Successfully //======>");
				response.sendRedirect("home.jsp");
			}
			else {
				System.out.println("<======// Error !!! Something went wrong //======>");
				response.sendRedirect("home.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String Command = request.getParameter("command");
			
			if(Command == null) {
				Command = "LIST";
			}
			else {
				
				switch(Command){
					case "CHECK_AVAILABILITY":
						checkRoomAvailability(request,response);
						break;
					case "RESERVE":
						reserveRoom(request,response);
						break;
					case "CHECK_RESERVATION":
						checkReservation(request,response);
						break;
					case "UPDATE_RESERVE":
						updateReservation(request,response);
						break;
					case "DELETE_RESERVE":
						deleteReservation(request,response);
						break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		List<Integer> available_room_ids = roomDbUtil.getRoomsReservedByUser(request);
		
		Boolean room_update_status = roomDbUtil.updateRoomAvailability("free",available_room_ids);
		
		Boolean reserv_del_status = bookingDbUtil.deleteReservation(request);
		
		Boolean cust_del_status = customerDbUtil.deleteCustomer(request);
		
		if(reserv_del_status && cust_del_status && room_update_status) {
			request.setAttribute("ROOM_STATUS_SUCCESS", "Thanks.Come again, Your reservation has been cancelled successfully !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("ROOM_STATUS_FAIL", "Sorry, Your request was declined because there are conflicts.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
	}

	private void updateReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		Boolean status = customerDbUtil.updateCustomerInfo(request);
		
		if(status) {
			request.setAttribute("ROOM_STATUS_SUCCESS", "Thanks, Your reservation is updated successfully !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("ROOM_STATUS_FAIL", "Sorry, Your request was declined because there are conflicts.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	private void checkReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		Booking booking = bookingDbUtil.getBooking(request);
		Customer customer = customerDbUtil.getCustomer(request);
		
		if(booking != null) {
			
			request.setAttribute("BOOKING_DETAILS", booking);
			request.setAttribute("CUSTOMER_DETAILS", customer);
			
			HttpSession session=request.getSession(false);  
			session.setAttribute("PERMITTED", true);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation-details.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("ROOM_STATUS_FAIL", "Sorry, Your request was declined because there are conflicts.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void reserveRoom(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
		
		List<Integer> available_room_ids = new ArrayList<>();
		
		HttpSession session=request.getSession();  
		available_room_ids = (ArrayList<Integer>)session.getAttribute("AVAIL_ROOM_IDS");
		
		System.out.println(available_room_ids);
		
		Long booked_user = customerDbUtil.insertCustomer(request,session);
		Boolean booking_status = bookingDbUtil.insertBooking(request, booked_user, available_room_ids);
		Boolean payment_status = paymentDbUtil.insertPayement(request, booked_user, available_room_ids);
		Boolean room_update_status = roomDbUtil.updateRoomAvailability("reserve", available_room_ids);
		
		if(booking_status && room_update_status && payment_status) {
			request.setAttribute("ROOM_STATUS_SUCCESS", "Thanks, Your booking is successful ! Your Booking ID : ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("ROOM_STATUS_FAIL", "Sorry, Your request was declined because there are conflicts.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	private void checkRoomAvailability(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		int no_of_rooms = Integer.parseInt(request.getParameter("rooms"));
		
		String check_in_str = request.getParameter("checkIn");
		
		Date check_in = new SimpleDateFormat("yyyy-MM-dd").parse(check_in_str); 
		
		List<Integer> available_room_ids = new ArrayList<>();
		
		available_room_ids = roomDbUtil.checkRoomAvailability(no_of_rooms,check_in);
		
		if(!available_room_ids.isEmpty() && available_room_ids.size() == no_of_rooms) {
			
			List<String> para_list = new ArrayList<>();
			
			para_list.add(request.getParameter("checkIn"));
			para_list.add(request.getParameter("checkOut"));
			para_list.add(request.getParameter("nights"));
			para_list.add(request.getParameter("rooms"));
			para_list.add(request.getParameter("adults"));
			para_list.add(request.getParameter("childs"));
			
			request.setAttribute("RES_DETAILS", para_list);
			
			HttpSession session=request.getSession();  
			
			session.setAttribute("AVAIL_ROOM_IDS", available_room_ids);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/booking.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("ROOM_STATUS", "Sorry no rooms are available on your travel dates");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
