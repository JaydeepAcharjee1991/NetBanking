package com.jaydeep.netbanking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaydeep.pkg1.Authentication;
import com.mysql.jdbc.Driver;

public class SignUp extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String fName=req.getParameter("fName");
		String mName=req.getParameter("mName");
		String lName=req.getParameter("lName");

		String gender[]=req.getParameterValues("gender");

		String dob=req.getParameter("datefind");
		String email=req.getParameter("email");

		String address1=req.getParameter("address1");
		String address2=req.getParameter("address2");

		String city=req.getParameter("city");
		String state=req.getParameter("State");
		String pincode=req.getParameter("pincode");
		String  phone=req.getParameter("phone");
		String username=req.getParameter("user");
		String password=req.getParameter("password");
		String confrmPassword=req.getParameter("Confirm password");
		
		Authentication obj1=new Authentication();
		boolean pass_check=obj1.pasword(password, confrmPassword);

		Connection con=null;
		PreparedStatement psmt=null;
		PrintWriter out=res.getWriter();
		boolean result=false;
		
		if(pass_check==true) {
		
		try {
			Driver ref=new Driver();
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
			psmt=con.prepareStatement("insert into createaccount values(?,?,?,?,?,?)");

			Authentication obj=new Authentication();
			result=obj.authentication(email);

			psmt.setString(1, fName);
			psmt.setString(2, mName);
			psmt.setString(3, lName);
			psmt.setString(4, gender[0]);
			psmt.setString(5, dob);

			if(result==true)
			{
				psmt.setString(6,email);
				int rs=psmt.executeUpdate();
			
			}
			else
			{
				res.sendRedirect("EmailIdDup.html");
				//out.println("Email already present");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	        
		if(result==true)
		{
			Connection conn=null;
			PreparedStatement psmts=null;
			try
			{
				Driver Ref=new Driver();
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
				psmts=con.prepareStatement("insert into createaccount1 values(?,?,?,?,?,?,?,?)");
				
				Authentication obj=new Authentication();
				boolean new_res=obj.authentication1(phone);
				if(new_res==true)
				{
					psmts.setString(1,phone);			
				}
				else
				{
					res.sendRedirect("PhoneNumberDup.html");
					//out.println("Phone number exists");
				}
				psmts.setString(2,address1);
				psmts.setString(3,address2);
				psmts.setString(4,city);
			    psmts.setString(5,state);
			    psmts.setString(6,pincode);
			    psmts.setString(7,username);
			    psmts.setString(8,password);
				
			    int rs=psmts.executeUpdate();
				if(rs!=0)
				{
					res.sendRedirect("Successfull.html");
					//out.println("Profile Updated");	
				}
					
				
			}catch(Exception e) {e.printStackTrace();}
		}
	}
		else
		{
			res.sendRedirect("PassWordMisMatch.html");
			//out.println("Paassword didn't matched");
		}
	

	}

}
