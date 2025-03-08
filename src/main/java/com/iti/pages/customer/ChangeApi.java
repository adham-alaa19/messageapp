/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.customer;

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
@WebServlet(name = "ChangeApi", urlPatterns = {"/app/customer/changeApi"})
public class ChangeApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String apiIdStr = request.getParameter("apiName");
        try {
            int apiId = Integer.parseInt(apiIdStr);
            System.out.println("Current API id=s "+apiId);
            SessionManager.setSessionApi(request, apiId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

}
