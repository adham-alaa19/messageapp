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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserStatisticServlet", urlPatterns = {"/app/admin/userstatistic"})
public class UserStatistic extends HttpServlet {

    private final AdminManager adminManager = new AdminManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> users = adminManager.getAllUsers();

        Map<Integer, Integer> userMessageCounts = new HashMap<>();

        for (Customer user : users) {
            int messageCount = adminManager.getMessageCount(user.getId());
            userMessageCounts.put(user.getId(), messageCount);
        }

        request.setAttribute("customers", users);
        request.setAttribute("userMessageCounts", userMessageCounts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("../../pages/admin_pages/userstatistic.jsp");
        dispatcher.forward(request, response);
    }
}