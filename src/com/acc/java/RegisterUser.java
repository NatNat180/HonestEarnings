package com.acc.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

@WebServlet("/register-user")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterUser() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			
			if(userName.equals("") || password.equals("")){
				request.getRequestDispatcher("/WEB-INF/register.jsp").include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please input a username and password.');");
				out.println("</script>");
			} else {
				trans.begin();
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase",
						"root", "Bigbones12");
				String dropTable = "DROP TABLE IF EXISTS user";
				String createTable = "CREATE TABLE IF NOT EXISTS user(id INT AUTO_INCREMENT PRIMARY KEY,"
						+ "userName VARCHAR(30), password VARCHAR(30),"
						+ "Auto_Insurance DOUBLE NOT NULL DEFAULT 0, Auto_Maintenance DOUBLE NOT NULL DEFAULT 0,"
						+ "Babysitter DOUBLE NOT NULL DEFAULT 0, Books DOUBLE NOT NULL DEFAULT 0,"
						+ "Cable DOUBLE NOT NULL DEFAULT 0, Cleaning DOUBLE NOT NULL DEFAULT 0,"
						+ "Clothes DOUBLE NOT NULL DEFAULT 0, Children DOUBLE NOT NULL DEFAULT 0,"
						+ "Donations DOUBLE NOT NULL DEFAULT 0, Electricity DOUBLE NOT NULL DEFAULT 0,"
						+ "Entertainment DOUBLE NOT NULL DEFAULT 0, Eating_Out DOUBLE NOT NULL DEFAULT 0,"
						+ "Fuel DOUBLE NOT NULL DEFAULT 0, Gas DOUBLE NOT NULL DEFAULT 0,"
						+ "Groceries DOUBLE NOT NULL DEFAULT 0, Gifts DOUBLE NOT NULL DEFAULT 0,"
						+ "Grooming DOUBLE NOT NULL DEFAULT 0, Home_Repair DOUBLE NOT NULL DEFAULT 0,"
						+ "Internet DOUBLE NOT NULL DEFAULT 0, Medical DOUBLE NOT NULL DEFAULT 0,"
						+ "Phone DOUBLE NOT NULL DEFAULT 0, Retirement DOUBLE NOT NULL DEFAULT 0,"
						+ "Savings DOUBLE NOT NULL DEFAULT 0, Spending DOUBLE NOT NULL DEFAULT 0,"
						+ "Vacation DOUBLE NOT NULL DEFAULT 0, Misc DOUBLE NOT NULL DEFAULT 0,"
						+ "Total_Allowance DOUBLE NOT NULL DEFAULT 0, Original_Allowance DOUBLE NOT NULL DEFAULT 0)";

				Statement st = con.createStatement();
				st.executeUpdate(dropTable);
				st.executeUpdate(createTable);
				// Create a resultset so that if a user registers a name that already exists in the table/resultset, the program lets the user know
				ResultSet rs;
				rs = st.executeQuery("SELECT * FROM User WHERE userName='" + userName + "'");
				if (rs.next()) {
					request.setAttribute("userName", userName);
					System.out.println("username " + userName + " already exists!");

					request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Username already exists.');");
					out.println("</script>");
				} // Otherwise, insert the username and password into the table
				else {
					String insertUserData = "INSERT INTO user (userName, password) VALUES (?, ?)";
					PreparedStatement statement = con.prepareStatement(insertUserData);
					statement.setString(1, userName);
					statement.setString(2, password);
					statement.executeUpdate();
					System.out.println("nice");
					trans.commit();
					request.getSession().setAttribute("userName", userName);
					doGet(request, response);
				}
<<<<<<< HEAD
				
			}
=======
				// //Assign a number to the first column of user_category table for
				// however many id's there are in the user table
				// String assignId = "INSERT INTO user_categories (id) VALUES (?)";
				// PreparedStatement assignIdPrep = con.prepareStatement(assignId);
				// //create a result set to loop through the id column in user table
				// ResultSet findingId = st.executeQuery("SELECT COUNT(id) FROM
				// user");
				// //Create an int count and it's string equivalent
				// int count = 0;
				// String countString = String.valueOf(count);
				// // While there is still something left in id column of user
				// table,
				// // Add one to count int, and assign the String of that count int
				// to the count int
				// // equivalent of the user_category id column
				// while(findingId.next()){
				// ++count;
				// assignIdPrep.setString(count, countString);
				// }
				// //update table
				// assignIdPrep.executeUpdate();
				
			}
			// JDBC
			// Begin the transaction, create a connection with the database and
			// create a user table if it doesn't already exist
>>>>>>> 3e24079e0d4621833432c953dbe599c4487cf0d0
			

		} catch (Exception e) {
			System.out.println("Houston we have a problem");
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/income-expenses.jsp").forward(request, response);
	}

}
