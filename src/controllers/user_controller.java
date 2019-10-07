package controllers;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import data.CustomerDbUtil;
import data.UserDbUtil;

/**
 * Servlet implementation class user_controller
 */
@WebServlet("/user_controller")
public class user_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDbUtil userDbUtil;
	
    @Resource(name="jdbc/web_HRRS")
    private DataSource dataSource;
    
    
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try{
			userDbUtil = new UserDbUtil(dataSource);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// read the command
				String command = request.getParameter("command");
				
				try {
					
					// If the command is missing, then default to listing users
					if(command == null) {
						command = "LIST";
					}
					
					switch(command) {
								
						
						case "VALIDATE":validateUser(request,response);
									break;
						case "SIGNUP":registerUser(request,response);
									break;
						
						default:;
						
					}
					
				} catch (Exception exc) {
					
					throw new ServletException(exc);
				}
	}

	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Boolean validated = userDbUtil.registerUser(request);
		
		if(validated) {
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(10*60);
			
			session.setAttribute("user",  request.getParameter("username"));
			
			request.setAttribute("VALID_USER", "Thanks.Come again, Your reservation has been cancelled successfully !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("INVALID_USER", "Sorry Username has taken. Please Try again with different Username");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void validateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Boolean validated = userDbUtil.validateUser(request);
		
		if(validated) {
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(10*60);
			
			session.setAttribute("user",  request.getParameter("username"));
			
			request.setAttribute("VALID_USER", "Thanks.Come again, Your reservation has been cancelled successfully !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("INVALID_USER", "Invalid Username or Password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
