
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dao.VoteDAO;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ElectionResultControllerServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        RequestDispatcher rd = null;
        HttpSession sess = request.getSession();
        String userid = (String) sess.getAttribute("userid");
        if (userid == null) {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        
        String data=request.getParameter("data");
        try
        {
            if(data.equalsIgnoreCase("bycandidate"))
            {
            Map<String,Integer> result=VoteDAO.getResult();
            Set s=result.entrySet();
            Iterator it=s.iterator();
            LinkedHashMap<CandidateDetails,Integer> resultDetails=new LinkedHashMap<>();
            while(it.hasNext())
            {
                Map.Entry<String,Integer> e=(Map.Entry)it.next();
                CandidateDetails cd=CandidateDAO.getDetailsById(e.getKey());
                resultDetails.put(cd,e.getValue());
            }
              System.out.println("resultdetails"+resultDetails);
            request.setAttribute("votecount",VoteDAO.getVoteCount());
            request.setAttribute("result",resultDetails);
            rd=request.getRequestDispatcher("electionresult.jsp");
            }
            
            else if(data.equalsIgnoreCase("byparty"))
             {
                 System.out.println("In eleccontrserv else if");
                    Map<String,Integer> result=VoteDAO.getPartyResult();
            Set s=result.entrySet();
            Iterator it=s.iterator();
            LinkedHashMap<CandidateDetails,Integer> resultDetails=new LinkedHashMap<>();
            while(it.hasNext())
            {
                Map.Entry<String,Integer> e=(Map.Entry)it.next();
                CandidateDetails cd=CandidateDAO.getCandidateDetailsByParty(e.getKey());
               
                resultDetails.put(cd,e.getValue());
            }
                 System.out.println("resultdetails"+resultDetails);
            request.setAttribute("votecount",VoteDAO.getVoteCount());
            request.setAttribute("result",resultDetails);
            rd=request.getRequestDispatcher("resultbyparty.jsp");
                 
                 
             }
            
            else 
            {
             System.out.println("In eleccontrserv else ");   
             Map<String,Integer> result=VoteDAO.getGenderVotingPercent();
             Set s=result.entrySet();
             Iterator it=s.iterator();
               LinkedHashMap<String,Integer> resultDetails=new LinkedHashMap<>();
              while(it.hasNext())
            {
                Map.Entry<String,Integer> e=(Map.Entry)it.next();
               
               resultDetails.put(e.getKey(),e.getValue());
            }
             
               System.out.println("resultdetails"+resultDetails);
            request.setAttribute("votecount",VoteDAO.getVoteCount());
            request.setAttribute("result",resultDetails);
            rd=request.getRequestDispatcher("resultvotingbygender.jsp");
             
            }  
            
            
        }
        
        catch(Exception ex)
        {
            ex.printStackTrace();
            rd=request.getRequestDispatcher("showException.jsp");
            request.setAttribute("Exception", ex);
        }
        finally
        {
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
