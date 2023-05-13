package com.RegistrationLogin.module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DBCon_Register_Login {
	public static void main(String args[]) {
	Scanner s=new Scanner(System.in);
	try(s;){
		  Connection con = DriverManager.getConnection
				    ("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
				    				           //Creating Connection
		  PreparedStatement ps1=con.prepareStatement
				  ("insert into UserReg52 values(?,?,?,?,?,?,?)");
		  PreparedStatement ps2=con.prepareStatement
				  ("select uname, pword from UserReg52 where uname=? and pword=?");
		  PreparedStatement ps3=con.prepareStatement
				  ("select * from UserReg52 where uname=? and pword=?");
		  PreparedStatement ps4 = con.prepareStatement
				  ("update UserReg52 set addr=?,mailid=?,phno=? where uname=? and pword=?");
		  while(true) {
			  System.out.println("****Choice****");
			  System.out.println("\t1.Register"
			  		+ "\n\t2.Login"
			  		+ "\n\t3.exit");
			  System.out.println("Enter the Choice:");
			  int choice = Integer.parseInt(s.nextLine());
			  switch(choice) {
			  case 1:
				  System.out.println("Enter the UserName:");
				  String uName = s.nextLine();
				  System.out.println("Enter the PassWord");
				  String pWord = s.nextLine();
				  System.out.println("Enter the FirstName:");
				  String fName = s.nextLine();
				  System.out.println("Enter the LastName:");
				  String lName = s.nextLine();
				  System.out.println("Enter the Address:");
				  String adrr = s.nextLine();
				  System.out.println("Enter the MailId:");
				  String mId = s.nextLine();
				  System.out.println("Enter the MobileNo:");
				  double mNo = Double.parseDouble(s.nextLine());
				  
				  //Setting data to ps1-Object
				  ps1.setString(1, uName);
				  ps1.setString(2, pWord);
				  ps1.setString(3, fName);
				  ps1.setString(4, lName);
				  ps1.setString(5, adrr);
				  ps1.setString(6, mId);
				  ps1.setDouble(7, mNo);
				  int k = ps1.executeUpdate();//Execution
				  if(k>0) {
					  System.out.println
					  ("UserDetails inserted Successfully...");
				  }
				  break;
			  case 2:
				  boolean w=true;
				  String Ret_uName=null;
				  String Ret_pWord=null;
				  System.out.println("Enter The UserName");
				  String uName1=s.nextLine();
				  System.out.println("Enter the Password");
				  String pWord1=s.nextLine();
				  ps2.setString(1, uName1);
				  ps2.setString(2, pWord1);
				  ResultSet rs=ps2.executeQuery();
				  while(rs.next())
				  {
					  Ret_uName=rs.getString(1);
					  Ret_pWord=rs.getString(2);
				  }
				  
				if(uName1.equals(Ret_uName) && pWord1.equals(Ret_pWord)) {
					  System.out.println("Login SucessFull...");
					  
					  while(w) {
						  
						  System.out.println("****Choice****");
						  System.out.println("\t1.ViewProfile"
						  		+ "\n\t2.EditProfile(Add,MailId,PhNo)"
						  		+ "\n\t3.exit");
						  System.out.println("Enter the Choice:");
						  int choice1 = Integer.parseInt(s.nextLine());
						  switch(choice1) {
						  case 1:
							  ps3.setString(1, uName1);
							  ps3.setString(2, pWord1);
							  ResultSet rs1=ps3.executeQuery();
							  while(rs1.next())
							  {
								  System.out.println(rs1.getString(1)+"\t"
		    							  +rs1.getString(2)+"\t"
										  +rs1.getString(3)+"\t"
		    							  +rs1.getString(4)+"\t"
		    							  +rs1.getString(5)+"\t"
				    					  +rs1.getString(6)+"\t"
				    					  +rs1.getDouble(7)); 
							  }
							 
							  break;
						  case 2:
							  ps3.setString(1, uName1);
							  ps3.setString(2, pWord1);
							  ResultSet rs2=ps3.executeQuery();
							  if(rs2.next())
							  {
								  System.out.println("Your Old Address Is:"+rs2.getString(5));
								  System.out.println("Enter New Address:");
								  String nAddr=s.nextLine();
								  System.out.println("Your Old MailId Is:"+rs2.getString(6));
								  System.out.println("Enter New MailId:");
								  String nMailId=s.nextLine();
								  System.out.println("Your Old PhoneNumber Is:"+rs2.getDouble(7));
								  System.out.println("Enter New PhoneNumbe:");
								  Double nMNo=Double.parseDouble(s.nextLine());
								  ps4.setString(1, nAddr);
								  ps4.setString(2, nMailId);
								  ps4.setDouble(3, nMNo);
								  ps4.setString(4, uName1);
								  ps4.setString(5, pWord1);
								  ResultSet rs3=ps4.executeQuery();
								  
							  }
							  break;
						  case 3:
							  w=false;
							  System.out.println("*****Thank You*****");
							  break;
						     }//End of Internal Switch
						  
						  }//End Of Internal While
				  }
				else{
					System.out.println("OPPS!!!!!...Incorrect LoginId/Password");
				}
				  break;
			  case 3:
				  System.out.println("DB Operation Stopped");
				  System.exit(0);
			  }
			  }//end of while
	}//End of Try
	catch(Exception e) {
		e.printStackTrace();
	}
}
}
