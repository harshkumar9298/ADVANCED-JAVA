package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
public class DB_Con_Insert {
	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try(s;){
        	try
        	{
        	System.out.println("Enter the empId:");
        	String id = s.nextLine();
        	System.out.println("Enter the empName:");
        	String name = s.nextLine();
        	System.out.println("Enter the empDesg:");
        	String desg = s.nextLine();
        	System.out.println("Enter the empSal:");
        	int sal = s.nextInt();
        	
            Connection con = DriverManager.getConnection
    ("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
           //Creating Connection
    
           Statement stm = con.createStatement();
           //Preparing statement
           
           int k = stm.executeUpdate
             ("insert into Emp52 values('"+id+"','"+name+"','"+desg+"',"+sal+")");
              if(k>0)
              {
        	   System.out.println("Record inserted Successfully..");
              }
             con.close(); 
        	}//end of try
        	catch(InputMismatchException ime)
        	{
        		System.out.println("Invalid input...");
        	}
        	catch(SQLIntegrityConstraintViolationException sicve)
        	{
        		System.out.println("Employee details already available...");
        	}
        	catch(SQLException cnfe)
        	{
        		cnfe.printStackTrace();
        	}
        }//end of try with resource
	}
}
