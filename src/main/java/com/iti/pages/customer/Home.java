  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.customer;

import com.iti.managers.session.SessionManager;
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
@WebServlet(name = "Home", urlPatterns = {"/app/customer/home"})
public class Home extends HttpServlet {

       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
        if(SessionManager.isLoggedIn(request)){
      request.getRequestDispatcher("includes/header.jsp").include(request, response);
      request.getRequestDispatcher("includes/navbar.html").include(request, response);
      request.getRequestDispatcher("pages/customer_pages/home.html").include(request, response);
      request.getRequestDispatcher("includes/footer.html").include(request, response);
    }else 
         response.sendRedirect("loginsession?Logged=False");

         } catch(Exception e) {
             System.out.println("ERRORTTTT");
             e.printStackTrace();
         }
     }
     
}