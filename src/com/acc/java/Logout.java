package com.acc.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);
		
		HttpSession session = request.getSession();
		session.invalidate();
		
<<<<<<< HEAD
		out.print("<div class='alert alert-success' role='alert'>"
				+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
				+ "<span aria-hidden='true'>&times;</span></button>"
				+ "You have successfully logged out!</div>");
=======
		out.print("You have successfully logged out!");
>>>>>>> 3e24079e0d4621833432c953dbe599c4487cf0d0
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
