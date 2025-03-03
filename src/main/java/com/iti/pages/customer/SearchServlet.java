package com.iti.pages.customer;
import com.iti.managers.MessageManager;
import com.iti.models.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display the search bar without performing any search
        fetchMessagesAndForward( request, response);
        
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
        request.getRequestDispatcher("/customer_pages/search.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get search parameters from the request
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        // Parse date strings into Date objects


// Your existing code...

Timestamp startDate = null;
Timestamp endDate = null;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

try {
    if (startDateStr != null && !startDateStr.isEmpty()) {
        // Parse the string into a java.util.Date
        Date parsedStartDate = dateFormat.parse(startDateStr);
        // Convert java.util.Date to java.sql.Timestamp
        startDate = new Timestamp(parsedStartDate.getTime());
    }
    if (endDateStr != null && !endDateStr.isEmpty()) {
        // Parse the string into a java.util.Date
        Date parsedEndDate = dateFormat.parse(endDateStr);
        // Convert java.util.Date to java.sql.Timestamp
        endDate = new Timestamp(parsedEndDate.getTime());
    }
} catch (ParseException e) {
    e.printStackTrace();
    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format. Use yyyy-MM-dd.");
    return;
}

        // Initialize the MessageManager
        MessageManager messageManager = new MessageManager();
        messageManager.open();

        // Perform the search
        List<Message> messages = messageManager.searchMessages("01097002825", from, to, startDate, endDate, null);

        // Close the MessageManager
        messageManager.close();

        // Set the search results as a request attribute
        request.setAttribute("messages", messages);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/customer_pages/search.jsp").forward(request, response);
    }
}