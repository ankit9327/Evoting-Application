
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegistrationDAO {
    private static PreparedStatement ps,ps1;
   static{
    try
    {
        System.out.println("static block of regdao");
        ps=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
        ps1=DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?,?)");
    }
    catch(SQLException se){
        se.printStackTrace();
    }
    
}
   
   public static boolean searchUser(String userid)throws SQLException
   {
      
       ps.setString(1, userid);
       return ps.executeQuery().next();
   }
   
   public static boolean registerUser(UserDetails user)throws SQLException
   {
       System.out.println("reg user");
       ps1.setString(1,user.getUserid());
       ps1.setString(2,user.getPassword());
        ps1.setString(3,user.getUsername());
         ps1.setString(4,user.getAddress());
          ps1.setString(5,user.getCity());
           ps1.setString(6,user.getEmail());
            ps1.setLong(7,user.getMobile());
             ps1.setString(8,"Voter");
             ps1.setString(9,user.getGender());
            
             return ps1.executeUpdate()==1;
                 
                         
             
   }
}
