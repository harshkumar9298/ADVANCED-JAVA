package com.transactionManagement.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Fetch_Transaction_Details {

		public static void main(String[] args) {
			Scanner s = new Scanner(System.in);
			try(s;)
			{
       
				Connection con = DriverManager.getConnection
						("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
						//Creating Connection
       
				PreparedStatement ps = con.prepareStatement("select * from TransLogTab52 where haccno=?");
              //Preparing statement
				System.out.println("Enter HomeAccount No : ");
				long hAccNo=s.nextLong();
				ps.setLong(1, hAccNo);
				
				ResultSet rs = ps.executeQuery();
				//Executing query
				
				if(rs.next())
					{
						System.out.println(rs.getLong(1)+"\t"+
    			   			  rs.getLong(2)+"\t"+
    			   			  rs.getFloat(3)+"\t"+
    			   			  rs.getString(4));
					}//end of loop
				else {
					System.out.println("Invalid Account Number...");
				}
       
				con.close();
				//closing connection
			}//end of try
			catch(Exception e)
			{
			e.printStackTrace();
		}

	}

}
