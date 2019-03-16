package com.jaydeep.pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Transac {
	
	public boolean check_User(String name)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
			Statement stm=con.createStatement();
			
			ResultSet rs= stm.executeQuery("select username from createaccount1");
			while(rs.next())
			{
				String user=rs.getString("username");
				if(name.equals(user))
				{
					return true;
				}
				else
					return false;
			}
			
		}catch(Exception e) {e.printStackTrace();}
		return true;
	}
	public int check_balance()
	{
		int sum=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
			Statement stm=con.createStatement();
			
			ResultSet rs=stm.executeQuery("select balance from trans");
			while(rs.next())
			{
				 sum =rs.getInt("balance");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
         
		return sum;
	}
	
	
}
