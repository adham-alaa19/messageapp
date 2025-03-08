/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.start.otp;

import com.iti.managers.messages.ApiInfoManager;
import com.iti.managers.session.OTP_Manager;
import com.iti.managers.session.SessionManager;
import com.iti.managers.users.CustomerManager;
import com.iti.models.messages.Pub_API_INFO;
import com.iti.models.users.Customer;
import java.io.IOException;
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
@WebServlet(name = "Verify", urlPatterns = {"/verify"})
public class OTP_Verify extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sendTarget = (String) SessionManager.getSessionAttribute(request, "hiddenSendTarget");
        request.setAttribute("hiddenTarget", sendTarget);
        request.getRequestDispatcher("pages/start_pages/otp-verify.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String d1 = request.getParameter("d1");
        String d2 = request.getParameter("d2");
        String d3 = request.getParameter("d3");
        String d4 = request.getParameter("d4");
        String otp = d1 + d2 + d3 + d4;
        OTP_Manager otpManager = new OTP_Manager();
        if (otpManager.validateOTP(request, otp)) {
            CustomerManager customerManager = new CustomerManager();
            Customer customer = customerManager.verifyCustomer(SessionManager.getSessionCustomer(request));
            SessionManager.startSession(request, customer);
            ApiInfoManager apiManager = new ApiInfoManager();
            List<Pub_API_INFO> apiInfoList = apiManager.getUserApiInfo(customer.getId());
            SessionManager.setSessionApiList(request, apiInfoList);
            response.sendRedirect("app/customer/home");
        } else {
            response.sendRedirect("verify?WrongCode=True");
        }

    }

}
