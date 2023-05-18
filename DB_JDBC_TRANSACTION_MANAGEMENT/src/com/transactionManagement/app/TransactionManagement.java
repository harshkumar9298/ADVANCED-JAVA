package com.transactionManagement.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Savepoint;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class TransactionManagement {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try(s;){
			try {
			Connection con = DriverManager.getConnection
			("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
			System.out.println("Status of AutoCommit :"+con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("Status of AutoCommit : "+con.getAutoCommit());
	
			PreparedStatement ps1 =	
					con.prepareStatement("select * from Bank52 where accNo=?");
			PreparedStatement ps2 =
					con.prepareStatement("update Bank52 set bal=bal+? where accNo=?");
			PreparedStatement ps3 =
					con.prepareStatement("insert into TransLogTab52 values(?,?,?,?)");
			Savepoint sp = con.setSavepoint();
			System.out.println("Enter the Home AccNo:");
			long hAccNo = s.nextLong();
			ps1.setLong(1, hAccNo);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				float bl = rs1.getFloat(3);
				System.out.println("Enter beneficiery AccNo:");
				long bAccNo = s.nextLong();
				ps1.setLong(1, bAccNo);
				ResultSet rs2 = ps1.executeQuery();
			
					if(rs2.next()) {
							System.out.println("Enter the amt to be Transferred: ");
							float amt = s.nextFloat();
							if(amt<=bl) {
								ps2.setFloat(1,-amt);
								ps2.setLong(2, hAccNo);
								int i = ps2.executeUpdate();//Updated in buffer
								ps2.setFloat(1, amt);
								ps2.setLong(2, bAccNo);
								int j = ps2.executeUpdate();//Updated in buffer
								
								if(i==1 && j==1) {
									
									Date date = new Date();
									String ldt = String.format("%tc", date );
									        //Generating System date
									 try {    
									ps3.setLong(1, hAccNo);
									/*when we transfer 2nd time from hAccNo to bAccNo
									 then it generate SQLIntegrityConstraintViolationException
									 because in TransLogTab52 hAccNo is Primary Key Thats Why we can't enter 
									 hAccNo more than Once*/
									ps3.setLong(2, bAccNo);
									ps3.setFloat(3, amt);
									ps3.setString(4, ldt);
									int k=ps3.executeUpdate();
										if(k>0)
										{
											System.out.println
											("TransactionDetails Updated in TransLogTab52 Table Sucessfully ");
										}
									System.out.println("Transaction Successfull...");
									con.commit();//Update the database
									 }
									 catch(SQLIntegrityConstraintViolationException SICVE) {
										 System.out.println
										 ("Home Account Number Aready Exist(Primary Key) so we cant enter two time");
									 }
									 
								}else {
									System.out.println("Transaction Failed...");
									con.rollback(sp);
								}
							}else {
								System.out.println("Insufficient fund...");
							}
					}else {
						System.out.println("Invalid bAccNo...");
					}
				}else {
						System.out.println("Invalid homeAccNo...");
				}
			}catch(Exception e) {e.printStackTrace();}
			}//end of try with resource
		}

	private static String String(LocalDateTime mldt) {
		// TODO Auto-generated method stub
		return null;
	}

}
