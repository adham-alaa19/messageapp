/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.admin;


import com.iti.managers.users.AdminManager;
import com.iti.models.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/admin/userDetails")
public class UserDetailsServlet extends HttpServlet {

    private final AdminManager adminManager = new AdminManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        Customer user = adminManager.getUser(userId);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../../pages/admin_pages/userDetails.jsp");
        dispatcher.forward(request, response);
    }
}
