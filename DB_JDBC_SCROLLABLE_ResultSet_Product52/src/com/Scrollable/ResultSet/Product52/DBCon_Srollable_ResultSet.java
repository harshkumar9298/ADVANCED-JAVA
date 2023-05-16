package com.Scrollable.ResultSet.Product52;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DBCon_Srollable_ResultSet {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		boolean loop1=true;
		boolean loop2=true;
		boolean loop3=true;
		try(s;) {
		Connection con = DriverManager.getConnection

		("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
		//Creating Connection
		while(loop1) {
			System.out.println("*****Scrollable Resulset Program*****");
			System.out.println("\n1.Using Statement \n2.Using PreparedStatement \n3.Exit");
			int choice=Integer.parseInt(s.nextLine());
			switch(choice) {
			case 1:
				while(loop2) {
					Statement stm = con.createStatement
							(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					ResultSet rs = stm.executeQuery("select * from Product52");
					System.out.println
					("\n1.DisplayNormal \n2.DisplayReverse \n3.DisplayLastRow \n4.DisplayFirstRow \n5.DisplaySpecificRow \n6.Exit");
					int choice1=Integer.parseInt(s.nextLine());
					switch(choice1) {
					case 1:
						System.out.println("****Table data in Normal Form*****");
						rs.beforeFirst();
						while(rs.next()) {
								System.out.println(rs.getString(1)+"\t"
								+rs.getString(2)+"\t"
								+rs.getInt(3)+"\t"
								+rs.getInt(4));
						}//end of loop
						
						break;
					case 2:
						System.out.println("****Table data in reverse*****");
						rs.afterLast();//Cursor pointing after last row
						while(rs.previous()) {
							System.out.println(rs.getString(1)+"\t"
									+rs.getString(2)+"\t"
									+rs.getInt(3)+"\t"
									+rs.getInt(4));
						}//end of loop
						break;
					case 3:
						System.out.println("*****Last Row*****");
						rs.last();//Cursor Pointing to Last Row
						System.out.println(rs.getString(1)+"\t"
								+rs.getString(2)+"\t"
								+rs.getInt(3)+"\t"
								+rs.getInt(4));
						
						break;
					case 4:
						System.out.println("****First Row****");
						rs.first();//cursor pointing to First Row
						System.out.println(rs.getString(1)+"\t"
								+rs.getString(2)+"\t"
								+rs.getInt(3)+"\t"
								+rs.getInt(4));
						break;
					case 5:
						System.out.println
						       ("Display Specific Row (Enter The Row You Want to Print)");
						System.out.println("Enter The Row You Want To Display : ");
						int row=Integer.parseInt(s.nextLine());
						if(rs.absolute(row)) //cursor pointing to specific row
						{
						System.out.println(rs.getString(1)+"\t"
								+rs.getString(2)+"\t"
								+rs.getInt(3)+"\t"
								+rs.getInt(4));
						}
						else {
							System.out.println("Row "+row+" Is Not Available");
						}
						break;
					case 6:
						loop2=false;
						System.out.println("Exited From Statement");
						break;
					}//end of inner switch
					
					}//End of case 1 while loop
				break;
			case 2:
				while(loop3) {
					PreparedStatement ps = con.prepareStatement
							("select * from Product52",1004,1007);
					ResultSet rs2 = ps.executeQuery();
					System.out.println
					("\n1.DisplayNormal \n2.DisplayReverse \n3.DisplayLastRow \n4.DisplayFirstRow \n5.DisplaySpecificRow \n6.Exit");
					int choice2=Integer.parseInt(s.nextLine());
					switch(choice2) {
					case 1:
						System.out.println("****Table data in Normal Form*****");
						rs2.beforeFirst();
						while(rs2.next()) {
								System.out.println(rs2.getString(1)+"\t"
								+rs2.getString(2)+"\t"
								+rs2.getInt(3)+"\t"
								+rs2.getInt(4));
						}//end of loop
						break;
					case 2:
						System.out.println("****Table data in reverse*****");
						rs2.afterLast();//Cursor pointing after last row
						while(rs2.previous()) {
							System.out.println(rs2.getString(1)+"\t"
									+rs2.getString(2)+"\t"
									+rs2.getInt(3)+"\t"
									+rs2.getInt(4));
						}//end of loop
						break;
					case 3:
						System.out.println("*****Last Row*****");
						rs2.last();//Cursor Pointing to Last Row
						System.out.println(rs2.getString(1)+"\t"
								+rs2.getString(2)+"\t"
								+rs2.getInt(3)+"\t"
								+rs2.getInt(4));
						
						break;
					case 4:
						System.out.println("****First Row****");
						rs2.first();//cursor pointing to First Row
						System.out.println(rs2.getString(1)+"\t"
								+rs2.getString(2)+"\t"
								+rs2.getInt(3)+"\t"
								+rs2.getInt(4));
						break;
					case 5:
						System.out.println
						("Display Specific Row (Enter The Row You Want to Print)");
						System.out.println("Enter The Row You Want To Display : ");
						int Row=Integer.parseInt(s.nextLine());
						if(rs2.absolute(Row)) //cursor pointing to specific row
						{
						System.out.println(rs2.getString(1)+"\t"
								+rs2.getString(2)+"\t"
								+rs2.getInt(3)+"\t"
								+rs2.getInt(4));
						}
						else {
							System.out.println("Row "+Row+" Is Not Available");
						}
						break;
					case 6:
						loop3=false;
						System.out.println("Exited From PreparedStatement");
						break;
					}//end of inner switch
					
					}//End of case 2 while loop
				break;
			case 3:
				loop1=false;
				System.out.println("Exited...");
				break;
			}//end of switch
		}//End of While
		System.out.println("Thank You For Choosing Us");
		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

}
