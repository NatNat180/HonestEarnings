package com.acc.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ModifyUserData
 */
@WebServlet("/record-expense")
public class RecordExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RecordExpense() {
        super();

    }
    
    public Double calculateAmount(double currentValue, double expense){
    	
    	return currentValue - expense;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object userName = session.getAttribute("userName");
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "Bigbones12");
			
			//Retrieve the table data
			String getCurrentValues = "SELECT Auto_Insurance, Auto_Maintenance,"
					+ "Babysitter, Books,"
					+ "Cable, Cleaning, Clothes, Children,"
					+ "Donations, Electricity, Entertainment, Eating_Out,"
					+ "Fuel, Gas, Groceries, Gifts,"
					+ "Grooming, Home_Repair, Internet, Medical,"
					+ "Phone, Retirement, Savings, Spending,"
					+ "Vacation, Misc, Total_Allowance "
					+ "FROM user WHERE userName = '" + userName + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(getCurrentValues);
			
			Double currentAllowance = 0.0;
			Double newAllowance = 0.0;
			double autoInsurance = 0;
			double autoMaintenance = 0; 
			double babysitter = 0;
			double books = 0;
			double cable = 0;
			double cleaning = 0;
			double clothes = 0;
			double children = 0;
			double donations = 0;
			double electricity = 0;
			double entertainment = 0;
			double eatingOut = 0;
			double fuel = 0;
			double gas = 0;
			double groceries = 0;
			double gifts = 0;
			double grooming = 0;
			double homeRepair = 0;
			double internet = 0;
			double medical = 0;
			double phone = 0;
			double retirement = 0;
			double savings = 0;
			double spending = 0;
			double vacation = 0;
			double misc = 0;
			
			double autoInsuranceExpense = 0;
			double autoMaintenanceExpense = 0; 
			double babysitterExpense = 0;
			double booksExpense = 0;
			double cableExpense = 0;
			double cleaningExpense = 0;
			double clothesExpense = 0;
			double childrenExpense = 0;
			double donationsExpense = 0;
			double electricityExpense = 0;
			double entertainmentExpense = 0;
			double eatingOutExpense = 0;
			double fuelExpense = 0;
			double gasExpense = 0;
			double groceriesExpense = 0;
			double giftsExpense = 0;
			double groomingExpense = 0;
			double homeRepairExpense = 0;
			double internetExpense = 0;
			double medicalExpense = 0;
			double phoneExpense = 0;
			double retirementExpense = 0;
			double savingsExpense = 0;
			double spendingExpense = 0;
			double vacationExpense = 0;
			double miscExpense = 0;
			
			//obtain the original allowance value
			while(rs.next()){
				autoInsurance = rs.getDouble(1); autoMaintenance = rs.getDouble(2);
				babysitter = rs.getDouble(3); books = rs.getDouble(4);
				cable = rs.getDouble(5); cleaning = rs.getDouble(6);
				clothes = rs.getDouble(7); children = rs.getDouble(8);
				donations = rs.getDouble(9); electricity = rs.getDouble(10);
				entertainment = rs.getDouble(11); eatingOut = rs.getDouble(12);
				fuel = rs.getDouble(13); gas = rs.getDouble(14);
				groceries = rs.getDouble(15); gifts = rs.getDouble(16);
				grooming = rs.getDouble(17); homeRepair = rs.getDouble(18);
				internet = rs.getDouble(19); medical = rs.getDouble(20);
				phone = rs.getDouble(21); retirement = rs.getDouble(22);
				savings = rs.getDouble(23); spending = rs.getDouble(24);
				vacation = rs.getDouble(25); misc = rs.getDouble(26);
				currentAllowance = rs.getDouble(27);
			}
			
			//Calculation time
			String autoInsuranceExpenseString = request.getParameter("Auto Insurance");
			if(autoInsuranceExpenseString != null){autoInsuranceExpense = Double.parseDouble(autoInsuranceExpenseString);}
			double autoInsuranceValue = calculateAmount(autoInsurance, autoInsuranceExpense);
			
			String autoMaintenanceExpenseString = request.getParameter("Auto Maintenance");
			if(autoMaintenanceExpenseString != null){autoMaintenanceExpense = Double.parseDouble(autoMaintenanceExpenseString);}
			double autoMaintenanceValue = calculateAmount(autoMaintenance, autoMaintenanceExpense);

			String babysitterExpenseString = request.getParameter("Babysitter");
			if(babysitterExpenseString != null){babysitterExpense = Double.parseDouble(babysitterExpenseString);}
			double babysitterValue = calculateAmount(babysitter, babysitterExpense);

			String booksExpenseString = request.getParameter("Books");
			if(booksExpenseString != null){booksExpense = Double.parseDouble(booksExpenseString);}
			double booksValue = calculateAmount(books, booksExpense);
		
			String cableExpenseString = request.getParameter("Cable");
			if(cableExpenseString != null){cableExpense = Double.parseDouble(cableExpenseString);}
			double cableValue = calculateAmount(cable, cableExpense);
			
			String cleaningExpenseString = request.getParameter("Cleaning");
			if(cleaningExpenseString != null){cleaningExpense = Double.parseDouble(cleaningExpenseString);}
			double cleaningValue = calculateAmount(cleaning, cleaningExpense);
			
			String clothesExpenseString = request.getParameter("Clothes");
			if(clothesExpenseString != null){clothesExpense = Double.parseDouble(clothesExpenseString);}
			double clothesValue = calculateAmount(clothes, clothesExpense);
			
			String childrenExpenseString = request.getParameter("Children");
			if(childrenExpenseString != null){childrenExpense = Double.parseDouble(childrenExpenseString);}
			double childrenValue = calculateAmount(children, childrenExpense);
			
			String donationsExpenseString = request.getParameter("Donations");
			if(donationsExpenseString != null){donationsExpense = Double.parseDouble(donationsExpenseString);}
			double donationsValue = calculateAmount(donations, donationsExpense);
			
			String electricityExpenseString = request.getParameter("Electricity");
			if(electricityExpenseString != null){electricityExpense = Double.parseDouble(electricityExpenseString);}
			double electricityValue = calculateAmount(electricity, electricityExpense);
			
			String entertainmentExpenseString = request.getParameter("Entertainment");
			if(entertainmentExpenseString != null){entertainmentExpense = Double.parseDouble(entertainmentExpenseString);}
			double entertainmentValue = calculateAmount(entertainment, entertainmentExpense);
			
			String eatingOutExpenseString = request.getParameter("Eating Out");
			if(eatingOutExpenseString != null){eatingOutExpense = Double.parseDouble(eatingOutExpenseString);}
			double eatingOutValue = calculateAmount(eatingOut, eatingOutExpense);
			
			String fuelExpenseString = request.getParameter("Fuel");
			if(fuelExpenseString != null){fuelExpense = Double.parseDouble(fuelExpenseString);}
			double fuelValue = calculateAmount(fuel, fuelExpense);
			
			String gasExpenseString = request.getParameter("Gas");
			if(gasExpenseString != null){gasExpense = Double.parseDouble(gasExpenseString);}
			double gasValue = calculateAmount(gas, gasExpense);
			
			String groceriesExpenseString = request.getParameter("Groceries");
			if(groceriesExpenseString != null){groceriesExpense = Double.parseDouble(groceriesExpenseString);}
			double groceriesValue = calculateAmount(groceries, groceriesExpense);
			
			String giftsExpenseString = request.getParameter("Gifts");
			if(giftsExpenseString != null){giftsExpense = Double.parseDouble(giftsExpenseString);}
			double giftsValue = calculateAmount(gifts, giftsExpense);
			
			String groomingExpenseString = request.getParameter("Grooming");
			if(groomingExpenseString != null){groomingExpense = Double.parseDouble(groomingExpenseString);}
			double groomingValue = calculateAmount(grooming, groomingExpense);
			
			String homeRepairExpenseString = request.getParameter("Home Repair");
			if(homeRepairExpenseString != null){homeRepairExpense = Double.parseDouble(homeRepairExpenseString);}
			double homeRepairValue = calculateAmount(homeRepair, homeRepairExpense);
			
			String internetExpenseString = request.getParameter("Internet");
			if(internetExpenseString != null){internetExpense = Double.parseDouble(internetExpenseString);}
			double internetValue = calculateAmount(internet, internetExpense);
			
			String medicalExpenseString = request.getParameter("Medical");
			if(medicalExpenseString != null){medicalExpense = Double.parseDouble(medicalExpenseString);}
			double medicalValue = calculateAmount(medical, medicalExpense);
			
			String phoneExpenseString = request.getParameter("Phone");
			if(phoneExpenseString != null){phoneExpense = Double.parseDouble(phoneExpenseString);}
			double phoneValue = calculateAmount(phone, phoneExpense);
			
			String retirementExpenseString = request.getParameter("Retirement");
			if(retirementExpenseString != null){retirementExpense = Double.parseDouble(retirementExpenseString);}
			double retirementValue = calculateAmount(retirement, retirementExpense);
			
			String savingsExpenseString = request.getParameter("Savings");
			if(savingsExpenseString != null){savingsExpense = Double.parseDouble(savingsExpenseString);}
			double savingsValue = calculateAmount(savings, savingsExpense);
			
			String spendingExpenseString = request.getParameter("Spending");
			if(spendingExpenseString != null){spendingExpense = Double.parseDouble(spendingExpenseString);}
			double spendingValue = calculateAmount(spending, spendingExpense);
			
			String vacationExpenseString = request.getParameter("Vacation");
			if(vacationExpenseString != null){vacationExpense = Double.parseDouble(vacationExpenseString);}
			double vacationValue = calculateAmount(vacation, vacationExpense);
			
			String miscExpenseString = request.getParameter("Misc");
			if(miscExpenseString != null){miscExpense = Double.parseDouble(miscExpenseString);}
			double miscValue = calculateAmount(misc, miscExpense);
			
			
			newAllowance = (currentAllowance - (miscExpense + vacationExpense + 
					spendingExpense + savingsExpense + retirementExpense +
					phoneExpense + medicalExpense + internetExpense + homeRepairExpense +
					groomingExpense + giftsExpense + groceriesExpense + 
					gasExpense + fuelExpense + eatingOutExpense + entertainmentExpense +
					electricityExpense + donationsExpense + childrenExpense + 
					clothesExpense + cleaningExpense + cableExpense + 
					booksExpense + babysitterExpense + autoMaintenanceExpense +
					autoInsuranceExpense));
			
			if(newAllowance.equals(currentAllowance)){
				response.sendRedirect("/WEB-INF/home.jsp");
			}
			
			//Insert the new values into the table!
			String updateCategoryTable = "UPDATE user SET Auto_Insurance = ?, Auto_Maintenance = ?,"
					+ "Babysitter = ?, Books = ?,"
					+ "Cable = ?, Cleaning = ?, Clothes = ?, Children = ?,"
					+ "Donations = ?, Electricity = ?, Entertainment = ?, Eating_Out = ?,"
					+ "Fuel = ?, Gas = ?, Groceries = ?, Gifts = ?,"
					+ "Grooming = ?, Home_Repair = ?, Internet = ?, Medical = ?,"
					+ "Phone = ?, Retirement = ?, Savings = ?, Spending = ?,"
					+ "Vacation = ?, Misc = ?, Total_Allowance = ?"
					+ "WHERE userName = ?";
		
		PreparedStatement updateCategories = con.prepareStatement(updateCategoryTable);
		
		updateCategories.setDouble(1, autoInsuranceValue); updateCategories.setDouble(2, autoMaintenanceValue);
		updateCategories.setDouble(3, babysitterValue); updateCategories.setDouble(4, booksValue);
		updateCategories.setDouble(5, cableValue); updateCategories.setDouble(6, cleaningValue);
		updateCategories.setDouble(7, clothesValue); updateCategories.setDouble(8, childrenValue);
		updateCategories.setDouble(9, donationsValue); updateCategories.setDouble(10, electricityValue);
		updateCategories.setDouble(11, entertainmentValue); updateCategories.setDouble(12, eatingOutValue);
		updateCategories.setDouble(13, fuelValue); updateCategories.setDouble(14, gasValue);
		updateCategories.setDouble(15, groceriesValue); updateCategories.setDouble(16, giftsValue);
		updateCategories.setDouble(17, groomingValue); updateCategories.setDouble(18, homeRepairValue);
		updateCategories.setDouble(19, internetValue); updateCategories.setDouble(20, medicalValue);
		updateCategories.setDouble(21, phoneValue); updateCategories.setDouble(22, retirementValue);
		updateCategories.setDouble(23, savingsValue); updateCategories.setDouble(24, spendingValue);
		updateCategories.setDouble(25, vacationValue); updateCategories.setDouble(26, miscValue);
		updateCategories.setDouble(27, newAllowance); updateCategories.setObject(28, userName);
		
		updateCategories.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/process-user-data").forward(request, response);
	}

}
