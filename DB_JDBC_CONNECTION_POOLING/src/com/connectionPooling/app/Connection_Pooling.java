package com.connectionPooling.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class Connection_Pooling {
		 public String dbUrl,uName,pWord;
	
		 public Connection_Pooling(String dbUrl, String uName, String pWord) {

			 this.dbUrl=dbUrl;
			 this.uName=uName;
			 this.pWord=pWord;
		}
		//Instance reference variable(Tightly Coupled reference)
		public Vector<Connection> v = new Vector<Connection>();
		 
		//Instance method
		 public void createConnections() {
			 try {
				 while(v.size()<5) {
					 System.out.println("Pool is not full....");
					 Connection con =
							 DriverManager.getConnection(dbUrl,uName,pWord);
					 v.add(con);
					 System.out.println(con);
					 }//end of loop
					 if(v.size()==5) {
					 System.out.println("Pool is full...."); 
					 }
			 }//end of method
			 catch(Exception e) 
		 		{
				 e.printStackTrace();
		 		} 
		 	}//end of method
		 	
		 public synchronized Connection useConnection() {
			 Connection con = v.elementAt(0);
			 //Taking the element from index 0
			 v.removeElementAt(0);//Element deleted from Vector
			 return con;
			 }//end of method
		 
		public synchronized void returnConnection(Connection con) {
			 v.addElement(con);//Adding the Connection back to Vector
			 System.out.println("Connection added back to pool...");
			 }//end of method
		}