package com.model.transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaydeep.pkg1.Transac;

public class Deposit extends HttpServlet {
	boolean flag=false;
	public void dep(int deposit,int withdrawal)
	{
		try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
            PreparedStatement psmt=con.prepareStatement("insert into transaction values(?,?,?)");
            
           GetBalance bal=new GetBalance();
           int balance=bal.getBalance();
           if(withdrawal==0)
           {
               System.out.println("first");
                balance=balance+deposit;
                psmt.setInt(1,deposit);
                psmt.setInt(2,0);
                psmt.setInt(3,balance);
                
                int rs=psmt.executeUpdate();
                if(rs!=0)
                {
                    //System.out.println("Inserted");
                	flag=true;
                }
           }
             if(withdrawal!=0&&deposit!=0&&balance>withdrawal)
           {
               System.out.println("second");
               balance=deposit+balance-withdrawal;
               psmt.setInt(1,deposit);
               psmt.setInt(2,withdrawal);
               psmt.setInt(3,balance);
               
               
               int rs=psmt.executeUpdate();
               if(rs!=0)
               {
                   //System.out.println("Inserted");
            	   flag=true;
               }
           }
             
               if(withdrawal!=0&&deposit==0&&balance>withdrawal)
           {
               System.out.println("second");
               balance=deposit+balance-withdrawal;
               psmt.setInt(1,0);
               psmt.setInt(2,withdrawal);
               psmt.setInt(3,balance);
               
               
               
               int rs=psmt.executeUpdate();
               if(rs!=0)
               {
                   //System.out.println("Inserted");
            	   flag=true;
               }
           }
               
               else if(balance<withdrawal)
               {
                   //System.out.println("Money not available");
            	   flag=false;
               }
               
             
            
        }catch(Exception e){e.printStackTrace();}
		System.out.println(flag);
				
	}
		
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String username=req.getParameter("username");
		Transac obj=new Transac();
		boolean result=obj.check_User(username);
		int deposit_amount=0;
		if(result==true)
		{
			 deposit_amount=Integer.parseInt(req.getParameter("depositamount"));
		}
		
		Deposit d=new Deposit();
		d.dep(deposit_amount, 0);
		System.out.println(flag);
		
		if(flag==false)
		{
			res.sendRedirect("SuccessfullWithdraw.html");
		}
		if(flag==true)
		{
			res.sendRedirect("FailedWithfraw.html");
		}
			
	}

}
