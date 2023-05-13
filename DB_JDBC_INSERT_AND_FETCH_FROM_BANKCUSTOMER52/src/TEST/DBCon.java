package TEST;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class DBCon {
	public static void main(String[] args) {
      Scanner  s = new Scanner(System.in);
      try(s;){
    	  try {
    		  Connection con = DriverManager.getConnection
    ("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
    				           //Creating Connection
    		  PreparedStatement ps1 = con.prepareStatement
    		   ("insert into BANKCUSTOMER52 values(?,?,?,?)");
    		        //Compilation
    		  PreparedStatement ps2 = con.prepareStatement
    				  ("select * from BANKCUSTOMER52");
    		        //Compilation
    		  while(true) {
    			  System.out.println("****Choice****");
    			  System.out.println("\t1.AddAccountDetails"
    			  		+ "\n\t2.ViewAllAccountDetails"
    			  		+ "\n\t3.Exit");
    			  System.out.println("Enter the Choice:");
    			  int choice = Integer.parseInt(s.nextLine());
    			  switch(choice) {
    			  case 1:
    				  System.out.println("Enter the Account Number:");
    				  double aN = Double.parseDouble(s.nextLine());
    				  System.out.println("Enter the Customer Name:");
    				  String cN =s.nextLine();
    				  System.out.println("Enter the Balance:");
    				  float bal = Float.parseFloat(s.nextLine());
    				  System.out.println("Enter the Account Type:");
    				  String accT = s.nextLine();
    				  
    				  
    				  //Setting data to ps1-Object
    				  ps1.setDouble(1, aN);
    				  ps1.setString(2, cN);
    				  ps1.setFloat(3, bal);
    				  ps1.setString(4, accT);
    				 
    				  
    				  int k = ps1.executeUpdate();//Execution
    				  if(k>0) {
    					  System.out.println
    					  ("CustomerDetails inserted Successfully...");
    				  }
    				  break;
    			  case 2:
    				  ResultSet rs = ps2.executeQuery();
    				                      //Execution
    				  System.out.println("****Account-Details****");
    				  while(rs.next()) {
    					  System.out.println(rs.getDouble(1)+"\t"
    							  +rs.getString(2)+"\t"+
    							  rs.getFloat(3)+"\t"+
    							  rs.getString(4));
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
