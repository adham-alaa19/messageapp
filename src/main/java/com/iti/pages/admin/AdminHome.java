/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.admin;

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
@WebServlet(name = "AdminHome", urlPatterns = {"/adminhome"})
public class AdminHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try (PrintWriter out = response.getWriter()) {
        if(SessionManager.isLoggedIn(request)){
      request.getRequestDispatcher("includes/header.html").include(request, response);
      request.getRequestDispatcher("includes/adminbar.html").include(request, response);
      request.getRequestDispatcher("admin_pages/adminhome.html").include(request, response);
      request.getRequestDispatcher("includes/footer.html").include(request, response);
    }
               else 
         response.sendRedirect("loginsession?Logged=False");

         } catch(Exception e) {
             System.out.println("ERRORTTTT");
             e.printStackTrace();
         }
     }

    
}
