
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject" %>
<%@page import="evoting.dto.CandidateDetails" %>
<%@page import="java.util.ArrayList" %>
<% 
    System.out.println("In adminupdate.jsp");
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
                System.out.println("In adminupdate :");
                System.out.println(displayblock);
                
            }
              else if(result!=null && result.equalsIgnoreCase("details"))
            {
                CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
                ArrayList<String>city=(ArrayList<String>)request.getAttribute("city");
                String str="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px;height:200px'/>";
                
              JSONObject json=new JSONObject();
                StringBuffer sb=new StringBuffer();
                for(String c:city)
                {
                    sb.append("<option value='"+c+"'>"+c+"</option>");
                }
                System.out.println(sb);
                
               json.put("city",sb.toString());
               json.put("userid",cd.getUserId());
               json.put("cname",cd.getCandidateName());
               json.put("party",cd.getParty());
               json.put("symbol",str);
               out.println(json);
            }
            
%>