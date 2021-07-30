
package evoting.controller;

import evoting.dao.userDAO;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateUserServ extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        RequestDispatcher rd=null;
           HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
       
         String userId=request.getParameter("userid");
          String userName=request.getParameter("username");
           String city=request.getParameter("city");
            String address=request.getParameter("addr");
             String email=request.getParameter("email");
             long mobile=Long.parseLong(request.getParameter("mobile"));
           
           
        System.out.println("userid :"+userId);
        UserDetails ud=new UserDetails(userName,userId,email,address,city,mobile);
          try
        {
            System.out.println("try of updateuserserv");
          
            boolean result=userDAO.updateUser(ud);
            System.out.println("result :"+result);
               if(result)
            rd=request.getRequestDispatcher("success.jsp");
        else
            rd=request.getRequestDispatcher("failure.jsp");
       
        }
        
           catch(Exception ex)
        {
            System.out.println("Excepton in UpdateUserServ");
            ex.printStackTrace();
               rd=request.getRequestDispatcher("showException.jsp");
                request.setAttribute("Exception",ex);
                rd.forward(request, response);
        }
        finally
        {
            if(rd!=null)
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
