package com.connectionPooling.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connection_Pooling_Main {

	public static void main(String[] args) {
		Connection_Pooling cp =
			new Connection_Pooling("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
		 cp.createConnections();//Method call
		
		 System.out.println("*****User-1*****"); 
		 Connection cn1 = cp.useConnection();
		 System.out.println("Con at user-1 : "+cn1);
		
		 System.out.println("Size of pool :"+cp.v.size());
		 try {
		 PreparedStatement ps1 =
		                cn1.prepareStatement("Select * from Product52"); 
		 ResultSet rs1 = ps1.executeQuery();
		 while(rs1.next()) {
		 System.out.println(rs1.getString(1)+"\t"+ rs1.getString(2)+"\t"+rs1.getFloat(3)+ "\t"+rs1.getInt(4));
		 }//end of loop
		 }catch(Exception e) 
		 {
			 e.printStackTrace();
		 } 
		 System.out.println("****User-2****");
		 Connection cn2 = cp.useConnection();
		 System.out.println("Con at user-2 : "+cn2); 
		 System.out.println("Size of pool :"+cp.v.size());
		 try {
			 PreparedStatement ps2 = cn2.prepareStatement ("Select * from Emp52");
			 ResultSet rs2 = ps2.executeQuery();
			 while(rs2.next()) {
					 System.out.println(rs2.getString(1)+ 
					 "\t"+rs2.getString(2)+
					 "\t"+rs2.getString(3)+
					 "\t"+rs2.getInt(4));
			 	}//end of loop
		 }
		 catch(Exception e) 
		 	{
			 	e.printStackTrace();
		 	} 
		 System.out.println("*****User-1****");
		 cp.returnConnection(cn1);
		 System.out.println("Size of pool :"+cp.v.size());
		 System.out.println("*****User-2****");
		 cp.returnConnection(cn2);
		 System.out.println("Size of pool :"+cp.v.size());
		 System.out.println("-----Display Connections----");
		 cp.v.forEach((k)->
		 {
					 System.out.println(k);
		 	});
		}

	

}
