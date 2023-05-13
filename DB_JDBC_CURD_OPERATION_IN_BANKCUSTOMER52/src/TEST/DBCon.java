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
    		  PreparedStatement ps3 = con.prepareStatement
     				 ("select * from BANKCUSTOMER52 where ACCNO=?");
     		  PreparedStatement ps4 = con.prepareStatement
     				  ("update BANKCUSTOMER52 set BALANCE=? where ACCNO=?");
     		  PreparedStatement ps5 = con.prepareStatement
     				  ("delete from BANKCUSTOMER52 where ACCNO=?");
    		  while(true) {
    			  System.out.println("****Choice****");
    			  System.out.println("\t1.AddAccountDetails"
    			  		+ "\n\t2.ViewAllAccountDetails"
    			  		+ "\n\t3.ViewCustomerByAccountNo"
    			  		+ "\n\t4.UpdateSalaryBy(Salary)"
    			  		+ "\n\t5.DeleteCustomerByAccountNo"
    			  		+ "\n\t6.Exit");
    			  System.out.println("Enter the Choice:");
    			  int choice = Integer.parseInt(s.nextLine());
    			  switch(choice) {
    			  case 1:
    				  System.out.println("Enter the Account Number:");
    				  double aN =Double.parseDouble(s.nextLine());
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
    				  System.out.println("Enter the BankAccountNumber:");
    				  double accNo=Double.parseDouble(s.nextLine());
    				  
    				  //setting data to ps3-Object
    				  ps3.setDouble(1, accNo);
    				  
    				  ResultSet rs2 = ps3.executeQuery();
    				  if(rs2.next()) {
    					  System.out.println(rs2.getInt(1)+"\t"
    							  +rs2.getString(2)+"\t"+
    							  rs2.getFloat(3)+"\t"+
    							  rs2.getString(4));
    				  }else {
    					System.out.println("Invalid BankAccount...");  
    				  }
    				  break;
    			  case 4:
    				  System.out.println("Enter the AccounntNo:");
    				  double accNo2 =Double.parseDouble(s.nextLine());
    				  ps3.setDouble(1, accNo2);
    				  ResultSet rs3 = ps3.executeQuery();
    				  if(rs3.next()) {
    					System.out.println("Old Account Balance : "+rs3.getInt(3));
    					System.out.println("Enter new Account Balance : ");
    					float nBal =Float.parseFloat(s.nextLine());				
    					ps4.setFloat(1,nBal);
    					ps4.setDouble(2, accNo2);
    					int k2 = ps4.executeUpdate();
    					if(k2>0) {
    						System.out.println("Account Balance Updated...");
    					}
    				  }else {
    					  System.out.println("Invalid Account Number..");
    				  }
    				  break;
    			  case 5:
    				  System.out.println("Enter the Account Number:");
       				  double accNo3 =Double.parseDouble(s.nextLine());
    				  ps3.setDouble(1, accNo3);
    				  ResultSet rs4 = ps3.executeQuery();
    				  if(rs4.next()) {
    					  ps5.setDouble(1, accNo3);
    					  int k3 = ps5.executeUpdate();
    					  if(k3>0) {
    						  System.out.println("Account Details deleted Successfully..");
    					  }
    				  }else {
    					  System.out.println("Invalid AccountNumber...");
    				  }
    				  break;
    			  case 6:
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
