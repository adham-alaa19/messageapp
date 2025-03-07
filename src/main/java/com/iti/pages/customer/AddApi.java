/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.pages.customer;

import com.iti.managers.messages.ApiInfoManager;
import com.iti.managers.session.SessionManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author theda
 */
@WebServlet(name = "AddApi", urlPatterns = {"/app/customer/addApiInfo"})
public class AddApi extends HttpServlet {

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       ApiInfoManager apiManager = new ApiInfoManager();
     List<Map<String, String>> apiList = apiManager.getAvailableApi();
    request.setAttribute("apiList", apiList);
    request.getRequestDispatcher("../../pages/customer_pages/addAPI.jsp").forward(request, response);
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    String apiName = request.getParameter("api_name");
    String apiKey = request.getParameter("api_key");
    String apiSecret = request.getParameter("api_secret");
    String senderId = request.getParameter("sender_id");
    
    if (apiName == null || apiName.trim().isEmpty() ||
        apiKey == null || apiKey.trim().isEmpty() ||
        apiSecret == null || apiSecret.trim().isEmpty() ||
        senderId == null || senderId.trim().isEmpty()) 
    {
    response.sendRedirect("addApiInfo?Filled=False");
    }
    ApiInfoManager apiInfoManager = new ApiInfoManager();
    int apiId = apiInfoManager.createApiInfo(apiName, apiKey, apiSecret, senderId, SessionManager.getSessionCustomer(request).getId()).getApi_id();

    }

}
