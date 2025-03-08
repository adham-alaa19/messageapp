/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.start.otp;

import com.iti.managers.session.OTP_Manager;
import com.iti.managers.session.SessionManager;
import com.iti.managers.users.CustomerManager;
import com.iti.models.users.Customer;
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
@WebServlet(name = "OTP_Send", urlPatterns = {"/otp-send"})
public class OTP_Send extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OTP_Manager otpManager = new OTP_Manager();
        String sendTarget = "";
        String hiddenSendTarget;
        String mail = "";
        Customer customer = SessionManager.getSessionCustomer(request);
        if (customer != null) {
            mail = customer.getEmail();
            if (otpManager.getOtpType().equals("phone")) {
                sendTarget = customer.getMsisdn();
            } else if (otpManager.getOtpType().equals("mail")) {
                sendTarget = customer.getEmail();
            }
            setAndSend(mail, sendTarget, request);
            otpManager.storeAndSendOTP(request, sendTarget);
            response.sendRedirect("verify");
        } else {

            mail = request.getParameter("otpmail");
            if (mail == null || mail.trim().isEmpty()) {
                response.sendRedirect("login?NotFound=True");
                return;
            }
            CustomerManager customerManager = new CustomerManager();
            String phone = customerManager.getCustomerPhone(mail);
            if (phone == null) {
                response.sendRedirect("login?NotFound=True");
                return;
            }
            if (otpManager.getOtpType().equals("phone")) {
                sendTarget = phone;
            } else if (otpManager.getOtpType().equals("mail")) {
                sendTarget = mail;
            }
            SessionManager.startSession(request);
            setAndSend(mail, sendTarget, request);
            otpManager.storeAndSendOTP(request, sendTarget);
            response.sendRedirect("otp-login-page");

        }

    }

    private void setAndSend(String mail, String sendTarget, HttpServletRequest request) {
        String hiddenSendTarget = sendTarget.substring(0,  6) + "XXXXX";
        SessionManager.setSessionAttribute(request, "mail", mail);
        SessionManager.setSessionAttribute(request, "sendTarget", sendTarget);
        SessionManager.setSessionAttribute(request, "hiddenSendTarget", hiddenSendTarget);
    }

}
