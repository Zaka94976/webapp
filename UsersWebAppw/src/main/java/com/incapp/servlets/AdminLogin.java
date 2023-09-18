package com.incapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=response.getWriter();
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			//JDBC code
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/userapp", "root", "zakaullah");
			Statement st=c.createStatement();
			
			ResultSet rs=st.executeQuery("select * from login where id = '"+id+"' and password = '"+password+"'");
		
			if(rs.next()) {
				String name=rs.getString("name");
				
				HttpSession session=request.getSession();
				session.setAttribute("name",name);
				response.sendRedirect("AdminHome");
			}
			else {
				response.sendRedirect("LoginFailed.html");
			}
			
			c.close();
		}catch (Exception e) {
			out.print(e);
		}
	}

}
