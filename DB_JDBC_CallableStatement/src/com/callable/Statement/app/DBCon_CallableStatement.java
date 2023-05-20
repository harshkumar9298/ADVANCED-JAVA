package com.callable.Statement.app;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class DBCon_CallableStatement {
	
	public static void main(String[] args) {
		 Scanner s = new Scanner(System.in);
			 try(s;){
				 	try {
						 System.out.println("Enter the CustId:"); 
						 String cId = s.nextLine();
						 System.out.println("Enter the CustFirstName:");
						 String fName = s.nextLine();
						 System.out.println("Enter the CustLastName:");
						 String lName = s.nextLine();
						 System.out.println("Enter the hNo:");
						 String hNo = s.nextLine();
						 System.out.println("Enter the StreetName:"); 
						 String sName = s.nextLine();
						 System.out.println("Enter the City:"); 
						 String city = s.nextLine();
						 System.out.println("Enter the PinCode:"); 
						 int pinCode = Integer.parseInt(s.nextLine()); 
						 System.out.println("Enter the MailId:"); 
						 String mId = s.nextLine();
						 System.out.println("Enter the PhoneNo:"); 
						 long phNo = Long.parseLong(s.nextLine());
						 Connection con = DriverManager.getConnection
								 ("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
						 CallableStatement cs = con.prepareCall 
								                ("{call CustInsert52(?,?,?,?,?,?,?,?,?)}");
						 cs.setString(1,cId);
						 cs.setString(2,fName);
						 cs.setString(3, lName);
						 cs.setString(4, hNo);
						 cs.setString(5, sName);
						 cs.setString(6, city);
						 cs.setInt(7, pinCode);
						 cs.setString(8, mId);
						 cs.setLong(9, phNo);
					
						 cs.execute();
						 System.out.println("Procedure executed Successfully...");
						 System.out.println("CustData updated...."); 
				 	}
				 catch(Exception e) 
				 	 {
					 e.printStackTrace();
					 } 
			 }//end of try
		}
}
