/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateInfo;
import evoting.dto.CandidateUpdateDTO;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

public class CandidateDAO {
     private static PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9;
     private static Statement st,st1;
   static{
    try
    {
        st=DBConnection.getConnection().createStatement();
        ps=DBConnection.getConnection().prepareStatement("select max(candidate_id)from candidate");
        ps1=DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=?");
        ps2=DBConnection.getConnection().prepareStatement("select distinct city from user_details");
        ps3=DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)");
        ps4=DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id=? ");
        ps5=DBConnection.getConnection().prepareStatement("update candidate set party=?,city=?,symbol=? where candidate_id=?");
        ps6=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from candidate,user_details where candidate.user_id=user_details.adhar_no and candidate.city=(select city from user_details where adhar_no=?)");
        ps7=DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
        st1=DBConnection.getConnection().createStatement();
        ps8=DBConnection.getConnection().prepareStatement("select candidate_id from candidate where city=? and party=?");
        ps9=DBConnection.getConnection().prepareStatement("select * from candidate where party=?");
        System.out.println("try of userdao");
    }
    catch(SQLException se){
        se.printStackTrace();
    }
   }
   
   public static String getNewId()throws SQLException
   {
       ResultSet rs=ps.executeQuery();
      rs.next();
      String cid=rs.getString(1);
      if(cid==null)
          return "C101";
      else
      {
          int id=Integer.parseInt(cid.substring(1));
          return "C"+(id+1);
      }
       
   }
   
   public static String getUserNameById(String uid)throws SQLException
   {
       ps1.setString(1,uid);
       ResultSet rs=ps1.executeQuery();
       if(rs.next())
       {
           return rs.getString(1);
       }
       else 
           return null;
   }
   
   public static ArrayList<String> getCity() throws SQLException
   {
       ArrayList<String> citylist=new ArrayList<>();
       ResultSet rs=ps2.executeQuery();
       while(rs.next())
       {
           citylist.add(rs.getString(1));
       }
         return citylist;
   }
   
   public static boolean addCandidate(CandidateDTO obj)throws SQLException
   {
       ps3.setString(1,obj.getCandidateId());
       ps3.setString(2,obj.getParty());
       ps3.setString(3,obj.getUserid());
       System.out.println("before getsymbol");
       ps3.setBinaryStream(4,obj.getSymbol());
       System.out.println("after getsymbol");
       ps3.setString(5,obj.getCity());
      
       return  ps3.executeUpdate()!=0;
   }
  
   public static ArrayList<String> getCandidateId()throws SQLException
   {
        ArrayList<String> candidateIdList=new ArrayList<>();
       ResultSet rs=st.executeQuery("select candidate_id from candidate");
       while(rs.next())
       {
           candidateIdList.add(rs.getString(1));
       }
         return candidateIdList;
   }
         
   public static CandidateDetails getDetailsById(String cid)throws Exception
   {
       ps4.setString(1,cid);
       ResultSet rs=ps4.executeQuery();
       CandidateDetails cd=new CandidateDetails();
       Blob blob;
       InputStream inputStream;
       byte[] buffer;
       byte[] imageBytes;
       int bytesRead;
       String base64Image;
       ByteArrayOutputStream outputStream;
       
       if(rs.next())
       {
           blob=rs.getBlob(4);
           inputStream=blob.getBinaryStream();
           outputStream=new ByteArrayOutputStream();
           buffer=new byte[4096];
           bytesRead=-1;
           while((bytesRead=inputStream.read(buffer))!=-1)
           {
               outputStream.write(buffer,0,bytesRead);
           }
           imageBytes=outputStream.toByteArray();
           Base64.Encoder en=Base64.getEncoder();
           base64Image=en.encodeToString(imageBytes);
           
           cd.setSymbol(base64Image);
           cd.setCandidateId(cid);
           cd.setCandidateName(getUserNameById(rs.getString(3)));
           cd.setParty(rs.getString(2));
           cd.setCity(rs.getString(5));
           cd.setUserId(rs.getString(3));
           
       }
       return cd;
   }
   
   public static boolean updateCandidate(CandidateUpdateDTO obj)throws SQLException
   {
       ps5.setString(4, obj.getCandidateId());
       ps5.setString(1,obj.getParty());
       ps5.setString(2,obj.getCity());
       ps5.setBinaryStream(3,obj.getSymbol());
       
      return  ps5.executeUpdate()!=0;
   }
   
   public static ArrayList<CandidateInfo> viewCandidate(String adhar_no)throws Exception
   {
       ArrayList<CandidateInfo>candidateList=new ArrayList<CandidateInfo>();
       ps6.setString(1, adhar_no);
       ResultSet rs=ps6.executeQuery();
       Blob blob;
       InputStream inputStream;
       byte[] buffer;
       byte[] imageBytes;
       int bytesRead;
       String base64Image;
       ByteArrayOutputStream outputStream;
       
       while(rs.next())
       {
           blob=rs.getBlob(4);
           inputStream=blob.getBinaryStream();
           outputStream=new ByteArrayOutputStream();
           buffer=new byte[4096];
           bytesRead=-1;
           while((bytesRead=inputStream.read(buffer))!=-1)
           {
               outputStream.write(buffer,0,bytesRead);
           }
           imageBytes=outputStream.toByteArray();
           Base64.Encoder en=Base64.getEncoder();
           base64Image=en.encodeToString(imageBytes);
           CandidateInfo cd=new CandidateInfo();
            cd.setSymbol(base64Image);
           cd.setCandidateId(rs.getString(1));
           cd.setCandidateName(rs.getString(2));
           cd.setParty(rs.getString(3));
            candidateList.add(cd);
          
       }
       return candidateList;
   }
   
   public static boolean deleteCandidate(String cid)throws SQLException
   {
       ps7.setString(1,cid);
       return ps7.executeUpdate()!=0;
   }
   
   public static ArrayList<String> getUserIdInCandidateForm()throws SQLException
   {
       ResultSet rs=st1.executeQuery("select adhar_no from user_details minus select user_id from candidate");
       ArrayList<String> UserIdList=new ArrayList<>();
       while(rs.next())
       {
           UserIdList.add(rs.getString(1));
       }
       return UserIdList;
   }
   
   public static boolean checkPartyCity(String city,String party)throws SQLException
   {
       ps8.setString(1,city);
       ps8.setString(2,party);
       ResultSet rs=ps8.executeQuery();
       if(rs.next())
       {
           return true;
       }
       return false;
   }
   
   public static CandidateDetails getCandidateDetailsByParty(String party)throws Exception
   {
       ps9.setString(1,party);
        ResultSet rs=ps9.executeQuery();
        CandidateDetails cd=new CandidateDetails();
         Blob blob;
       InputStream inputStream;
       byte[] buffer;
       byte[] imageBytes;
       int bytesRead;
       String base64Image;
       ByteArrayOutputStream outputStream;
       
       if(rs.next())
       {
           blob=rs.getBlob(4);
           inputStream=blob.getBinaryStream();
           outputStream=new ByteArrayOutputStream();
           buffer=new byte[4096];
           bytesRead=-1;
           while((bytesRead=inputStream.read(buffer))!=-1)
           {
               outputStream.write(buffer,0,bytesRead);
           }
           imageBytes=outputStream.toByteArray();
           Base64.Encoder en=Base64.getEncoder();
           base64Image=en.encodeToString(imageBytes);
           
           
             cd.setSymbol(base64Image);
           cd.setCandidateId(rs.getString(1));
           cd.setCandidateName(getUserNameById(rs.getString(3)));
           cd.setParty(party);
           cd.setCity(rs.getString(5));
           cd.setUserId(rs.getString(3));
       }
       
       return cd;
   }
      
}
