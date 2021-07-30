package evoting.controller;

import evoting.dao.VoteDAO;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddVoteControllerServlet extends HttpServlet {

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

        try {
            String candidateId = request.getParameter("candidateid");
            VoteDTO vote = new VoteDTO(candidateId, userid);
            boolean result = VoteDAO.addVote(vote);
           CandidateInfo candidate = VoteDAO.getVote(candidateId);
            if(result==true)
                  sess.setAttribute("candidate", candidate);
            
            request.setAttribute("result", result);
            rd = request.getRequestDispatcher("verifyvote.jsp");
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            rd = request.getRequestDispatcher("showException.jsp");
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
