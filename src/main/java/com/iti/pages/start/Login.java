package com.iti.pages.start;
import com.iti.managers.SessionManager;
import com.iti.managers.UserManager;
import com.iti.models.IUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author theda
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 try (PrintWriter out = response.getWriter()) {
      if(SessionManager.isLoggedIn(request))   response.sendRedirect("homesession");
      request.getRequestDispatcher("start_pages/login.html").include(request, response);
      String s= request.getParameter("NotFound");
      String s2= request.getParameter("Logged");
      if("False".equalsIgnoreCase(s2))
                out.println("<h3 style=\"color=red;\"> Please Log In First</h3>");
      else if("True".equalsIgnoreCase(s))
                out.println("<h3 style=\"color=red;\"> User Not Exist</h3>");
            request.getRequestDispatcher("includes/footer.html").include(request, response);

         } catch(Exception e) {
             e.printStackTrace();
         }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String mail = request.getParameter("email");
      String password = request.getParameter("password");
      UserManager usrmanager = new UserManager();
      IUser user= usrmanager.getUser(mail, password);
      System.out.println(user);
      if(user!=null) 
      {
       
       HttpSession mySession = request.getSession(true);
       mySession.setAttribute("user", user);
       if(usrmanager.isAdmin(user))
           response.sendRedirect("adminhome");
       else
            response.sendRedirect("home");
      }
      else  response.sendRedirect("login?NotFound=True");
    }
    

}
