/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.customer;

import com.iti.managers.messages.ApiInfoManager;
import com.iti.managers.messages.SendManager;
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
@WebServlet(name = "SendMessage", urlPatterns = {"/app/customer/send"})
public class SendMessage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            int apiId = SessionManager.getSessionApiInfo(request);
            ApiInfoManager apiManager = new ApiInfoManager();
            String senderId = apiManager.getApiSenderId(apiId);
            request.setAttribute("pageTitle", "SendMessages");
            request.setAttribute("cssFile", "../../static/css/customer_pages/send.css");
            request.setAttribute("senderId", senderId);
            request.getRequestDispatcher("../../includes/header.jsp").include(request, response);
            request.getRequestDispatcher("../../includes/navbars/customerbar.jsp").include(request, response);
            request.getRequestDispatcher("../../pages/customer_pages/send.jsp").include(request, response);
            request.getRequestDispatcher("../../includes/footer.html").include(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            String body = request.getParameter("body");
            SendManager sendManager = new SendManager();
            int apiId = SessionManager.getSessionApiInfo(request);
            sendManager.sendMessage(apiId, to, body);
            response.sendRedirect("home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
