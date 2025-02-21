/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.start;

import com.iti.managers.SessionManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author theda
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try (PrintWriter out = response.getWriter()) {
      if(SessionManager.isLoggedIn(request))   response.sendRedirect("adminhome");
      request.getRequestDispatcher("pages/start_pages/register.html").include(request, response);
      String s= request.getParameter("Exist");
            if("True".equals(s))
                out.println("<h3> User  Exist</h3>");
         }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        DB_connector dbc = new DB_connector();
//        int id = Integer.parseInt(request.getParameter("id"));
//       boolean added= dbc.addUser(id, request.getParameter("fname"), request.getParameter("lname"), request.getParameter("uname"), request.getParameter("password"));
//       if(added) 
//       response.sendRedirect("homecookie");
//      else  response.sendRedirect("register?Exist=True");
//    
    }
}
