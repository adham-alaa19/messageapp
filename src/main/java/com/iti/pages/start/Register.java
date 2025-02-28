/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.start;

import com.iti.database.DB_Handler;
import com.iti.database.psql.PSQL_Handler;
import com.iti.managers.session.SessionManager;
import com.iti.managers.users.UserManager;
import com.iti.models.Customer;
import com.iti.utils.UserValidator;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

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
             
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String job = request.getParameter("job");
            String governorate = request.getParameter("city");
            String district = request.getParameter("district");
            String street = request.getParameter("street");   
            String birthDate = request.getParameter("dateOfBirth");
            String buildingNo = request.getParameter("building");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
    
            ArrayList<String> errors = UserValidator.validateUser(firstName, lastName, birthDate, email, password,
                confirmPassword, job, governorate, district, street, buildingNo, phone);

        if (!errors.isEmpty()) {
            response.sendRedirect("register?" + String.join("&", errors));
            return;
        }
           UserManager userManager = new UserManager();
            
        
    }
        
        
        
    }

