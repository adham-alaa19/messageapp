/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.admin;

import com.iti.managers.SessionManager;
import com.iti.managers.UserManager;
import com.iti.models.Admin;
import com.iti.models.IUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author theda
 */
@WebServlet(name = "AdminHome", urlPatterns = {"/adminhome"})
public class AdminHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            if (SessionManager.isLoggedIn(request)) {
                Admin user = SessionManager.getSessionAdmin(request);
                if (user == null) response.sendRedirect("home");
                request.setAttribute("pageTitle", "Admin Home");
                request.getRequestDispatcher("includes/header.jsp").include(request, response);
                request.getRequestDispatcher("includes/navbars/adminbar.html").include(request, response);
               /*******Page Content*******/
                request.getRequestDispatcher("pages/admin_pages/adminhome.html").include(request, response);
                /*******Page Content*******/
                request.getRequestDispatcher("includes/footer.html").include(request, response);
            } else {
                response.sendRedirect("login?Logged=False");
            }
        } catch (Exception e) {
            System.out.println("ERRORTTTT");
            e.printStackTrace();
        }
    }

}
