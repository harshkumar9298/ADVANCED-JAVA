package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class DBCon3 {
	public static void main(String[] args) {
      Scanner  s = new Scanner(System.in);
      try(s;){
    	  try {
    		  Connection con = DriverManager.getConnection
    ("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
    				           //Creating Connection
    		  PreparedStatement ps1 = con.prepareStatement
    		   ("insert into BookDetails52 values(?,?,?,?,?)");
    		        //Compilation
    		  PreparedStatement ps2 = con.prepareStatement
    				  ("select * from BookDetails52");
    		        //Compilation
    		  while(true) {
    			  System.out.println("****Choice****");
    			  System.out.println("\t1.AddBookDetails"
    			  		+ "\n\t2.ViewAllBookDetails"
    			  		+ "\n\t3.Exit");
    			  System.out.println("Enter the Choice:");
    			  int choice = Integer.parseInt(s.nextLine());
    			  switch(choice) {
    			  case 1:
    				  System.out.println("Enter the BookCode:");
    				  String bC = s.nextLine();
    				  System.out.println("Enter the BookName:");
    				  String bN = s.nextLine();
    				  System.out.println("Enter the BookAuthor:");
    				  String bA = s.nextLine();
    				  System.out.println("Enter the BookPrice:");
    				  float bP = Float.parseFloat(s.nextLine());
    				  System.out.println("Enter the BookQty:");
    				  int bQ = Integer.parseInt(s.nextLine());
    				  
    				  //Setting data to ps1-Object
    				  ps1.setString(1, bC);
    				  ps1.setString(2, bN);
    				  ps1.setString(3, bA);
    				  ps1.setFloat(4, bP);
    				  ps1.setInt(5, bQ);
    				  
    				  int k = ps1.executeUpdate();//Execution
    				  if(k>0) {
    					  System.out.println
    					  ("BookDetails inserted Successfully...");
    				  }
    				  break;
    			  case 2:
    				  ResultSet rs = ps2.executeQuery();
    				                      //Execution
    				  System.out.println("****Book-Details****");
    				  while(rs.next()) {
    					  System.out.println(rs.getString(1)+"\t"
    							  +rs.getString(2)+"\t"+
    							  rs.getString(3)+"\t"+
    							  rs.getFloat(4)+"\t"
    							  +rs.getInt(5));
    				  }//end of loop
    				  break;
    			  case 3:
    				  System.out.println("Operation on DB Stopped...");
    				  System.exit(0);
    			  default:
    				  System.out.println("Invalid Choice..");
    			  }//end of switch
    		  }//end of loop
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
      }//end of try with resource
	}
}
