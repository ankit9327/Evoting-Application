/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class DBConnection {
      private static Connection conn=null;
    static
    {
        System.out.println("static block of db  conn");
        try{
    Class.forName("oracle.jdbc.OracleDriver");
    conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-4FQK1SA:1521/xe","evoting","evoting");
            System.out.println("Driver loaded and connection opened");
        }
        catch(ClassNotFoundException cnf)
        {
            cnf.printStackTrace();
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
    }
    
    public static Connection getConnection(){
        System.out.println("returrn conn");
        return conn;
        
    }
    
  
}
