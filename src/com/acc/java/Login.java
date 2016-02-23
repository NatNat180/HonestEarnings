package com.acc.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getServletContext().getRequestDispatcher("/process-user-data").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			//Create table if it doesn't already exist for user
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://aaaj8td77qaymd.cvn0vweraivv.us-west-2.rds.amazonaws.com:3306/ebdb", "root", "Bigbones12");
			String createTable = "CREATE TABLE IF NOT EXISTS user (id INT AUTO_INCREMENT PRIMARY KEY,"
					+	"userName VARCHAR(30), password VARCHAR(30))";
			Statement st = con.createStatement();
			st.executeUpdate(createTable);
			//Create result set, if username and password exist within table/result set, redirect user to welcome page
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM user WHERE userName='" + userName + "' AND password='" + password + "'");
			if(rs.next()){
				request.getSession().setAttribute("userName", userName);
				System.out.println("nice");
				doGet(request, response);
			} //Otherwise, tell them to try again
			else {
				request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);
				out.println("<div class='alert alert-danger' role='alert'>"
						+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
						+ "<span aria-hidden='true'>&times;</span></button>"
						+ "Username or password was incorrect.</div>");
				System.out.println("Username/password descrepancy");
				
			}
			
		} catch (SQLException e) {
			System.out.println("Houston we have a problem");
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			doGet(request, response);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
		} finally {

		}
	}

}
