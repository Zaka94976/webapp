package com.incapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/SearchUser")
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();;
		try {
			
			
			String name=request.getParameter("name");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/userapp", "root", "zakaullah");
			
			//PreparedStatement p=c.prepareStatement("select * from users where name=?");
			PreparedStatement p=c.prepareStatement("select * from users where name like ?");
			
			p.setString(1, "%"+name+"%");
			ResultSet rs=p.executeQuery();
			int count=0;
			ArrayList<HashMap> users=new ArrayList<>();
			while(rs.next()) {
				HashMap user=new HashMap<>();
				user.put("name",rs.getString("name"));
				user.put("email",rs.getString("email"));
				user.put("phone",rs.getString("phone"));
				user.put("gender",rs.getString("gender"));
				user.put("age",rs.getInt("age"));
				user.put("address",rs.getString("address"));
				users.add(user);
				count++;
			}
			if(count!=0){
				request.setAttribute("users",users);
				request.setAttribute("count",count);
				RequestDispatcher rd=request.getRequestDispatcher("PrintUser");
				rd.forward(request, response);
			}else {
				response.sendRedirect("NoUserFound.html");
			}
		}catch (Exception e) {
			out.print(e);
		}
		out.close();
	}

}
