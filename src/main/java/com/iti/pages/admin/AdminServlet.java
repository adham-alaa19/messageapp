/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.admin;



import com.iti.managers.users.AdminManager;
import com.iti.models.users.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/app/admin/viewusers")
@WebServlet(name = "AdminServlet", urlPatterns = {"/app/admin/viewusers"})
public class AdminServlet extends HttpServlet {
    
    private final AdminManager adminManager = new AdminManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> users = adminManager.getAllUsers();
        request.setAttribute("customers", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../../pages/admin_pages/adminUsers.jsp");
        dispatcher.forward(request, response);
    }
}
