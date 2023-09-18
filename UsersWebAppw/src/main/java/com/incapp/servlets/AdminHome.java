package com.incapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AdminHome")
public class AdminHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHome() {
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
			
			HttpSession session=request.getSession(false);
			if(session==null) {
				response.sendRedirect("SessionError.html");
			}else {
				String name=(String)session.getAttribute("name");
				out.print("<html>");
				out.print("<head>");
				out.print("<title>Users App</title>");
				out.print("</head>");
				out.print("<body>");
				out.print("<h1>Users App</h1>");
				out.print("<hr/><p>"
						+ "<a href='AdminHome'>Home</a> &nbsp;&nbsp;&nbsp;&nbsp;"
						+ "Welcome: <b>"+name+"</b> &nbsp;&nbsp;&nbsp;&nbsp;"
						+ "<a href='Logout'>Logout</a>"
						+ "</p><hr/>");
				out.print("<h3>Add New User</h3>");
				out.print("<form action='AddUser' method='post'>");
				out.print("<label>Email:</label>"
						+ "<input type='email' name='email' required /> <br/><br/>"
						+ "<label>Name:</label>"
						+ "<input type='text' name='name' required /> <br/><br/>"
						+ "<label>Phone:</label>"
						+ "<input type='tel' name='phone' maxlength='10' required /> <br/><br/>"
						+ "<label>Age:</label>"
						+ "<input type='number' name='age' required /> <br/><br/>"
						+ "<label>Gender:</label>"
						+ "<input type='radio' name='gender' value='Male' checked /> Male"
						+ "<input type='radio' name='gender' value='Female' /> Female"
						+ "<br/><br/>"
						+ "<label>Address:</label>"
						+ "<textarea rows='3' name='address' required></textarea> <br/><br/>"
						+ "<button>Add User</button>"
						+ "	</form>");
				out.print("</body>");
				out.print("</html>");
			}
		}catch (Exception e) {
			out.print(e);
		}
	}

}
