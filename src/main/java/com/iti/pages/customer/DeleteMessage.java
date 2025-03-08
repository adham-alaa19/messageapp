/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.customer;

import com.iti.managers.messages.MessageManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author theda
 */
@WebServlet(name = "DeleteMessage", urlPatterns = {"/app/customer/deletemessage"})
public class DeleteMessage extends HttpServlet {
   
       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String messageId = request.getParameter("messageId");
            if (messageId != null && !messageId.isEmpty()) {
               
                MessageManager messageManager = new MessageManager();
                messageManager.open();
                Integer.parseInt(messageId);
                boolean isDeleted = messageManager.deleteMessage (Integer.parseInt(messageId));
                messageManager.close();

                if (isDeleted) {
                  
                    response.sendRedirect("history");
                    return;
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete message");
                    return;
                }
            }
        }
    }}
