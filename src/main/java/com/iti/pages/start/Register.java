/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.start;

import com.iti.database.DB_Handler;
import com.iti.database.psql.PSQL_Handler;
import com.iti.exceptions.UserAlreadyExistsException;
import com.iti.managers.messages.ApiInfoManager;
import com.iti.managers.session.SessionManager;
import com.iti.managers.users.UserManager;
import com.iti.models.messages.Pub_API_INFO;
import com.iti.models.users.Customer;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            if (SessionManager.isLoggedIn(request)) {
                response.sendRedirect("adminhome");
            }
            request.getRequestDispatcher("pages/start_pages/register.html").include(request, response);
            String s = request.getParameter("Exist");
            if ("True".equals(s)) {
                out.println("<h3> User  Exist</h3>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String job = request.getParameter("job");
        String governorate = request.getParameter("city");
        String district = request.getParameter("district");
        String street = request.getParameter("street");
        String birthDateString = request.getParameter("birthdate");
        String buildingNoStr = request.getParameter("building");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        ArrayList<String> errors = UserValidator.validateUser(firstName, lastName, birthDateString, email, password,
                confirmPassword, job, governorate, district, street, buildingNoStr, phone);

        if (!errors.isEmpty()) {
            response.sendRedirect("register?" + String.join("&", errors));
            return;
        }
        Date birthDate = null;
        try {
            birthDate = java.sql.Date.valueOf(birthDateString);
        } catch (IllegalArgumentException e) {
            // Should not occur if validation passed
        }
        int buildingNo = 1;
        try {
            buildingNo = Integer.parseInt(buildingNoStr);
        } catch (NumberFormatException e) {
            // Should not occur if validation passed
        }
        UserManager userManager = new UserManager();
        try {
            Customer customer = userManager.createCustomer(firstName, lastName, birthDate, email, password,
                    job, governorate, district, street, buildingNo, phone);
            SessionManager.startSession(request, customer);
            ApiInfoManager apiManager = new ApiInfoManager();
            List<Pub_API_INFO> apiInfoList = apiManager.getUserApiInfo(customer.getId());
            SessionManager.setSessionApiList(request, apiInfoList);
            request.getRequestDispatcher("otp-send").forward(request,response);
        } catch (UserAlreadyExistsException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
           response.sendRedirect("register?Exist=True");
        }

    }

}
