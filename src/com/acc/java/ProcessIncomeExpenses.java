package com.acc.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;


@WebServlet("/process-income-expenses")
public class ProcessIncomeExpenses extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double calculateAmount(double points, double income){
    	
    	points = points/100;
    	return round((income * points), 2);
    	
    }
    
    public static double calculatePercent(double categoryAmount, double income){
    	
    	return categoryAmount/income;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//See if user is registered first
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("userName") == null){
			request.getRequestDispatcher("/WEB-INF/register.jsp").include(request, response);
			out.print("Please register first");
		} else {
			
			//HERE WE GO
			String netIncomeString = request.getParameter("netIncome");
			double netIncome = Double.parseDouble(netIncomeString);
			
			String payPeriod = request.getParameter("payPeriod");
			
			String knownMonthlyExpensesString = request.getParameter("knownMonthlyExpenses");
			double knownMonthlyExpenses = Double.parseDouble(knownMonthlyExpensesString);
			
			double knownExpenses;
			double totalAllowanceLong;
			// Establish how much to subtract from totalIncome each time it is logged
			if(payPeriod.equals("weekly")){
				knownExpenses = (knownMonthlyExpenses * 12) / 52;
			} else if(payPeriod.equals("bi-weekly")){
				knownExpenses = (knownMonthlyExpenses * 12) / 26;
			} else if(payPeriod.equals("semi-monthly")){
				knownExpenses = (knownMonthlyExpenses * 12) / 24;
			} else {
				knownExpenses = knownMonthlyExpenses;
			}
			
			//Create total allowance by subtracting knownExpenses from net income, then use round method to round the decimal to two places
			totalAllowanceLong = netIncome - knownExpenses;
			double totalAllowance = round(totalAllowanceLong, 2);
			System.out.println("Net Income: $" + netIncome);
			System.out.println("Pay Period: " + payPeriod);
			System.out.println("Known Monthly Expenses: $" + knownMonthlyExpenses);
			System.out.println("How much it equates to each pay period: $" + round(knownExpenses, 2));
			System.out.println("Total allowance per pay period: $" + totalAllowance);
			
			//Make total allowance into a String and set as an attribute so JSP's can read it and show it
			String totalAllowanceString = String.valueOf(totalAllowance);
			session.setAttribute("totalAllowance", totalAllowanceString);

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
			
			//Set attributes to category text fields and parse into double STRING EXAMPLE
			String autoInsuranceString = request.getParameter("autoInsurance");
			if(autoInsuranceString != null){autoInsurance = Double.parseDouble(autoInsuranceString);}
			double autoInsuranceValue = calculateAmount(autoInsurance, totalAllowance);
			String autoInsuranceValueString = Double.toString(autoInsuranceValue);
			session.setAttribute("autoInsurance", autoInsuranceValueString);
			
			
			String autoMaintenanceString = request.getParameter("autoMaintenance");
			if(autoMaintenanceString != null){autoMaintenance = Double.parseDouble(autoMaintenanceString);}
			double autoMaintenanceValue = calculateAmount(autoMaintenance, totalAllowance);
			String autoMaintenanceValueString = Double.toString(autoMaintenanceValue);
			session.setAttribute("autoMaintenance", autoMaintenanceValueString);
			
			String babysitterString = request.getParameter("babysitter");
			if(babysitterString != null){babysitter = Double.parseDouble(babysitterString);}
			double babysitterValue = calculateAmount(babysitter, totalAllowance);
			String babysitterValueString = Double.toString(babysitterValue);	
			session.setAttribute("babysitter", babysitterValueString);
			
			String booksString = request.getParameter("books");
			if(booksString != null){books = Double.parseDouble(booksString);}
			double booksValue = calculateAmount(books, totalAllowance);
			String booksValueString = Double.toString(booksValue);
			session.setAttribute("books", booksValueString);
		
			String cableString = request.getParameter("cable");
			if(cableString != null){cable = Double.parseDouble(cableString);}
			double cableValue = calculateAmount(cable, totalAllowance);
			String cableValueString = Double.toString(cableValue);
			session.setAttribute("cable", cableValueString);
			
			String cleaningString = request.getParameter("cleaning");
			if(cleaningString != null){cleaning = Double.parseDouble(cleaningString);}
			double cleaningValue = calculateAmount(cleaning, totalAllowance);
			String cleaningValueString = Double.toString(cleaningValue);
			session.setAttribute("cleaning", cleaningValueString);
			
			String clothesString = request.getParameter("clothes");
			if(clothesString != null){clothes = Double.parseDouble(clothesString);}
			double clothesValue = calculateAmount(clothes, totalAllowance);
			String clothesValueString = Double.toString(clothesValue);
			session.setAttribute("clothes", clothesValueString);
			
			String childrenString = request.getParameter("children");
			if(childrenString != null){children = Double.parseDouble(childrenString);}
			double childrenValue = calculateAmount(children, totalAllowance);
			String childrenValueString = Double.toString(childrenValue);
			session.setAttribute("children", childrenValueString);
			
			String donationsString = request.getParameter("donations");
			if(donationsString != null){donations = Double.parseDouble(donationsString);}
			double donationsValue = calculateAmount(donations, totalAllowance);
			String donationsValueString = Double.toString(donationsValue);
			session.setAttribute("donations", donationsValueString);
			
			String electricityString = request.getParameter("electricity");
			if(electricityString != null){electricity = Double.parseDouble(electricityString);}
			double electricityValue = calculateAmount(electricity, totalAllowance);
			String electricityValueString = Double.toString(electricityValue);
			session.setAttribute("electricity", electricityValueString);
			
			String entertainmentString = request.getParameter("entertainment");
			if(entertainmentString != null){entertainment = Double.parseDouble(entertainmentString);}
			double entertainmentValue = calculateAmount(entertainment, totalAllowance);
			String entertainmentValueString = Double.toString(entertainmentValue);
			session.setAttribute("entertainment", entertainmentValueString);
			
			String eatingOutString = request.getParameter("eatingOut");
			if(eatingOutString != null){eatingOut = Double.parseDouble(eatingOutString);}
			double eatingOutValue = calculateAmount(eatingOut, totalAllowance);
			String eatingOutValueString = Double.toString(eatingOutValue);
			session.setAttribute("eatingOut", eatingOutValueString);
			
			String fuelString = request.getParameter("fuel");
			if(fuelString != null){fuel = Double.parseDouble(fuelString);}
			double fuelValue = calculateAmount(fuel, totalAllowance);
			String fuelValueString = Double.toString(fuelValue);
			session.setAttribute("fuel", fuelValueString);
			
			String gasString = request.getParameter("gas");
			if(gasString != null){gas = Double.parseDouble(gasString);}
			double gasValue = calculateAmount(gas, totalAllowance);
			String gasValueString = Double.toString(gasValue);
			session.setAttribute("gas", gasValueString);
			
			String groceriesString = request.getParameter("groceries");
			if(groceriesString != null){groceries = Double.parseDouble(groceriesString);}
			double groceriesValue = calculateAmount(groceries, totalAllowance);
			String groceriesValueString = Double.toString(groceriesValue);
			session.setAttribute("groceries", groceriesValueString);
			
			String giftsString = request.getParameter("gifts");
			if(giftsString != null){gifts = Double.parseDouble(giftsString);}
			double giftsValue = calculateAmount(gifts, totalAllowance);
			String giftsValueString = Double.toString(giftsValue);
			session.setAttribute("gifts", giftsValueString);
			
			String groomingString = request.getParameter("grooming");
			if(groomingString != null){grooming = Double.parseDouble(groomingString);}
			double groomingValue = calculateAmount(grooming, totalAllowance);
			String groomingValueString = Double.toString(groomingValue);
			session.setAttribute("grooming", groomingValueString);
			
			String homeRepairString = request.getParameter("homeRepair");
			if(homeRepairString != null){homeRepair = Double.parseDouble(homeRepairString);}
			double homeRepairValue = calculateAmount(homeRepair, totalAllowance);
			String homeRepairValueString = Double.toString(homeRepairValue);
			session.setAttribute("homeRepair", homeRepairValueString);
			
			String internetString = request.getParameter("internet");
			if(internetString != null){internet = Double.parseDouble(internetString);}
			double internetValue = calculateAmount(internet, totalAllowance);
			String internetValueString = Double.toString(internetValue);
			session.setAttribute("internet", internetValueString);
			
			String medicalString = request.getParameter("medical");
			if(medicalString != null){medical = Double.parseDouble(medicalString);}
			double medicalValue = calculateAmount(medical, totalAllowance);
			String medicalValueString = Double.toString(medicalValue);
			session.setAttribute("medical", medicalValueString);
			
			String phoneString = request.getParameter("phone");
			if(phoneString != null){phone = Double.parseDouble(phoneString);}
			double phoneValue = calculateAmount(phone, totalAllowance);
			String phoneValueString = Double.toString(phoneValue);
			session.setAttribute("phone", phoneValueString);
			
			String retirementString = request.getParameter("retirement");
			if(retirementString != null){retirement = Double.parseDouble(retirementString);}
			double retirementValue = calculateAmount(retirement, totalAllowance);
			String retirementValueString = Double.toString(retirementValue);
			session.setAttribute("retirement", retirementValueString);
			
			String savingsString = request.getParameter("savings");
			if(savingsString != null){savings = Double.parseDouble(savingsString);}
			double savingsValue = calculateAmount(savings, totalAllowance);
			String savingsValueString = Double.toString(savingsValue);
			session.setAttribute("savings", savingsValueString);
			
			String spendingString = request.getParameter("spending");
			if(spendingString != null){spending = Double.parseDouble(spendingString);}
			double spendingValue = calculateAmount(spending, totalAllowance);
			String spendingValueString = Double.toString(spendingValue);
			session.setAttribute("spending", spendingValueString);
			
			String vacationString = request.getParameter("vacation");
			if(vacationString != null){vacation = Double.parseDouble(vacationString);}
			double vacationValue = calculateAmount(vacation, totalAllowance);
			String vacationValueString = Double.toString(vacationValue);
			session.setAttribute("vacation", vacationValueString);
			
			String miscString = request.getParameter("misc");
			if(miscString != null){misc = Double.parseDouble(miscString);}
			double miscValue = calculateAmount(misc, totalAllowance);
			String miscValueString = Double.toString(miscValue);
			session.setAttribute("misc", miscValueString);
			
			//Create an ArrayList with all values
			List<String> categoryValues = new ArrayList<String>();
			categoryValues.add(autoInsuranceValueString); categoryValues.add(autoMaintenanceValueString);
			categoryValues.add(babysitterValueString); categoryValues.add(booksValueString);
			categoryValues.add(cableValueString); categoryValues.add(cleaningValueString);
			categoryValues.add(clothesValueString); categoryValues.add(childrenValueString);
			categoryValues.add(donationsValueString); categoryValues.add(electricityValueString);
			categoryValues.add(entertainmentValueString); categoryValues.add(eatingOutValueString);
			categoryValues.add(fuelValueString); categoryValues.add(gasValueString);
			categoryValues.add(groceriesValueString); categoryValues.add(giftsValueString);
			categoryValues.add(groomingValueString); categoryValues.add(homeRepairValueString);
			categoryValues.add(internetValueString); categoryValues.add(medicalValueString);
			categoryValues.add(phoneValueString); categoryValues.add(retirementValueString);
			categoryValues.add(savingsValueString); categoryValues.add(spendingValueString);
			categoryValues.add(vacationValueString); categoryValues.add(miscValueString);
			

			//Set title to each category attribute CATEGORY NAME LIST
			String[] categoryNameArray = request.getParameterValues("category");
			List<String> categoryNameList = new ArrayList<String>();
			categoryNameList = Arrays.asList(categoryNameArray);
			request.setAttribute("categoryName", categoryNameList);
			
			
			//Create a table within the user_categories database 
			Connection con;
			try {
				con = (Connection)DriverManager.getConnection("jdbc:mysql://aaaj8td77qaymd.cvn0vweraivv.us-west-2.rds.amazonaws.com:3306/ebdb", "root", "Bigbones12");

				//Update user categories table
				Object userName = session.getAttribute("userName");
                String updateCategoryTable = "UPDATE user SET Auto_Insurance = ?, Auto_Maintenance = ?,"
                        + "Babysitter = ?, Books = ?,"
                        + "Cable = ?, Cleaning = ?, Clothes = ?, Children = ?,"
                        + "Donations = ?, Electricity = ?, Entertainment = ?, Eating_Out = ?,"
                        + "Fuel = ?, Gas = ?, Groceries = ?, Gifts = ?,"
                        + "Grooming = ?, Home_Repair = ?, Internet = ?, Medical = ?,"
                        + "Phone = ?, Retirement = ?, Savings = ?, Spending = ?,"
                        + "Vacation = ?, Misc = ?, Total_Allowance = ?, Original_Allowance = ?,"
                        + "Auto_Insurance_Original = ?, Auto_Maintenance_Original = ?,"
                        + "Babysitter_Original = ?, Books_Original = ?,"
                        + "Cable_Original = ?, Cleaning_Original = ?, Clothes_Original = ?, Children_Original = ?,"
                        + "Donations_Original = ?, Electricity_Original = ?, Entertainment_Original = ?, Eating_Out_Original = ?,"
                        + "Fuel_Original = ?, Gas_Original = ?, Groceries_Original = ?, Gifts_Original = ?,"
                        + "Grooming_Original = ?, Home_Repair_Original = ?, Internet_Original = ?, Medical_Original = ?,"
                        + "Phone_Original = ?, Retirement_Original = ?, Savings_Original = ?, Spending_Original = ?,"
                        + "Vacation_Original = ?, Misc_Original = ?,"
                        + "Auto_Insurance_Percent = ?, Auto_Maintenance_Percent = ?,"
                        + "Babysitter_Percent = ?, Books_Percent = ?,"
                        + "Cable_Percent = ?, Cleaning_Percent = ?, Clothes_Percent = ?, Children_Percent = ?,"
                        + "Donations_Percent = ?, Electricity_Percent = ?, Entertainment_Percent = ?, Eating_Out_Percent = ?,"
                        + "Fuel_Percent = ?, Gas_Percent = ?, Groceries_Percent = ?, Gifts_Percent = ?,"
                        + "Grooming_Percent = ?, Home_Repair_Percent = ?, Internet_Percent = ?, Medical_Percent = ?,"
                        + "Phone_Percent = ?, Retirement_Percent = ?, Savings_Percent = ?, Spending_Percent = ?,"
                        + "Vacation_Percent = ?, Misc_Percent = ?"
                        + "WHERE userName = ?";
                PreparedStatement updateCategories = (PreparedStatement)con.prepareStatement(updateCategoryTable);
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
                updateCategories.setDouble(27, totalAllowance); updateCategories.setDouble(28, totalAllowance);
               
                //Lock in the original amount of each category into the table
                updateCategories.setDouble(29, autoInsuranceValue); updateCategories.setDouble(30, autoMaintenanceValue);
                updateCategories.setDouble(31, babysitterValue); updateCategories.setDouble(32, booksValue);
                updateCategories.setDouble(33, cableValue); updateCategories.setDouble(34, cleaningValue);
                updateCategories.setDouble(35, clothesValue); updateCategories.setDouble(36, childrenValue);
                updateCategories.setDouble(37, donationsValue); updateCategories.setDouble(38, electricityValue);
                updateCategories.setDouble(39, entertainmentValue); updateCategories.setDouble(40, eatingOutValue);
                updateCategories.setDouble(41, fuelValue); updateCategories.setDouble(42, gasValue);
                updateCategories.setDouble(43, groceriesValue); updateCategories.setDouble(44, giftsValue);
                updateCategories.setDouble(45, groomingValue); updateCategories.setDouble(46, homeRepairValue);
                updateCategories.setDouble(47, internetValue); updateCategories.setDouble(48, medicalValue);
                updateCategories.setDouble(49, phoneValue); updateCategories.setDouble(50, retirementValue);
                updateCategories.setDouble(51, savingsValue); updateCategories.setDouble(52, spendingValue);
                updateCategories.setDouble(53, vacationValue); updateCategories.setDouble(54, miscValue);
               
                //Set the category percent amounts in the table
                updateCategories.setDouble(55, autoInsurance); updateCategories.setDouble(56, autoMaintenance);
                updateCategories.setDouble(57, babysitter); updateCategories.setDouble(58, books);
                updateCategories.setDouble(59, cable); updateCategories.setDouble(60, cleaning);
                updateCategories.setDouble(61, clothes); updateCategories.setDouble(62, children);
                updateCategories.setDouble(63, donations); updateCategories.setDouble(64, electricity);
                updateCategories.setDouble(65, entertainment); updateCategories.setDouble(66, eatingOut);
                updateCategories.setDouble(67, fuel); updateCategories.setDouble(68, gas);
                updateCategories.setDouble(69, groceries); updateCategories.setDouble(70, gifts);
                updateCategories.setDouble(71, grooming); updateCategories.setDouble(72, homeRepair);
                updateCategories.setDouble(73, internet); updateCategories.setDouble(74, medical);
                updateCategories.setDouble(75, phone); updateCategories.setDouble(76, retirement);
                updateCategories.setDouble(77, savings); updateCategories.setDouble(78, spending);
                updateCategories.setDouble(79, vacation); updateCategories.setDouble(80, misc);
               
                updateCategories.setObject(81, userName);
                updateCategories.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {

			}
		
			doGet(request, response);
			
		}

		out.close();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.getRequestDispatcher("/process-user-data").forward(request, response);
	
	}

}
