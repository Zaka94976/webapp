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
@WebServlet("/PrintUser")
public class PrintUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();;
		try {
			
			
			ArrayList<HashMap> users=(ArrayList<HashMap>)request.getAttribute("users");
			int count=(Integer)request.getAttribute("count");
			
			
			out.print("<html>");
			out.print("<head>");
			ServletContext ctx=getServletContext();
			String an=ctx.getInitParameter("app-name");
			out.print("<title>"+an+"</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h1>"+an+"</h1>");
			out.print("<hr/><p> Total Results Found:"+count+"</p><hr/>");
			for(HashMap user:users) {
				out.print("<div style='background-color:yellow; padding:20px;margin:5px;'>");
				out.print("<p> Name: <b>"+user.get("name")+"</b> </p>");
				out.print("<p> Email: <b>"+user.get("email")+"</b> </p>");
				out.print("<p> Phone: <b>"+user.get("phone")+"</b> </p>");
				out.print("<p> Gender: <b>"+user.get("gender")+"</b> </p>");
				out.print("<p> Age: <b>"+user.get("age")+"</b> </p>");
				out.print("<p> Address: <b>"+user.get("address")+"</b> </p>");
				out.print("</div>");
			}
			out.print("</body>");
			out.print("</html>");
		}catch (Exception e) {
			out.print(e);
		}
		out.close();
	}

}
