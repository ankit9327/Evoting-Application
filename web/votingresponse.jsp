<%-- 
    Document   : votingresponse
    Created on : 2 Jun, 2021, 3:12:02 PM
    Author     : HP
--%>

<%@page import="evoting.dto.CandidateInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheets/backgroundimage.css" rel="stylesheet">
        <link href="stylesheets/pageheader.css" rel="stylesheet">
        <link href="stylesheets/showcandidate.css" rel="stylesheet">
        <title>voting Details</title>
    </head>
    
    <body>
        <% 
        String userid=(String) session.getAttribute("userid");
        if(userid==null)
       {
         session.invalidate();
         response.sendRedirect("accessDenied.html");
         return;
       }
        CandidateInfo c=(CandidateInfo)session.getAttribute("candidate");
        StringBuffer displayblock=new StringBuffer();
        displayblock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>");
        if(c==null)
        {
            displayblock.append("<div class='subcandidate'>Sorry! Your vote could not be casted</div><br><br>");
            displayblock.append("<div><h4 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h4></div>");
            out.println(displayblock);
        }
        else
        {
             displayblock.append("<div class='subcandidate'>Thank you for voting</div><br><br>");
             displayblock.append("<div class='candidateprofile'><p>CandidateId :"+c.getCandidateId()+"<br>");
             displayblock.append("<strong>You voted for</strong><br><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px;height:200px;'/>");
             displayblock.append("<br><div class='candidateprofile'><p>CandidateId :"+c.getCandidateId()+"<br>");
             displayblock.append("Candidate Name:"+c.getCandidateName()+"<br>");
             displayblock.append("Party :"+c.getParty()+"<br></div>");
             out.println(displayblock);
        }

        %>
        
        
        
    </body>
    
</html>
