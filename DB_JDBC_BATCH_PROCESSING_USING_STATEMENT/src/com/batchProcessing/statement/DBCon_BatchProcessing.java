package com.batchProcessing.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class DBCon_BatchProcessing {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try(s){
		
		Connection con = DriverManager.getConnection
		("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
		Statement stm = con.createStatement();

		System.out.println("****Enter BookDetails****");
		System.out.println("Enter the code:");
		String code = s.nextLine();
		System.out.println("Enter the name:");
		String name = s.nextLine();
		System.out.println("Enter the author:");
		String author = s.nextLine();
		System.out.println("Enter the price:");
		float price = Float.parseFloat(s.nextLine());
		System.out.println("Enter the qty:");
		int qty = Integer.parseInt(s.nextLine());

		System.out.println("====Delete employee====");
		System.out.println("Enter the Customer Account No:");
		Double accNo =Double.parseDouble(s.nextLine());

		stm.addBatch
		("insert into BookDetails52 values('"+code+"','"+name+"','"+author+"',"+price+","+qty+")");
		stm.addBatch("delete from bankcustomer52 where ACCNO='"+accNo+"'");

		int k[] = stm.executeBatch();
		for(int i=0;i<k.length;i++) {
		System.out.println("Executed Successfully...");
		}
		stm.clearBatch();
		con.close();
		}catch(Exception e) {e.printStackTrace();}
		}//end of try
		}

