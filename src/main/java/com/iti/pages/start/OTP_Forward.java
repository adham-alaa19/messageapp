/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.start;

import com.iti.managers.users.CustomerManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author theda
 */
@WebServlet(name = "OTP_Redirect", urlPatterns = {"/otp-login"})
public class OTP_Forward extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mail = request.getParameter("otpmail");
        if (mail == null || mail.trim().isEmpty()) {
            response.sendRedirect("login?NotFound=True");
            return;
        }
        CustomerManager customerManager = new CustomerManager();
        String phone = customerManager.getCustomerPhone(mail);
        request.setAttribute("phone", phone);
         request.setAttribute("mail", mail);
        if (phone != null) {
            request.getRequestDispatcher("otp-login-page").forward(request, response);
        } else {
            response.sendRedirect("login?NotFound=True");
        }

    }

}
