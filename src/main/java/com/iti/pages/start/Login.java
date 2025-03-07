package com.iti.pages.start;
import com.iti.managers.messages.ApiInfoManager;
import com.iti.managers.session.SessionManager;
import com.iti.managers.users.UserManager;
import com.iti.models.IUser;
import com.iti.models.Pub_API_INFO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

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
      request.getRequestDispatcher("pages/start_pages/login.html").include(request, response);
      String s= request.getParameter("NotFound");
      String s2= request.getParameter("Logged");
      if("False".equalsIgnoreCase(s2))
                out.println("<h3 style=\"color=red;\"> Please Log In First</h3>");
      else if("True".equalsIgnoreCase(s))
                out.println("<h3 style=\"color=red;\"> User Not Exist</h3>");
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
      IUser user= usrmanager.getUser(mail);
      if(user!=null) 
      {
       if(usrmanager.checkUserPassword(user, password))
       {
       SessionManager.startSession(request, user);
       if(usrmanager.isAdmin(user))
           response.sendRedirect("app/admin/home");
       else
       {
            ApiInfoManager apiManager = new ApiInfoManager();
            List<Pub_API_INFO> apiInfoList = apiManager.getUserApiInfo(user.getId());
            SessionManager.setSessionApiList(request, apiInfoList);
            response.sendRedirect("app/customer/home");
       }
      } else
        response.sendRedirect("login?WrongPassword=True");
      }
      else  response.sendRedirect("login?NotFound=True");
    }
    

}
