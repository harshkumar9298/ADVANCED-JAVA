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
        	System.out.println("Enter the Product code:");
        	String pc = s.nextLine();
        	System.out.println("Enter the Product Name:");
        	String pname = s.nextLine();
        	System.out.println("Enter the Product Price:");
        	String pp = s.nextLine();
        	System.out.println("Enter the Product Quantity:");
        	int qty = s.nextInt();
        	
            Connection con = DriverManager.getConnection
    ("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
           //Creating Connection
    
           Statement stm = con.createStatement();
           //Preparing statement
           
           int k = stm.executeUpdate
             ("insert into product52 values('"+pc+"','"+pname+"','"+pp+"',"+qty+")");
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
        		System.out.println("Product details already available...");
        	}
        	catch(SQLException cnfe)
        	{
        		cnfe.printStackTrace();
        	}
        }//end of try with resource
	}
}
