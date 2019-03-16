package com.jaydeep.pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Authentication {
	
	public boolean authentication(String email)
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
		    Statement stm=con.createStatement();
		    ResultSet rs=stm.executeQuery("select email from createaccount");
		    
		    while(rs.next())
		    {
		    	String result=rs.getString("email");
		    	if(result==null)
		    	{
		    		return true;
		    	}
		    	if(email.equals(result))
		    	{
		    		return false;
		    	}
		    	else {
		    		return false;
		    	}
		    }
			
		}catch(Exception e) {e.printStackTrace();}
		
		return true;
	}
	public boolean authentication1(String num)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
		    Statement stm=con.createStatement();
		    ResultSet rs=stm.executeQuery("select phone from createaccount1");
		    
		    while(rs.next())
		    {
		    	String result=rs.getString("phone");
		    	if(result==null)
		    	{
		    		return true;
		    	}
		    	if(num.equals(result))
		    	{
		    		return false;
		    	}
		    	else {
		    		return false;
		    	}
		    }
		    
		    
		}catch(Exception e) {e.printStackTrace();}
		return true;
		
	}
	public boolean pasword(String pass,String conf_pas)
	{
		if(pass.equals(conf_pas))
		{
			return true;
		}
		else
			return false;
	}
	public boolean userId(String name)
	{

		try{
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
		    Statement stm=con.createStatement();
		    ResultSet rs=stm.executeQuery("select username from createaccount1");
		    
		    while(rs.next())
		    {
		    	String result=rs.getString("username");
		    	
		    	if(name.equals(result))
		    	{
		    		return true;
		    	}
		    	else {
		    		return false;
		    	}
		    }
		    
		    
		}catch(Exception e) {e.printStackTrace();}
		return true;
		
	}

}
