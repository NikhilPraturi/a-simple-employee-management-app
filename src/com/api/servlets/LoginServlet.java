package com.api.servlets;
import java.io.PrintWriter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub

		try {
			userService = new DBOperation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(app);
	}

	// @EJB
	private DBOperation userService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

		String message = "";

		if (username == null || username.isEmpty()) {

			message = "Please enter username";
		}

		else if (password == null || password.isEmpty()) {

			message = "Please enter password";
		}

		if (message.isEmpty()) {
			boolean valid = userService.find(username, password);

			if (valid) {
				request.getSession().setAttribute("user", true);
				response.sendRedirect(request.getContextPath() + "/showdatabase.jsp");
				return;
			} else {
				message = "username or password incorrect";
			}
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}