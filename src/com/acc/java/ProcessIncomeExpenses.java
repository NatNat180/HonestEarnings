package com.acc.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.tomcat.jni.User;

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
    
	private static double autoInsurancePercent; private static double autoMaintenancePercent; private static double babysitterPercent;
	private static double booksPercent; private static double cablePercent; private static double cleaningPercent;
	private static double clothesPercent; private static double childrenPercent; private static double donationsPercent;
	private static double electricityPercent; private static double entertainmentPercent; private static double eatingOutPercent;
	private static double fuelPercent; private static double gasPercent; private static double groceriesPercent;
	private static double giftsPercent; private static double groomingPercent; private static double homeRepairPercent;
	private static double internetPercent; private static double medicalPercent; private static double phonePercent;
	private static double retirementPercent; private static double savingsPercent; private static double spendingPercent;
	private static double vacationPercent; private static double miscPercent;
	
	public static double getAutoInsurancePercent(){return autoInsurancePercent;}
	public static double getAutoMaintenancePercent(){return autoMaintenancePercent;}
	public static double getBabysitterPercent(){return babysitterPercent;}
	public static double getBooksPercent(){return booksPercent;}
	public static double getCablePercent(){return cablePercent;}
	public static double getCleaningPercent(){return cleaningPercent;}
	public static double getClothesPercent(){return clothesPercent;}
	public static double getChildrenPercent(){return childrenPercent;}
	public static double getDonationsPercent(){return donationsPercent;}
	public static double getElectricityPercent(){return electricityPercent;}
	public static double getEntertainmentPercent(){return entertainmentPercent;}
	public static double getEatingOutPercent(){return eatingOutPercent;}
	public static double getFuelPercent(){return fuelPercent;}
	public static double getGasPercent(){return gasPercent;}
	public static double getGroceriesPercent(){return groceriesPercent;}
	public static double getGiftsPercent(){return giftsPercent;}
	public static double getGroomingPercent(){return groomingPercent;}
	public static double getHomeRepairPercent(){return homeRepairPercent;}
	public static double getInternetPercent(){return internetPercent;}
	public static double getMedicalPercent(){return medicalPercent;}
	public static double getPhonePercent(){return phonePercent;}
	public static double getRetirementPercent(){return retirementPercent;}
	public static double getSavingsPercent(){return savingsPercent;}
	public static double getSpendingPercent(){return spendingPercent;}
	public static double getVacationPercent(){return vacationPercent;}
	public static double getMiscPercent(){return miscPercent;}
	
	public static void setAutoInsurancePercent(double percent){autoInsurancePercent = percent;}
	public static void setAutoMaintenancePercent(double percent){autoMaintenancePercent = percent;}
	public static void setBabysitterPercent(double percent){babysitterPercent = percent;}
	public static void setBooksPercent(double percent){booksPercent = percent;}
	public static void setCablePercent(double percent){cablePercent = percent;}
	public static void setCleaningPercent(double percent){cleaningPercent = percent;}
	public static void setClothesPercent(double percent){clothesPercent = percent;}
	public static void setChildrenPercent(double percent){childrenPercent = percent;}
	public static void setDonationsPercent(double percent){donationsPercent = percent;}
	public static void setElectricityPercent(double percent){electricityPercent = percent;}
	public static void setEntertainmentPercent(double percent){entertainmentPercent = percent;}
	public static void setEatingOutPercent(double percent){eatingOutPercent = percent;}
	public static void setFuelPercent(double percent){fuelPercent = percent;}
	public static void setGasPercent(double percent){gasPercent = percent;}
	public static void setGroceriesPercent(double percent){groceriesPercent = percent;}
	public static void setGiftsPercent(double percent){giftsPercent = percent;}
	public static void setGroomingPercent(double percent){groomingPercent = percent;}
	public static void setHomeRepairPercent(double percent){homeRepairPercent = percent;}
	public static void setInternetPercent(double percent){internetPercent = percent;}
	public static void setMedicalPercent(double percent){medicalPercent = percent;}
	public static void setPhonePercent(double percent){phonePercent = percent;}
	public static void setRetirementPercent(double percent){retirementPercent = percent;}
	public static void setSavingsPercent(double percent){savingsPercent = percent;}
	public static void setSpendingPercent(double percent){spendingPercent = percent;}
	public static void setVacationPercent(double percent){vacationPercent = percent;}
	public static void setMiscPercent(double percent){miscPercent = percent;}
	
	//Set up a way to access the initial value set for each category
	private static double autoInsuranceInitial; private static double autoMaintenanceInitial; private static double babysitterInitial;
	private static double booksInitial; private static double cableInitial; private static double cleaningInitial;
	private static double clothesInitial; private static double childrenInitial; private static double donationsInitial;
	private static double electricityInitial; private static double entertainmentInitial; private static double eatingOutInitial;
	private static double fuelInitial; private static double gasInitial; private static double groceriesInitial;
	private static double giftsInitial; private static double groomingInitial; private static double homeRepairInitial;
	private static double internetInitial; private static double medicalInitial; private static double phoneInitial;
	private static double retirementInitial; private static double savingsInitial; private static double spendingInitial;
	private static double vacationInitial; private static double miscInitial;
	
	public static double getAutoInsuranceInitial(){return autoInsuranceInitial;}
	public static double getAutoMaintenanceInitial(){return autoMaintenanceInitial;}
	public static double getBabysitterInitial(){return babysitterInitial;}
	public static double getBooksInitial(){return booksInitial;}
	public static double getCableInitial(){return cableInitial;}
	public static double getCleaningInitial(){return cleaningInitial;}
	public static double getClothesInitial(){return clothesInitial;}
	public static double getChildrenInitial(){return childrenInitial;}
	public static double getDonationsInitial(){return donationsInitial;}
	public static double getElectricityInitial(){return electricityInitial;}
	public static double getEntertainmentInitial(){return entertainmentInitial;}
	public static double getEatingOutInitial(){return eatingOutInitial;}
	public static double getFuelInitial(){return fuelInitial;}
	public static double getGasInitial(){return gasInitial;}
	public static double getGroceriesInitial(){return groceriesInitial;}
	public static double getGiftsInitial(){return giftsInitial;}
	public static double getGroomingInitial(){return groomingInitial;}
	public static double getHomeRepairInitial(){return homeRepairInitial;}
	public static double getInternetInitial(){return internetInitial;}
	public static double getMedicalInitial(){return medicalInitial;}
	public static double getPhoneInitial(){return phoneInitial;}
	public static double getRetirementInitial(){return retirementInitial;}
	public static double getSavingsInitial(){return savingsInitial;}
	public static double getSpendingInitial(){return spendingInitial;}
	public static double getVacationInitial(){return vacationInitial;}
	public static double getMiscInitial(){return miscInitial;}
	
	public static void setAutoInsuranceInitial(double value){autoInsuranceInitial = value;}
	public static void setAutoMaintenanceInitial(double value){autoMaintenanceInitial = value;}
	public static void setBabysitterInitial(double value){babysitterInitial = value;}
	public static void setBooksInitial(double value){booksInitial = value;}
	public static void setCableInitial(double value){cableInitial = value;}
	public static void setCleaningInitial(double value){cleaningInitial = value;}
	public static void setClothesInitial(double value){clothesInitial = value;}
	public static void setChildrenInitial(double value){childrenInitial = value;}
	public static void setDonationsInitial(double value){donationsInitial = value;}
	public static void setElectricityInitial(double value){electricityInitial = value;}
	public static void setEntertainmentInitial(double value){entertainmentInitial = value;}
	public static void setEatingOutInitial(double value){eatingOutInitial = value;}
	public static void setFuelInitial(double value){fuelInitial = value;}
	public static void setGasInitial(double value){gasInitial = value;}
	public static void setGroceriesInitial(double value){groceriesInitial = value;}
	public static void setGiftsInitial(double value){giftsInitial = value;}
	public static void setGroomingInitial(double value){groomingInitial = value;}
	public static void setHomeRepairInitial(double value){homeRepairInitial = value;}
	public static void setInternetInitial(double value){internetInitial = value;}
	public static void setMedicalInitial(double value){medicalInitial = value;}
	public static void setPhoneInitial(double value){phoneInitial = value;}
	public static void setRetirementInitial(double value){retirementInitial = value;}
	public static void setSavingsInitial(double value){savingsInitial = value;}
	public static void setSpendingInitial(double value){spendingInitial = value;}
	public static void setVacationInitial(double value){vacationInitial = value;}
	public static void setMiscInitial(double value){miscInitial = value;}

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
			
			setAutoInsurancePercent(autoInsurance);
			setAutoMaintenancePercent(autoMaintenance); 
			setBabysitterPercent(babysitter);
			setBooksPercent(books);
			setCablePercent(cable);
			setCleaningPercent(cleaning);
			setClothesPercent(clothes);
			setChildrenPercent(children);
			setDonationsPercent(donations);
			setElectricityPercent(electricity);
			setEntertainmentPercent(entertainment);
			setEatingOutPercent(eatingOut);
			setFuelPercent(fuel);
			setGasPercent(gas);
			setGroceriesPercent(groceries);
			setGiftsPercent(gifts);
			setGroomingPercent(grooming);
			setHomeRepairPercent(homeRepair);
			setInternetPercent(internet);
			setMedicalPercent(medical);
			setPhonePercent(phone);
			setRetirementPercent(retirement);
			setSavingsPercent(savings);
			setSpendingPercent(spending);
			setVacationPercent(vacation);
			setMiscPercent(misc);
			
			//Set the initial category amount to the category value created here
			setAutoInsuranceInitial(autoInsuranceValue);
			setAutoMaintenanceInitial(autoMaintenanceValue); 
			setBabysitterInitial(babysitterValue);
			setBooksInitial(booksValue);
			setCableInitial(cableValue);
			setCleaningInitial(cleaningValue);
			setClothesInitial(clothesValue);
			setChildrenInitial(childrenValue);
			setDonationsInitial(donationsValue);
			setElectricityInitial(electricityValue);
			setEntertainmentInitial(entertainmentValue);
			setEatingOutInitial(eatingOutValue);
			setFuelInitial(fuelValue);
			setGasInitial(gasValue);
			setGroceriesInitial(groceriesValue);
			setGiftsInitial(giftsValue);
			setGroomingInitial(groomingValue);
			setHomeRepairInitial(homeRepairValue);
			setInternetInitial(internetValue);
			setMedicalInitial(medicalValue);
			setPhoneInitial(phoneValue);
			setRetirementInitial(retirementValue);
			setSavingsInitial(savingsValue);
			setSpendingInitial(spendingValue);
			setVacationInitial(vacationValue);
			setMiscInitial(miscValue);

			//Set title to each category attribute CATEGORY NAME LIST
			String[] categoryNameArray = request.getParameterValues("category");
			List<String> categoryNameList = new ArrayList<String>();
			categoryNameList = Arrays.asList(categoryNameArray);
			request.setAttribute("categoryName", categoryNameList);
			
			EntityManager em = com.acc.java.DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			
			//Create a table within the user_categories database 
			Connection con;
			try {
				trans.begin();
				con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "Bigbones12");

				//Update user categories table
				Object userName = session.getAttribute("userName");
				String updateCategoryTable = "UPDATE user SET Auto_Insurance = ?, Auto_Maintenance = ?,"
						+ "Babysitter = ?, Books = ?,"
						+ "Cable = ?, Cleaning = ?, Clothes = ?, Children = ?,"
						+ "Donations = ?, Electricity = ?, Entertainment = ?, Eating_Out = ?,"
						+ "Fuel = ?, Gas = ?, Groceries = ?, Gifts = ?,"
						+ "Grooming = ?, Home_Repair = ?, Internet = ?, Medical = ?,"
						+ "Phone = ?, Retirement = ?, Savings = ?, Spending = ?,"
						+ "Vacation = ?, Misc = ?, Total_Allowance = ?, Original_Allowance = ?"
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
				updateCategories.setObject(29, userName);
				updateCategories.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				trans.rollback();
			} finally {
				em.close();
			}
		
			doGet(request, response);
			
		}

		out.close();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.getRequestDispatcher("/process-user-data").forward(request, response);
	
	}

}
