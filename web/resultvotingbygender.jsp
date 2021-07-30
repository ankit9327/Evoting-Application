

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="evoting.dto.CandidateDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <% 
  String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            
   LinkedHashMap<String,Integer> resultDetails=(LinkedHashMap)request.getAttribute("result");
   int votecount=(int)request.getAttribute("votecount");
   Iterator it=resultDetails.entrySet().iterator();
   
StringBuffer displayBlock=new StringBuffer("<table>");
displayBlock.append("<tr><th>Gender</th><th>Votecount</th><th>Vote %</th></tr>");
while(it.hasNext())
{
    Map.Entry<String,Integer>e=(Map.Entry)it.next();
   
    float voteper=(e.getValue()*100.0f)/votecount;
    displayBlock.append("<tr><td>"+e.getKey()+"</td><td>"+e.getValue()+"</td><td>"+voteper+"</td></tr>");
}
displayBlock.append("</table>");
out.println(displayBlock);

 %>