<%-- 
    Document   : showcandidate
    Created on : 29 May, 2021, 2:35:37 PM
    Author     : HP
--%>

<%@page import="evoting.dto.UserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,evoting.dto.CandidateInfo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="jsscript/vote.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheets/backgroundimage.css" rel="stylesheet">
        <link href="stylesheets/pageheader.css" rel="stylesheet">
        <link href="stylesheets/showcandidate.css" rel="stylesheet">
        <link href="stylesheets/myresult.css" rel="stylesheet">
        <title>show user details</title>
    </head>
<% 
 String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
             
              ArrayList<UserDetails> userdetails=(ArrayList<UserDetails>)request.getAttribute("userdetailslist");
              StringBuffer displayBlock=new StringBuffer("<table id='table'>");
              displayBlock.append("<tr><th>UserId</th><th>UserName</th><th>Address</th><th>City</th><th>Email</th><th>Mobile</th></tr>");
              for(UserDetails ud : userdetails)
              {
                  displayBlock.append("<tr><td>"+ud.getUserid()+"</td><td>"+ud.getUsername()+"</td><td>"+ud.getAddress()+"</td><td>"+ud.getCity()+"</td><td>"+ud.getEmail()+"</td><td>"+ud.getMobile()+"</td></tr>");
              }
             displayBlock.append("</table>");
             out.println(displayBlock);
%>