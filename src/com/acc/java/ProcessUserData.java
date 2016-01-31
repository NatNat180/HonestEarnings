package com.acc.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class ProcessUserData
 */
@WebServlet("/process-user-data")
public class ProcessUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessUserData() {
        super();

    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//Get session attribute of username
			HttpSession session = request.getSession();
			Object userName = session.getAttribute("userName");
			//Establish connection with database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "Bigbones12");
			//Create a SQL statement
			String obtainUserData = "SELECT Auto_Insurance, Auto_Maintenance,"
						+ "Babysitter, Books,"
						+ "Cable, Cleaning, Clothes, Children,"
						+ "Donations, Electricity, Entertainment, Eating_Out,"
						+ "Fuel, Gas, Groceries, Gifts,"
						+ "Grooming, Home_Repair, Internet, Medical,"
						+ "Phone, Retirement, Savings, Spending,"
						+ "Vacation, Misc, Total_Allowance FROM user WHERE userName = '" + userName + "'";
			//Create a statement to get a resultset from user data
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(obtainUserData);

			//Create a List of category allowances
			List<Double> categoryAllowances = new ArrayList<Double>();
			//iterate through the result set and add values to the list, provided the value is not zero
			while(rs.next()){
				int i = 0;
				while(i <= 26){
					i++;
					categoryAllowances.add(rs.getDouble(i));
					System.out.println(rs.getDouble(i));
				}
			}
			
			//Convert the double list into a string list
			List<String> categoryAllowancesString = new ArrayList<String>();
			for(Double d : categoryAllowances){
				categoryAllowancesString.add(d.toString());
			}
			
			//Create a List of category names
			List<String> categoryNames = new ArrayList<String>();
			categoryNames.add("Auto Insurance"); categoryNames.add("Auto Maintenance");
			categoryNames.add("Babysitter"); categoryNames.add("Books");
			categoryNames.add("Cable"); categoryNames.add("Cleaning");
			categoryNames.add("Clothes"); categoryNames.add("Children");
			categoryNames.add("Donations"); categoryNames.add("Electricity");
			categoryNames.add("Entertainment"); categoryNames.add("Eating Out");
			categoryNames.add("Fuel"); categoryNames.add("Gas");
			categoryNames.add("Groceries"); categoryNames.add("Gifts");
			categoryNames.add("Grooming"); categoryNames.add("Home Repair");
			categoryNames.add("Internet"); categoryNames.add("Medical");
			categoryNames.add("Phone"); categoryNames.add("Retirement");
			categoryNames.add("Savings"); categoryNames.add("Spending");
			categoryNames.add("Vacation"); categoryNames.add("Misc");
			categoryNames.add("Total Allowance");
			
			//Create a hashmap using the category titles as a key, and the allowances as a value (from the list)
			Map<String, String> categoryMap = new HashMap<String, String>();
			categoryMap.put(categoryNames.get(0), categoryAllowancesString.get(0));
			categoryMap.put(categoryNames.get(1), categoryAllowancesString.get(1));
			categoryMap.put(categoryNames.get(2), categoryAllowancesString.get(2));
			categoryMap.put(categoryNames.get(3), categoryAllowancesString.get(3));
			categoryMap.put(categoryNames.get(4), categoryAllowancesString.get(4));
			categoryMap.put(categoryNames.get(5), categoryAllowancesString.get(5));
			categoryMap.put(categoryNames.get(6), categoryAllowancesString.get(6));
			categoryMap.put(categoryNames.get(7), categoryAllowancesString.get(7));
			categoryMap.put(categoryNames.get(8), categoryAllowancesString.get(8));
			categoryMap.put(categoryNames.get(9), categoryAllowancesString.get(9));
			categoryMap.put(categoryNames.get(10), categoryAllowancesString.get(10));
			categoryMap.put(categoryNames.get(11), categoryAllowancesString.get(11));
			categoryMap.put(categoryNames.get(12), categoryAllowancesString.get(12));
			categoryMap.put(categoryNames.get(13), categoryAllowancesString.get(13));
			categoryMap.put(categoryNames.get(14), categoryAllowancesString.get(14));
			categoryMap.put(categoryNames.get(15), categoryAllowancesString.get(15));
			categoryMap.put(categoryNames.get(16), categoryAllowancesString.get(16));
			categoryMap.put(categoryNames.get(17), categoryAllowancesString.get(17));
			categoryMap.put(categoryNames.get(18), categoryAllowancesString.get(18));
			categoryMap.put(categoryNames.get(19), categoryAllowancesString.get(19));
			categoryMap.put(categoryNames.get(20), categoryAllowancesString.get(20));
			categoryMap.put(categoryNames.get(21), categoryAllowancesString.get(21));
			categoryMap.put(categoryNames.get(22), categoryAllowancesString.get(22));
			categoryMap.put(categoryNames.get(23), categoryAllowancesString.get(23));
			categoryMap.put(categoryNames.get(24), categoryAllowancesString.get(24));
			categoryMap.put(categoryNames.get(25), categoryAllowancesString.get(25));
			System.out.println(categoryMap);
			session.setAttribute("categoryMap", categoryMap);
			
			//Create a separate map from just the total allowance
			Map<String, String> totalAllowanceMap = new HashMap<String, String>();
			totalAllowanceMap.put(categoryNames.get(26), categoryAllowancesString.get(26));
			session.setAttribute("totalAllowance", totalAllowanceMap);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

}
