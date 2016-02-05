package com.acc.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
//import javax.persistence.NoResultException;
//import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

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
		EntityManager em = com.acc.java.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		//HttpSession session = request.getSession();
		
		try {
			//JDBC WAY
			//Begin transaction, create table if it doesn't already exist for user
			trans.begin();
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "Bigbones12");
			String createTable = "CREATE TABLE IF NOT EXISTS user (id INT AUTO_INCREMENT PRIMARY KEY,"
					+	"userName VARCHAR(30), password VARCHAR(30))";
			Statement st = con.createStatement();
			st.executeUpdate(createTable);
			//Create result set, if username and password exist within table/result set, redirect user to welcome page
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM User WHERE userName='" + userName + "' AND password='" + password + "'");
			if(rs.next()){
				request.getSession().setAttribute("userName", userName);
				System.out.println("nice");
				doGet(request, response);
			} //Otherwise, tell them to try again
			else {
				request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);
<<<<<<< HEAD
				out.println("<div class='alert alert-danger' role='alert'>"
						+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
						+ "<span aria-hidden='true'>&times;</span></button>"
						+ "Username or password was incorrect.</div>");
=======
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Username or password was incorrect.');");
				out.println("</script>");
>>>>>>> 3e24079e0d4621833432c953dbe599c4487cf0d0
				System.out.println("Username/password descrepancy");
				
			}
			
		} catch (SQLException e) {
			System.out.println("Houston we have a problem");
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

}
