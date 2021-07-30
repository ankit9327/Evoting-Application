

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject" %>
<%@page import="evoting.dto.CandidateDetails" %>
<%@page import="java.util.ArrayList" %>
<% 
    System.out.println("In adminshowcand.jsp");
String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            StringBuffer displayblock=new StringBuffer();
            String result=(String)request.getAttribute("result");
            if(result!=null && result.equalsIgnoreCase("candidatelist"))
            {
                ArrayList<String>candidateId=(ArrayList<String>)request.getAttribute("candidateid");
                displayblock.append("<option value=' '>Choose Id</option>");
                for(String c:candidateId)
                {
                displayblock.append("<option value='"+c+"'>"+c+"</option>");
                
                }
                JSONObject json=new JSONObject();
                json.put("cid",displayblock.toString());
                out.println(json);
                System.out.println("In adminshowcand :");
                System.out.println(displayblock);
                
            }
            else if(result!=null && result.equalsIgnoreCase("details"))
            {
                CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
                String str="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px;height:200px'/>";
                
                displayblock.append("<table>");
                displayblock.append("<tr><th>UserId : </th><td>"+cd.getUserId()+"</td></tr>");
                displayblock.append("<tr><th>Candidate Name : </th><td>"+cd.getCandidateName()+"</td></tr>");
                displayblock.append("<tr><th>City : </th><td>"+cd.getCity()+"</td></tr>");
                displayblock.append("<tr><th>Party : </th><td>"+cd.getParty()+"</td></tr>");
                displayblock.append("<tr><th>Symbol : </th><td>"+str+"</td></tr>");
                displayblock.append("</table>");
                
                 JSONObject json=new JSONObject();
                 json.put("subdetails",displayblock.toString());
                 out.println(json);
            }
           
%>
