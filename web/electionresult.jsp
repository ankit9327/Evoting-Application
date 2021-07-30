

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
            
   LinkedHashMap<CandidateDetails,Integer> resultDetails=(LinkedHashMap)request.getAttribute("result");
   int votecount=(int)request.getAttribute("votecount");
   Iterator it=resultDetails.entrySet().iterator();
   
StringBuffer displayBlock=new StringBuffer("<table>");
displayBlock.append("<tr><th>Candidate Id</th><th>Candidate Name</th><th>Party</th><th>Symbol</th><th>City</th><th>Vote count</th><th>Vote %</th></tr>");
while(it.hasNext())
{
    Map.Entry<CandidateDetails,Integer>e=(Map.Entry)it.next();
    CandidateDetails cd=e.getKey();
    float voteper=(e.getValue()*100.0f)/votecount;
    displayBlock.append("<tr><td>"+cd.getCandidateId()+"</td><td>"+cd.getCandidateName()+"</td><td>"+cd.getParty()+"</td><td><img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:120px;height:120px'/></td><td>"+cd.getCity()+"</td><td>"+e.getValue()+"</td><td>"+voteper+"</td></tr>");
}
displayBlock.append("</table>");
out.println(displayBlock);

 %>