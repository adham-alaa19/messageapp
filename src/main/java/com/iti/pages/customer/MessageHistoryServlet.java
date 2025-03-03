package com.iti.pages.customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */




/*/*

                  |->doget display   Gr       
DisplayServelt----
                  |->dopost delete       
                
                  |->doget display Search result   //y3ml search     
SearchServelt------
                  |->dopost get Search Result (new Messages based on search) //



*/
@WebServlet(name = "MessageHistoryServlet", urlPatterns = {"/MessageHistoryServlet"})
public class MessageHistoryServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // delete function.  
        // list all messages.
        // list messages by mobile number i have sent to & by date. 
        processRequest(request, response);
    }
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MessageHistoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MessageHistoryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

 

  

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
