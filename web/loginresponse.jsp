<%-- 
    Document   : loginresponse
    Created on : 5 May, 2021, 8:45:06 PM
    Author     : HP
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String userid=(String)request.getAttribute("userid");
String userType=(String)request.getAttribute("userType");
if(userid!=null && userType!=null)
{
    HttpSession sess=request.getSession();
    sess.setAttribute("userid",userid);
     String url="";
    if(userType.equalsIgnoreCase("Admin"))
    {
        url="AdminControllerServlet;jsessionid="+sess.getId();
        //url=response.encodeUrl("AdminControllerServlet"); 
    }
    else
    {
        url="VotingControllerServlet;jsessionid="+sess.getId();
     }
    out.println(url);
}
else
{
    out.println("error");
}

%>