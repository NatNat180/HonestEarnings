package com.acc.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/additional-income")
public class AdditionalIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdditionalIncome() {
        super();

    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public static double calculateAmount(Double percent, Double currentIncome){
    	
    	return round(((percent/100) * currentIncome), 2);	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object userName = session.getAttribute("userName");
		//Establish connection with database
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "Bigbones12");
			String obtainCurrentAndOriginalAllowance = "SELECT Total_Allowance, Original_Allowance "
					+ "FROM user WHERE userName = '" + userName + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(obtainCurrentAndOriginalAllowance);
			
			Double currentAllowance = 0.0;
			
			String addIncome = request.getParameter("additional-income");
			Double addIncomeNum = Double.parseDouble(addIncome);
			
			//obtain the original allowance value
			while(rs.next()){
				currentAllowance = rs.getDouble(1);
				currentAllowance = currentAllowance + addIncomeNum;			
			}
			
			//update each corresponding category under the new allowance amount			
			double autoInsuranceValue = calculateAmount(ProcessIncomeExpenses.getAutoInsurancePercent(), currentAllowance);
			String autoInsuranceValueString = Double.toString(autoInsuranceValue);
			session.setAttribute("autoInsurance", autoInsuranceValueString);

			double autoMaintenanceValue = calculateAmount(ProcessIncomeExpenses.getAutoMaintenancePercent(), currentAllowance);
			String autoMaintenanceValueString = Double.toString(autoMaintenanceValue);
			session.setAttribute("autoMaintenance", autoMaintenanceValueString);
			
			double babysitterValue = calculateAmount(ProcessIncomeExpenses.getBabysitterPercent(), currentAllowance);
			String babysitterValueString = Double.toString(babysitterValue);	
			session.setAttribute("babysitter", babysitterValueString);

			double booksValue = calculateAmount(ProcessIncomeExpenses.getBooksPercent(), currentAllowance);
			String booksValueString = Double.toString(booksValue);
			session.setAttribute("books", booksValueString);

			double cableValue = calculateAmount(ProcessIncomeExpenses.getCablePercent(), currentAllowance);
			String cableValueString = Double.toString(cableValue);
			session.setAttribute("cable", cableValueString);

			double cleaningValue = calculateAmount(ProcessIncomeExpenses.getCleaningPercent(), currentAllowance);
			String cleaningValueString = Double.toString(cleaningValue);
			session.setAttribute("cleaning", cleaningValueString);

			double clothesValue = calculateAmount(ProcessIncomeExpenses.getClothesPercent(), currentAllowance);
			String clothesValueString = Double.toString(clothesValue);
			session.setAttribute("clothes", clothesValueString);

			double childrenValue = calculateAmount(ProcessIncomeExpenses.getChildrenPercent(), currentAllowance);
			String childrenValueString = Double.toString(childrenValue);
			session.setAttribute("children", childrenValueString);

			double donationsValue = calculateAmount(ProcessIncomeExpenses.getDonationsPercent(), currentAllowance);
			String donationsValueString = Double.toString(donationsValue);
			session.setAttribute("donations", donationsValueString);

			double electricityValue = calculateAmount(ProcessIncomeExpenses.getElectricityPercent(), currentAllowance);
			String electricityValueString = Double.toString(electricityValue);
			session.setAttribute("electricity", electricityValueString);

			double entertainmentValue = calculateAmount(ProcessIncomeExpenses.getEntertainmentPercent(), currentAllowance);
			String entertainmentValueString = Double.toString(entertainmentValue);
			session.setAttribute("entertainment", entertainmentValueString);

			double eatingOutValue = calculateAmount(ProcessIncomeExpenses.getEatingOutPercent(), currentAllowance);
			String eatingOutValueString = Double.toString(eatingOutValue);
			session.setAttribute("eatingOut", eatingOutValueString);

			double fuelValue = calculateAmount(ProcessIncomeExpenses.getFuelPercent(), currentAllowance);
			String fuelValueString = Double.toString(fuelValue);
			session.setAttribute("fuel", fuelValueString);

			double gasValue = calculateAmount(ProcessIncomeExpenses.getGasPercent(), currentAllowance);
			String gasValueString = Double.toString(gasValue);
			session.setAttribute("gas", gasValueString);

			double groceriesValue = calculateAmount(ProcessIncomeExpenses.getGroceriesPercent(), currentAllowance);
			String groceriesValueString = Double.toString(groceriesValue);
			session.setAttribute("groceries", groceriesValueString);

			double giftsValue = calculateAmount(ProcessIncomeExpenses.getGiftsPercent(), currentAllowance);
			String giftsValueString = Double.toString(giftsValue);
			session.setAttribute("gifts", giftsValueString);

			double groomingValue = calculateAmount(ProcessIncomeExpenses.getGroomingPercent(), currentAllowance);
			String groomingValueString = Double.toString(groomingValue);
			session.setAttribute("grooming", groomingValueString);

			double homeRepairValue = calculateAmount(ProcessIncomeExpenses.getHomeRepairPercent(), currentAllowance);
			String homeRepairValueString = Double.toString(homeRepairValue);
			session.setAttribute("homeRepair", homeRepairValueString);

			double internetValue = calculateAmount(ProcessIncomeExpenses.getInternetPercent(), currentAllowance);
			String internetValueString = Double.toString(internetValue);
			session.setAttribute("internet", internetValueString);

			double medicalValue = calculateAmount(ProcessIncomeExpenses.getMedicalPercent(), currentAllowance);
			String medicalValueString = Double.toString(medicalValue);
			session.setAttribute("medical", medicalValueString);

			double phoneValue = calculateAmount(ProcessIncomeExpenses.getPhonePercent(), currentAllowance);
			String phoneValueString = Double.toString(phoneValue);
			session.setAttribute("phone", phoneValueString);

			double retirementValue = calculateAmount(ProcessIncomeExpenses.getRetirementPercent(), currentAllowance);
			String retirementValueString = Double.toString(retirementValue);
			session.setAttribute("retirement", retirementValueString);

			double savingsValue = calculateAmount(ProcessIncomeExpenses.getSavingsPercent(), currentAllowance);
			String savingsValueString = Double.toString(savingsValue);
			session.setAttribute("savings", savingsValueString);

			double spendingValue = calculateAmount(ProcessIncomeExpenses.getSpendingPercent(), currentAllowance);
			String spendingValueString = Double.toString(spendingValue);
			session.setAttribute("spending", spendingValueString);

			double vacationValue = calculateAmount(ProcessIncomeExpenses.getSpendingPercent(), currentAllowance);
			String vacationValueString = Double.toString(vacationValue);
			session.setAttribute("vacation", vacationValueString);

			double miscValue = calculateAmount(ProcessIncomeExpenses.getMiscPercent(), currentAllowance);
			String miscValueString = Double.toString(miscValue);
			session.setAttribute("misc", miscValueString);
			
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
			updateCategories.setDouble(27, currentAllowance); updateCategories.setObject(28, userName);
			
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
