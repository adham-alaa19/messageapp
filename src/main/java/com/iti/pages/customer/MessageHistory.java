package com.iti.pages.customer;

import com.iti.managers.messages.MessageManager;
import com.iti.managers.session.SessionManager;
import com.iti.models.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "MessageHistory", urlPatterns = {"/app/customer/history"})
public class MessageHistory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        fetchMessagesAndForward(request, response);

    }
    private void fetchMessagesAndForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MessageManager messageManager = new MessageManager();
        messageManager.open();
        int fromId = SessionManager.getSessionCustomer(request).getId();
        List<Message> messages = messageManager.getUserMessages(fromId);
        messageManager.close();
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("../../pages/customer_pages/messages.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        Timestamp startDate = null;
        Timestamp endDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (startDateStr != null && !startDateStr.isEmpty()) {
                Date parsedStartDate = dateFormat.parse(startDateStr);
                startDate = new Timestamp(parsedStartDate.getTime());
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                Date parsedEndDate = dateFormat.parse(endDateStr);
                endDate = new Timestamp(parsedEndDate.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format. Use yyyy-MM-dd.");
            return; }
        MessageManager messageManager = new MessageManager();
        messageManager.open();
        int fromId = SessionManager.getSessionCustomer(request).getId();

        List<Message> messages = messageManager.searchMessages(fromId, from, to, startDate, endDate, null);

        messageManager.close();

        request.setAttribute("messages", messages);

        request.getRequestDispatcher("../../pages/customer_pages/messages.jsp").forward(request, response);
    }
}
