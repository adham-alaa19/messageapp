/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.customer;

import com.iti.managers.session.SessionManager;
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
@WebServlet(name = "ProfileServelt", urlPatterns = {"/app/customer/profile"})
public class ProfileServelt extends HttpServlet {
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", "Profile");
        request.setAttribute("cssFile", "../../static/css/customer_pages/send.css");
        Customer customer = SessionManager.getSessionCustomer(request);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("../../includes/header.jsp").include(request, response);
        request.getRequestDispatcher("../../includes/navbars/customerbar.jsp").include(request, response);
        request.getRequestDispatcher("../../pages/customer_pages/profile.jsp").include(request, response);
        request.getRequestDispatcher("../../includes/footer.html").include(request, response);
    }
 
}
