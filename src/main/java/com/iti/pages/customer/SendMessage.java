/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.customer;

import com.iti.managers.messages.SendManager;
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
            
                request.setAttribute("pageTitle", "SendMessages");
                request.setAttribute("cssFile", "../../static/css/customer_pages/send.css");
                request.getRequestDispatcher("../../includes/header.jsp").include(request, response);
                request.getRequestDispatcher("../../includes/navbars/navbar.html").include(request, response);
                request.getRequestDispatcher("../../pages/customer_pages/send.html").include(request, response);
                request.getRequestDispatcher("../../includes/footer.html").include(request, response);
           
        } catch (Exception e) {
            System.out.println("ERRORTTTT");
            e.printStackTrace();
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try (PrintWriter out = response.getWriter()) {
                String from =request.getParameter("from");
                String to =request.getParameter("to");
                String body =request.getParameter("body");
                System.out.println( from + " " + to + " " + body);
                SendManager sendManager=new SendManager();
                sendManager.sendMessage(from, to, body);
        } catch (Exception e) {
            System.out.println("ERRORTTTT");
            e.printStackTrace();
        }
    }

}
