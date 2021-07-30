<%-- 
    Document   : showException
    Created on : 1 May, 2021, 6:09:30 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Exception ex=(Exception)request.getAttribute("Exception");
System.out.println("Exception is"+ex);
out.println("some exception occured"+ex.getMessage());
%>
