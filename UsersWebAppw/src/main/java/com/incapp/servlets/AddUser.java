package com.incapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");
			int age = Integer.parseInt(request.getParameter("age"));
			
			//jdbc code
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/userapp", "root", "zakaullah");

				PreparedStatement p = c.prepareStatement(
						"insert into users (email,name,phone,age,gender,address) values (?,?,?,?,?,?)");
				
				p.setString(1, email);
				p.setString(2, name);
				p.setString(3, phone);
				p.setInt(4, age);
				p.setString(5, gender);
				p.setString(6, address);
				p.executeUpdate();
				
				response.sendRedirect("Success.html");
				
			}catch (SQLIntegrityConstraintViolationException e) {
				response.sendRedirect("AlreadyExist.html");
			}catch (ClassNotFoundException | SQLException e) {
				out.print(e);
				e.printStackTrace();
			}
		}catch (Exception e) {
			out.print(e);
			e.printStackTrace();
		}
	}

}
