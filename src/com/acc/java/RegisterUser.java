package com.acc.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		try {
			
			if(userName.equals("") || password.equals("")){
				request.getRequestDispatcher("/WEB-INF/register.jsp").include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please input a username and password.');");
				out.println("</script>");
			} else {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://aaaj8td77qaymd.cvn0vweraivv.us-west-2.rds.amazonaws.com:3306/ebdb",
						"root", "Bigbones12");
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
                        + "Total_Allowance DOUBLE NOT NULL DEFAULT 0, Original_Allowance DOUBLE NOT NULL DEFAULT 0,"
                        + "Auto_Insurance_Original DOUBLE NOT NULL DEFAULT 0, Auto_Maintenance_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Babysitter_Original DOUBLE NOT NULL DEFAULT 0, Books_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Cable_Original DOUBLE NOT NULL DEFAULT 0, Cleaning_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Clothes_Original DOUBLE NOT NULL DEFAULT 0, Children_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Donations_Original DOUBLE NOT NULL DEFAULT 0, Electricity_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Entertainment_Original DOUBLE NOT NULL DEFAULT 0, Eating_Out_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Fuel_Original DOUBLE NOT NULL DEFAULT 0, Gas_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Groceries_Original DOUBLE NOT NULL DEFAULT 0, Gifts_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Grooming_Original DOUBLE NOT NULL DEFAULT 0, Home_Repair_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Internet_Original DOUBLE NOT NULL DEFAULT 0, Medical_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Phone_Original DOUBLE NOT NULL DEFAULT 0, Retirement_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Savings_Original DOUBLE NOT NULL DEFAULT 0, Spending_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Vacation_Original DOUBLE NOT NULL DEFAULT 0, Misc_Original DOUBLE NOT NULL DEFAULT 0,"
                        + "Auto_Insurance_Percent DOUBLE NOT NULL DEFAULT 0, Auto_Maintenance_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Babysitter_Percent  DOUBLE NOT NULL DEFAULT 0, Books_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Cable_Percent  DOUBLE NOT NULL DEFAULT 0, Cleaning_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Clothes_Percent  DOUBLE NOT NULL DEFAULT 0, Children_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Donations_Percent  DOUBLE NOT NULL DEFAULT 0, Electricity_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Entertainment_Percent  DOUBLE NOT NULL DEFAULT 0, Eating_Out_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Fuel_Percent  DOUBLE NOT NULL DEFAULT 0, Gas_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Groceries_Percent  DOUBLE NOT NULL DEFAULT 0, Gifts_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Grooming_Percent  DOUBLE NOT NULL DEFAULT 0, Home_Repair_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Internet_Percent  DOUBLE NOT NULL DEFAULT 0, Medical_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Phone_Percent  DOUBLE NOT NULL DEFAULT 0, Retirement_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Savings_Percent  DOUBLE NOT NULL DEFAULT 0, Spending_Percent  DOUBLE NOT NULL DEFAULT 0,"
                        + "Vacation_Percent  DOUBLE NOT NULL DEFAULT 0, Misc_Percent  DOUBLE NOT NULL DEFAULT 0)";

                Statement st = con.createStatement();     
                st.executeUpdate(createTable);

				// Create a resultset so that if a user registers a name that already exists in the table/resultset, the program lets the user know
				ResultSet rs;
				rs = st.executeQuery("SELECT * FROM user WHERE userName='" + userName + "'");
				if (rs.next()) {
					request.setAttribute("userName", userName);
					System.out.println("username " + userName + " already exists!");

					request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);
					out.println("<div class='alert alert-info' role='alert'>"
							+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
							+ "<span aria-hidden='true'>&times;</span></button>"
							+ "Username already exists.</div>");
				} // Otherwise, insert the username and password into the table
				else {
					String insertUserData = "INSERT INTO user (userName, password) VALUES (?, ?)";
					PreparedStatement statement = con.prepareStatement(insertUserData);

					statement.setString(1, userName);
					statement.setString(2, password);
					statement.executeUpdate();
					System.out.println("nice");

					request.getSession().setAttribute("userName", userName);
					doGet(request, response);
				}
				
			}
			

		} catch (Exception e) {
			System.out.println("Houston we have a problem");
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			doGet(request, response);

		} finally {

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/income-expenses.jsp").forward(request, response);
	}

}
