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


@WebServlet("/just-got-paid")
public class JustGotPaid extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JustGotPaid() {
        super();

    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public static double calculateAmount(Double initialAmount, Double currentAmount){
    	
    	return round((initialAmount + currentAmount), 2);	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object userName = session.getAttribute("userName");
		//Establish connection with database
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://aaaj8td77qaymd.cvn0vweraivv.us-west-2.rds.amazonaws.com:3306/ebdb", "root", "Bigbones12");
            String obtainCurrentAndOriginalAllowance = "SELECT Auto_Insurance, Auto_Maintenance,"
                    + "Babysitter, Books,"
                    + "Cable, Cleaning, Clothes, Children,"
                    + "Donations, Electricity, Entertainment, Eating_Out,"
                    + "Fuel, Gas, Groceries, Gifts,"
                    + "Grooming, Home_Repair, Internet, Medical,"
                    + "Phone, Retirement, Savings, Spending,"
                    + "Vacation, Misc, Total_Allowance, Original_Allowance,"
                    + "Auto_Insurance_Original, Auto_Maintenance_Original,"
                    + "Babysitter_Original, Books_Original,"
                    + "Cable_Original, Cleaning_Original, Clothes_Original, Children_Original,"
                    + "Donations_Original, Electricity_Original, Entertainment_Original, Eating_Out_Original,"
                    + "Fuel_Original, Gas_Original, Groceries_Original, Gifts_Original,"
                    + "Grooming_Original, Home_Repair_Original, Internet_Original, Medical_Original,"
                    + "Phone_Original, Retirement_Original, Savings_Original, Spending_Original,"
                    + "Vacation_Original, Misc_Original "
                    + "FROM user WHERE userName = '" + userName + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(obtainCurrentAndOriginalAllowance);
       
        Double currentAllowance = 0.0;
        Double originalAllowance = 0.0;
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
       
        double autoInsuranceOriginal = 0;
        double autoMaintenanceOriginal = 0;
        double babysitterOriginal = 0;
        double booksOriginal = 0;
        double cableOriginal = 0;
        double cleaningOriginal = 0;
        double clothesOriginal = 0;
        double childrenOriginal = 0;
        double donationsOriginal = 0;
        double electricityOriginal = 0;
        double entertainmentOriginal = 0;
        double eatingOutOriginal = 0;
        double fuelOriginal = 0;
        double gasOriginal = 0;
        double groceriesOriginal = 0;
        double giftsOriginal = 0;
        double groomingOriginal = 0;
        double homeRepairOriginal = 0;
        double internetOriginal = 0;
        double medicalOriginal = 0;
        double phoneOriginal = 0;
        double retirementOriginal = 0;
        double savingsOriginal = 0;
        double spendingOriginal = 0;
        double vacationOriginal = 0;
        double miscOriginal = 0;
       
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
            originalAllowance = rs.getDouble(28);
           
            autoInsuranceOriginal = rs.getDouble(29); autoMaintenanceOriginal = rs.getDouble(30);
            babysitterOriginal = rs.getDouble(31); booksOriginal = rs.getDouble(32);
            cableOriginal = rs.getDouble(33); cleaningOriginal = rs.getDouble(34);
            clothesOriginal = rs.getDouble(35); childrenOriginal = rs.getDouble(36);
            donationsOriginal = rs.getDouble(37); electricityOriginal = rs.getDouble(38);
            entertainmentOriginal = rs.getDouble(39); eatingOutOriginal = rs.getDouble(40);
            fuelOriginal = rs.getDouble(41); gasOriginal = rs.getDouble(42);
            groceriesOriginal = rs.getDouble(43); giftsOriginal = rs.getDouble(44);
            groomingOriginal = rs.getDouble(45); homeRepairOriginal = rs.getDouble(46);
            internetOriginal = rs.getDouble(47); medicalOriginal = rs.getDouble(48);
            phoneOriginal = rs.getDouble(49); retirementOriginal = rs.getDouble(50);
            savingsOriginal = rs.getDouble(51); spendingOriginal = rs.getDouble(52);
            vacationOriginal = rs.getDouble(53); miscOriginal = rs.getDouble(54);
           
            currentAllowance = currentAllowance + originalAllowance;           
        }

			
        //update each corresponding category under the new allowance amount           
        double autoInsuranceValue = calculateAmount(autoInsuranceOriginal, autoInsurance);
        String autoInsuranceValueString = Double.toString(autoInsuranceValue);
        session.setAttribute("autoInsurance", autoInsuranceValueString);

        double autoMaintenanceValue = calculateAmount(autoMaintenanceOriginal, autoMaintenance);
        String autoMaintenanceValueString = Double.toString(autoMaintenanceValue);
        session.setAttribute("autoMaintenance", autoMaintenanceValueString);
       
        double babysitterValue = calculateAmount(babysitterOriginal, babysitter);
        String babysitterValueString = Double.toString(babysitterValue);   
        session.setAttribute("babysitter", babysitterValueString);

        double booksValue = calculateAmount(booksOriginal, books);
        String booksValueString = Double.toString(booksValue);
        session.setAttribute("books", booksValueString);

        double cableValue = calculateAmount(cableOriginal, cable);
        String cableValueString = Double.toString(cableValue);
        session.setAttribute("cable", cableValueString);

        double cleaningValue = calculateAmount(cleaningOriginal, cleaning);
        String cleaningValueString = Double.toString(cleaningValue);
        session.setAttribute("cleaning", cleaningValueString);

        double clothesValue = calculateAmount(clothesOriginal, clothes);
        String clothesValueString = Double.toString(clothesValue);
        session.setAttribute("clothes", clothesValueString);

        double childrenValue = calculateAmount(childrenOriginal, children);
        String childrenValueString = Double.toString(childrenValue);
        session.setAttribute("children", childrenValueString);

        double donationsValue = calculateAmount(donationsOriginal, donations);
        String donationsValueString = Double.toString(donationsValue);
        session.setAttribute("donations", donationsValueString);

        double electricityValue = calculateAmount(electricityOriginal, electricity);
        String electricityValueString = Double.toString(electricityValue);
        session.setAttribute("electricity", electricityValueString);

        double entertainmentValue = calculateAmount(entertainmentOriginal, entertainment);
        String entertainmentValueString = Double.toString(entertainmentValue);
        session.setAttribute("entertainment", entertainmentValueString);

        double eatingOutValue = calculateAmount(eatingOutOriginal, eatingOut);
        String eatingOutValueString = Double.toString(eatingOutValue);
        session.setAttribute("eatingOut", eatingOutValueString);

        double fuelValue = calculateAmount(fuelOriginal, fuel);
        String fuelValueString = Double.toString(fuelValue);
        session.setAttribute("fuel", fuelValueString);

        double gasValue = calculateAmount(gasOriginal, gas);
        String gasValueString = Double.toString(gasValue);
        session.setAttribute("gas", gasValueString);

        double groceriesValue = calculateAmount(groceriesOriginal, groceries);
        String groceriesValueString = Double.toString(groceriesValue);
        session.setAttribute("groceries", groceriesValueString);

        double giftsValue = calculateAmount(giftsOriginal, gifts);
        String giftsValueString = Double.toString(giftsValue);
        session.setAttribute("gifts", giftsValueString);

        double groomingValue = calculateAmount(groomingOriginal, grooming);
        String groomingValueString = Double.toString(groomingValue);
        session.setAttribute("grooming", groomingValueString);

        double homeRepairValue = calculateAmount(homeRepairOriginal, homeRepair);
        String homeRepairValueString = Double.toString(homeRepairValue);
        session.setAttribute("homeRepair", homeRepairValueString);

        double internetValue = calculateAmount(internetOriginal, internet);
        String internetValueString = Double.toString(internetValue);
        session.setAttribute("internet", internetValueString);

        double medicalValue = calculateAmount(medicalOriginal, medical);
        String medicalValueString = Double.toString(medicalValue);
        session.setAttribute("medical", medicalValueString);

        double phoneValue = calculateAmount(phoneOriginal, phone);
        String phoneValueString = Double.toString(phoneValue);
        session.setAttribute("phone", phoneValueString);

        double retirementValue = calculateAmount(retirementOriginal, retirement);
        String retirementValueString = Double.toString(retirementValue);
        session.setAttribute("retirement", retirementValueString);

        double savingsValue = calculateAmount(savingsOriginal, savings);
        String savingsValueString = Double.toString(savingsValue);
        session.setAttribute("savings", savingsValueString);

        double spendingValue = calculateAmount(spendingOriginal, spending);
        String spendingValueString = Double.toString(spendingValue);
        session.setAttribute("spending", spendingValueString);

        double vacationValue = calculateAmount(vacationOriginal, vacation);
        String vacationValueString = Double.toString(vacationValue);
        session.setAttribute("vacation", vacationValueString);

        double miscValue = calculateAmount(miscOriginal, misc);
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/process-user-data").forward(request, response);
	}

}
