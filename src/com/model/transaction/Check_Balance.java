package com.model.transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaydeep.pkg1.Transac;

public class Check_Balance extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String option=request.getParameter("CheckBalance");
		PrintWriter out=response.getWriter();
		Transac obj=new Transac();
		boolean result=obj.check_User(username);
		if(result==false)
		{
			response.sendRedirect("transactfailed.html");
		}
		if(result==true)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
				Statement stm=con.createStatement();
				
				ResultSet set=stm.executeQuery("select balance from transaction");
				if(set.next()!=false)
				{
					set.last();
					int balance=set.getInt("balance");
					out.print("The Net Balance is "+balance);
				}
				
				
			}catch(Exception e) {e.printStackTrace();}
		}
	}

}
