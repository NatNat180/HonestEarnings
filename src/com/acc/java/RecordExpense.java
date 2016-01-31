package com.acc.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object userName = session.getAttribute("userName");
		
		//DO this for all categories!
		String autoInsuranceExpenseString = request.getParameter("Auto Insurance");
		double autoInsuranceExpense = Double.parseDouble(autoInsuranceExpenseString);
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase", "root", "Bigbones12");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
