<%-- 
    Document   : registrationResponse
    Created on : 1 May, 2021, 6:09:07 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
boolean result=(boolean)request.getAttribute("result");
boolean userfound=(boolean)request.getAttribute("userfound");
if(userfound==true)
out.println("uap");
else if(result==true)
out.println("success");
else
out.println("error");
%>