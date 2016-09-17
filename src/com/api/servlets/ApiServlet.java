package com.api.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApiServlet
 */
@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DBOperation app;
	public static final String ADD = "add";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiServlet() {
        super();
        
        try {
			app = new DBOperation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //System.out.println(app);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("showdatabase.jsp").forward(request, response);
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String op = request.getParameter("operation");
		
		if("remove".equals(op))
		{
			try{
				
			
				int c = app.removeEmployee(request.getParameter("employee_id"));
				response.sendRedirect(request.getContextPath() + "/showdatabase.jsp");
				
				
			}catch(Exception e)
			{
				//System.out.println(e);
				
				out.println("error");
			}
		}else if("add".equals(op))
		{
			int c;
			
			
			try {
				c = app.addEmployee(request.getParameter("name"), request.getParameter("phone_number"), request.getParameter("supervisors"));
				response.sendRedirect(request.getContextPath() + "/showdatabase.jsp");
				
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				
				out.println("error");
			}
			
			
			
		}else{
			
			out.println("unknown operation");
		}
		
	}

}
