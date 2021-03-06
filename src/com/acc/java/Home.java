package com.acc.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Honest Earnings
 * @author Nathan Argall
 * 
 *
 * 
 */
@WebServlet("")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//If user is not logged in, send them to login page. Else, send them home. 
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("userName") == null){
			request.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			//request.getServletContext().getRequestDispatcher("/WEB-INF/sql.jsp").forward(request, response);
			//response.sendRedirect("/new");
		} else {
			//request.getServletContext().getRequestDispatcher("/process-user-data").forward(request, response);
			response.sendRedirect("/process-user-data");
		}
	}

}
