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
     <!-- <link href="stylesheets/result.css" rel="stylesheet">-->
        <!-- <link href="stylesheets/myresult.css" rel="stylesheet">-->
        <title>Admin Actions Page</title>
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
        "<div class='subcandidate'>Admin Actions Page</div><br><br>"+
                    "<div class='logout'><a href='index.html'>logout</a></div></div>"+
        "<div class='container'>"+
                     "<div id='dv3' onclick='showUserDetails()'><img src='images/show.png' height='250px' width='250px'><br><h3>Show User</h3></div>"+

            "<div id='dv1' onclick='showUpdateUserForm()'><img src='images/updateimg2.jpg' height='255px' width='250px'><br><h3>Update User</h3></div>"+
            "<div id='dv2' onclick='showDeleteUserForm()'><img src='images/delete.jpg' height='250px' width='250px'><br><h3>Delete User</h3></div>"+
                       "<br><br><div align='center' id='result'></div>"+
        "</div>");
        %>
    </body>
</html>
