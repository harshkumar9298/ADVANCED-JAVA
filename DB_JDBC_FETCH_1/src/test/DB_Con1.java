package test;
import java.sql.*;
public class DB_Con1 {
	public static void main(String[] args) {
		try 
		{
       Class.forName("oracle.jdbc.driver.OracleDriver");
               //Loading driver
       
       Connection con = DriverManager.getConnection
       ("jdbc:oracle:thin:@localhost:1521:ORCL","system","harsh");
              //Creating Connection
       
       Statement stm = con.createStatement();
              //Preparing statement
       
       ResultSet rs = stm.executeQuery("select * from Emp52");
                //Executing query
       
       while(rs.next())
       {
    	   System.out.println(rs.getString(1)+"\t"+
                rs.getString(2)+"\t"+rs.getString(3)+"\t"+
    			   rs.getInt(4));
       }//end of loop
       
       con.close();
          //closing connection
		}//end of try
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
