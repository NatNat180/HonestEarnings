package com.acc.java;

import java.io.IOException;
import java.io.PrintWriter;
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
    
    public static double calculateAmount(Double percent, Double additionalIncome, Double currentAllowance){
    	
    	return round((((percent/100) * additionalIncome) + currentAllowance), 2);	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

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
                    + "Vacation, Misc, Total_Allowance,"
                    + "Auto_Insurance_Percent, Auto_Maintenance_Percent,"
                    + "Babysitter_Percent, Books_Percent,"
                    + "Cable_Percent, Cleaning_Percent, Clothes_Percent, Children_Percent,"
                    + "Donations_Percent, Electricity_Percent, Entertainment_Percent, Eating_Out_Percent,"
                    + "Fuel_Percent, Gas_Percent, Groceries_Percent, Gifts_Percent,"
                    + "Grooming_Percent, Home_Repair_Percent, Internet_Percent, Medical_Percent,"
                    + "Phone_Percent, Retirement_Percent, Savings_Percent, Spending_Percent,"
                    + "Vacation_Percent, Misc_Percent "
                    + "FROM user WHERE userName = '" + userName + "'";
        Statement st = con.createStatement();
       
        Double currentAllowance = 0.0;
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
       
        double autoInsurancePercent = 0;
        double autoMaintenancePercent = 0;
        double babysitterPercent = 0;
        double booksPercent = 0;
        double cablePercent = 0;
        double cleaningPercent = 0;
        double clothesPercent = 0;
        double childrenPercent = 0;
        double donationsPercent = 0;
        double electricityPercent = 0;
        double entertainmentPercent = 0;
        double eatingOutPercent = 0;
        double fuelPercent = 0;
        double gasPercent = 0;
        double groceriesPercent = 0;
        double giftsPercent = 0;
        double groomingPercent = 0;
        double homeRepairPercent = 0;
        double internetPercent = 0;
        double medicalPercent = 0;
        double phonePercent = 0;
        double retirementPercent = 0;
        double savingsPercent = 0;
        double spendingPercent = 0;
        double vacationPercent = 0;
        double miscPercent = 0;
       
        String addIncome = request.getParameter("additional-income");
        Double addIncomeNum = Double.parseDouble(addIncome);
			
			if(addIncome == null){
				response.sendRedirect("/WEB-INF/home.jsp");
				out.println("<div class='alert alert-danger' role='alert'>"
						+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
						+ "<span aria-hidden='true'>&times;</span></button>"
						+ "You did not input your additional income.</div>");
			}
			
            ResultSet rs = st.executeQuery(obtainCurrentAndOriginalAllowance);
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
               
                autoInsurancePercent = rs.getDouble(28); autoMaintenancePercent = rs.getDouble(29);
                babysitterPercent = rs.getDouble(30); booksPercent = rs.getDouble(31);
                cablePercent = rs.getDouble(32); cleaningPercent = rs.getDouble(33);
                clothesPercent = rs.getDouble(34); childrenPercent = rs.getDouble(35);
                donationsPercent = rs.getDouble(36); electricityPercent = rs.getDouble(37);
                entertainmentPercent = rs.getDouble(38); eatingOutPercent = rs.getDouble(39);
                fuelPercent = rs.getDouble(40); gasPercent = rs.getDouble(41);
                groceriesPercent = rs.getDouble(42); giftsPercent = rs.getDouble(43);
                groomingPercent = rs.getDouble(44); homeRepairPercent = rs.getDouble(45);
                internetPercent = rs.getDouble(46); medicalPercent = rs.getDouble(47);
                phonePercent = rs.getDouble(48); retirementPercent = rs.getDouble(49);
                savingsPercent = rs.getDouble(50); spendingPercent = rs.getDouble(51);
                vacationPercent = rs.getDouble(52); miscPercent = rs.getDouble(53);
               
                currentAllowance = currentAllowance + addIncomeNum;           
            }
			
            //update each corresponding category under the new allowance amount           
            double autoInsuranceValue = calculateAmount(autoInsurancePercent, addIncomeNum, autoInsurance);
            String autoInsuranceValueString = Double.toString(autoInsuranceValue);
            session.setAttribute("autoInsurance", autoInsuranceValueString);

            double autoMaintenanceValue = calculateAmount(autoMaintenancePercent, addIncomeNum, autoMaintenance);
            String autoMaintenanceValueString = Double.toString(autoMaintenanceValue);
            session.setAttribute("autoMaintenance", autoMaintenanceValueString);
           
            double babysitterValue = calculateAmount(babysitterPercent, addIncomeNum, babysitter);
            String babysitterValueString = Double.toString(babysitterValue);   
            session.setAttribute("babysitter", babysitterValueString);

            double booksValue = calculateAmount(booksPercent, addIncomeNum, books);
            String booksValueString = Double.toString(booksValue);
            session.setAttribute("books", booksValueString);

            double cableValue = calculateAmount(cablePercent, addIncomeNum, cable);
            String cableValueString = Double.toString(cableValue);
            session.setAttribute("cable", cableValueString);

            double cleaningValue = calculateAmount(cleaningPercent, addIncomeNum, cleaning);
            String cleaningValueString = Double.toString(cleaningValue);
            session.setAttribute("cleaning", cleaningValueString);

            double clothesValue = calculateAmount(clothesPercent, addIncomeNum, clothes);
            String clothesValueString = Double.toString(clothesValue);
            session.setAttribute("clothes", clothesValueString);

            double childrenValue = calculateAmount(childrenPercent, addIncomeNum, children);
            String childrenValueString = Double.toString(childrenValue);
            session.setAttribute("children", childrenValueString);

            double donationsValue = calculateAmount(donationsPercent, addIncomeNum, donations);
            String donationsValueString = Double.toString(donationsValue);
            session.setAttribute("donations", donationsValueString);

            double electricityValue = calculateAmount(electricityPercent, addIncomeNum, electricity);
            String electricityValueString = Double.toString(electricityValue);
            session.setAttribute("electricity", electricityValueString);

            double entertainmentValue = calculateAmount(entertainmentPercent, addIncomeNum, entertainment);
            String entertainmentValueString = Double.toString(entertainmentValue);
            session.setAttribute("entertainment", entertainmentValueString);

            double eatingOutValue = calculateAmount(eatingOutPercent, addIncomeNum, eatingOut);
            String eatingOutValueString = Double.toString(eatingOutValue);
            session.setAttribute("eatingOut", eatingOutValueString);

            double fuelValue = calculateAmount(fuelPercent, addIncomeNum, fuel);
            String fuelValueString = Double.toString(fuelValue);
            session.setAttribute("fuel", fuelValueString);

            double gasValue = calculateAmount(gasPercent, addIncomeNum, gas);
            String gasValueString = Double.toString(gasValue);
            session.setAttribute("gas", gasValueString);

            double groceriesValue = calculateAmount(groceriesPercent, addIncomeNum, groceries);
            String groceriesValueString = Double.toString(groceriesValue);
            session.setAttribute("groceries", groceriesValueString);

            double giftsValue = calculateAmount(giftsPercent, addIncomeNum, gifts);
            String giftsValueString = Double.toString(giftsValue);
            session.setAttribute("gifts", giftsValueString);

            double groomingValue = calculateAmount(groomingPercent, addIncomeNum, grooming);
            String groomingValueString = Double.toString(groomingValue);
            session.setAttribute("grooming", groomingValueString);

            double homeRepairValue = calculateAmount(homeRepairPercent, addIncomeNum, homeRepair);
            String homeRepairValueString = Double.toString(homeRepairValue);
            session.setAttribute("homeRepair", homeRepairValueString);

            double internetValue = calculateAmount(internetPercent, addIncomeNum, internet);
            String internetValueString = Double.toString(internetValue);
            session.setAttribute("internet", internetValueString);

            double medicalValue = calculateAmount(medicalPercent, addIncomeNum, medical);
            String medicalValueString = Double.toString(medicalValue);
            session.setAttribute("medical", medicalValueString);

            double phoneValue = calculateAmount(phonePercent, addIncomeNum, phone);
            String phoneValueString = Double.toString(phoneValue);
            session.setAttribute("phone", phoneValueString);

            double retirementValue = calculateAmount(retirementPercent, addIncomeNum, retirement);
            String retirementValueString = Double.toString(retirementValue);
            session.setAttribute("retirement", retirementValueString);

            double savingsValue = calculateAmount(savingsPercent, addIncomeNum, savings);
            String savingsValueString = Double.toString(savingsValue);
            session.setAttribute("savings", savingsValueString);

            double spendingValue = calculateAmount(spendingPercent, addIncomeNum, spending);
            String spendingValueString = Double.toString(spendingValue);
            session.setAttribute("spending", spendingValueString);

            double vacationValue = calculateAmount(vacationPercent, addIncomeNum, vacation);
            String vacationValueString = Double.toString(vacationValue);
            session.setAttribute("vacation", vacationValueString);

            double miscValue = calculateAmount(miscPercent, addIncomeNum, misc);
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
