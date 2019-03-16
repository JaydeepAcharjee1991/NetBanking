package com.model.transaction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaydeep.pkg1.Transac;

public class WithDrawal extends HttpServlet {
	 int withdraw_amount=0;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String username=req.getParameter("username");
		Transac obj=new Transac();
		boolean result=obj.check_User(username);
		int withdraw_amount=0;
		if(result==true)
		{
			 withdraw_amount=Integer.parseInt(req.getParameter("Withamount"));
		}
			
		Deposit d=new Deposit();
		d.dep(0, withdraw_amount);
		if(d.flag==true)
		{
			res.sendRedirect("SuccessfullWithdraw.html");
		}
		else
		{
			res.sendRedirect("FailedWithfraw.html");
		}
		

	}

}
