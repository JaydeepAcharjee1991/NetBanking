package com.model.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetBalance {
	
	public int getBalance()throws Exception
    {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking?user=root&&password=root");
            Statement stm=con.createStatement();
            
            ResultSet rs=stm.executeQuery("select balance from transaction");
            if(rs.next()==false)
            {
             return 0;   
            }
            else
            {
                 rs.last();
                 return rs.getInt("balance");
            }
                             
    }

}
