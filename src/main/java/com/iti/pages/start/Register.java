/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.start;

import com.iti.database.DB_Handler;
import com.iti.database.psql.PSQL_Handler;
import com.iti.managers.SessionManager;
import com.iti.models.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

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
//       else  response.sendRedirect("register?Exist=True");
//    
              String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        Date birthDate = Date.valueOf(request.getParameter("birth_date")); // Ensure the format is YYYY-MM-DD
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String job = request.getParameter("job");
        
        String governorate = request.getParameter("governorate");
        String district = request.getParameter("district");
        String street = request.getParameter("street");
        int buildingNo = Integer.parseInt(request.getParameter("building_no"));

        String msisdn = request.getParameter("msisdn");

        // Creating a Customer object
        Customer customer = new Customer(firstName, lastName, birthDate, email, password, job, governorate, district, street, buildingNo, msisdn);
        
        
        
    }
}
