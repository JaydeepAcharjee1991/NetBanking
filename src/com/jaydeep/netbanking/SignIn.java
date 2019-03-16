package com.jaydeep.netbanking;

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

public class SignIn extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username=req.getParameter("username");
		String password=req.getParameter("password");

		PrintWriter out=resp.getWriter();

		Connection con=null;
		Statement smt=null;
		ResultSet rs=null;
		boolean flag =true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
			smt=con.createStatement();
			rs=smt.executeQuery("select * from createaccount1");

			while(rs.next())
			{
				String user=rs.getString("username");
				String pass=rs.getString("password");

				if(user.equals(username)&&pass.equals(password))
				{
					flag=true;
					//out.println("welcome to netbanking");
					resp.sendRedirect("Transaction.html");
					break;
				}
				else
				{
					flag=false;

				}
			}
			if(flag==false)
			{
				//out.println("Error login");
				resp.sendRedirect("ErrorTransac.html");
			}

		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(con!=null) {con.close();}if(smt!=null) {smt.close();}if(rs!=null) {rs.close();}
			}catch(Exception e) {e.printStackTrace();}
		}


	}

}
