/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.start;

import com.iti.managers.session.OTP_Manager;
import com.iti.managers.session.SessionManager;
import com.iti.managers.users.CustomerManager;
import com.iti.models.Customer;
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
@WebServlet(name = "Verify", urlPatterns = {"/verify"})
public class OTP_Verify extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = SessionManager.getSessionCustomer(request);
        OTP_Manager otpManager = new OTP_Manager();
        otpManager.storeAndSendOTP(request, customer.getMsisdn());
        System.out.println(customer.getMsisdn());
        System.out.println("HELLLLO THERE");
        System.out.println(customer.getMsisdn());
        request.setAttribute("phone", customer.getMsisdn());
        request.setAttribute("pub_id", customer.getCustomer_pub_id());
        request.getRequestDispatcher("pages/start_pages/otp-verify.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String d1 = request.getParameter("d1");
        String d2 = request.getParameter("d2");
        String d3 = request.getParameter("d3");
        String d4 = request.getParameter("d4");
        String id = request.getParameter("id");
        String otp = d1 + d2 + d3 + d4;
        OTP_Manager otpManager = new OTP_Manager();
       if(otpManager.validateOTP(request, otp)){
           CustomerManager customerManager = new CustomerManager();
          Customer customer = customerManager.verifyCustomer(SessionManager.getSessionCustomer(request));
          SessionManager.startSession(request, customer);
          response.sendRedirect("app/customer/home");
       }
    

    }

}
