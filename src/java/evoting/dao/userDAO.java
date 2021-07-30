
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import evoting.dto.userDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class userDAO {
     private static PreparedStatement ps,ps1,ps2,ps3;
      private static Statement st,st1;
     
   static{
    try
    {
         st=DBConnection.getConnection().createStatement();
          st1=DBConnection.getConnection().createStatement();
        ps=DBConnection.getConnection().prepareStatement("select user_type from user_details where adhar_no=? and password=?");
        ps1=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
         ps2=DBConnection.getConnection().prepareStatement("update user_details set username=?,address=?,city=?,email=?,mobile_no=? where adhar_no=?");
         ps3=DBConnection.getConnection().prepareStatement("delete from user_details where adhar_no=?");
        System.out.println("try of userdao");
    }
    catch(SQLException se){
        se.printStackTrace();
    }
   }
   
   public static String validateUser(userDTO user)throws SQLException
   {
       ps.setString(1,user.getUserid());
       ps.setString(2,user.getPassword());
      String id=user.getUserid();
      String pwd=user.getPassword();
       System.out.println("id="+id+"pwd="+pwd);
       
       ResultSet rs=ps.executeQuery();
       if(rs.next()){
           System.out.println(rs.getString(1));
           return rs.getString(1);
       }
       else
       {
           System.out.println("null");
       return null;
       }
   }
   
   public static UserDetails getUserDetailsById(String userid)throws SQLException
   {
       ps1.setString(1,userid);
       ResultSet rs=ps1.executeQuery();
       UserDetails user=new UserDetails();
       if(rs.next())
       {
           user.setUserid(rs.getString(1));
           user.setUsername(rs.getString(3));
           user.setCity(rs.getString(5));
           user.setMobile(rs.getLong(7));
           user.setEmail(rs.getString(6));
           user.setAddress(rs.getString(4));
       }
       return user;
   }
   
   public static ArrayList<String> getUserId()throws SQLException
   {
       ResultSet rs=st.executeQuery("select adhar_no from user_details");
       ArrayList<String> userIdList=new ArrayList<>();
       while(rs.next())
       {
           userIdList.add(rs.getString((1)));
       }
       return userIdList;
   }
   
   public static boolean updateUser(UserDetails ud)throws SQLException
   {
       ps2.setString(6,ud.getUserid());
       ps2.setString(1,ud.getUsername());
       ps2.setString(2,ud.getAddress());
       ps2.setString(3,ud.getCity());
       ps2.setString(4,ud.getEmail());
       ps2.setLong(5,ud.getMobile());
       
        return  ps2.executeUpdate()!=0;
   }
   
   public static boolean deleteUser(String userid)throws SQLException
   {
       ps3.setString(1,userid);
       return ps3.executeUpdate()!=0;

   }
   
   public static ArrayList<UserDetails> showUser()throws SQLException
           
   {
       ResultSet rs=st1.executeQuery("select * from user_details");
       ArrayList<UserDetails> userdetailslist=new ArrayList<>();
       while(rs.next())
       {
           UserDetails ud=new UserDetails();
           ud.setUserid(rs.getString(1));
           ud.setUsername(rs.getString(3));
           ud.setAddress(rs.getString(4));
           ud.setCity(rs.getString(5));
           ud.setEmail(rs.getString(6));
           ud.setMobile(rs.getLong(7));
           userdetailslist.add(ud);
       }
       return userdetailslist;
       
   }
}
