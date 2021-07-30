<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/adminoptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheets/backgroundimage.css" rel="stylesheet">
        <link href="stylesheets/pageheader.css" rel="stylesheet">
        <link href="stylesheets/admin.css" rel="stylesheet">
        <link href="stylesheets/result.css" rel="stylesheet">
        <link href="stylesheets/myresult.css" rel="stylesheet">
        <title>Show result Page</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
        "<div class='subcandidate'>Election Result Page</div><br><br>"+
                    "<div class='logout'><a href='index.html'>logout</a></div></div>"+
        "<div class='container'>"+
            "<div id='dv1' onclick='electionResultCandidate()'><img src='images/img1.jpg' height='255px' width='250px'><br><h3>Show By Candidate</h3></div>"+
            "<div id='dv2' onclick='resultByParty()'><img src='images/politicalparties.jpg' height='250px' width='250px'><br><h3>Show By Party</h3></div>"+
           "<div id='dv2' onclick='resultvotingbygender()'><img src='images/gettyimages.jpg' height='250px' width='250px'><br><h3>Voting By Gender</h3></div>"+
            "<br><br><div align='center' id='result'></div>"+
        "</div>");
        %>
    </body>
</html>
