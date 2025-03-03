package com.iti.pages.customer;
import com.iti.managers.MessageManager;
import com.iti.models.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DisplayServlet", urlPatterns = {"/DisplayServlet"})
public class DisplayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String messageId = request.getParameter("messageId");
            if (messageId != null && !messageId.isEmpty()) {
                // Delete the message
                MessageManager messageManager = new MessageManager();
                messageManager.open();
                Integer.parseInt(messageId);
                boolean isDeleted = messageManager.deleteMessage (Integer.parseInt(messageId));
                messageManager.close();

                if (isDeleted) {
                    // Redirect to the same page to refresh the message list
                    response.sendRedirect("SearchServlet");
                    return;
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete message");
                    return;
                }
            }
        }

        // Fetch messages and forward to JSP
        fetchMessagesAndForward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch messages and forward to JSP
        fetchMessagesAndForward(request, response);
    }

    private void fetchMessagesAndForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialize the MessageManager
        MessageManager messageManager = new MessageManager();
        messageManager.open();

        // Fetch messages for the user
        String username = "01097002825"; // Replace with dynamic username if needed
        List<Message> messages = messageManager.getUserMessages(username);

        // Close the MessageManager
        messageManager.close();

        // Set the messages as a request attribute
        request.setAttribute("messages", messages);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/customer_pages/messages.jsp").forward(request, response);
    }
}