/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package com.iti.filters;

import com.iti.managers.session.SessionManager;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author theda
 */
@WebFilter(urlPatterns = {"/login", "/register"})
public class HomeRedirectFilter implements Filter {

        
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void destroy() {
    }
    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String fullURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + httpRequest.getContextPath(); 
        if (SessionManager.isLoggedIn(httpRequest)) {
            if (SessionManager.isAdminSession(httpRequest)) {
                httpResponse.sendRedirect(fullURL+"/app/admin/home");
                 return;
            } else {
                httpResponse.sendRedirect(fullURL+"/app/customer/home");
                 return;
            }
        }

        chain.doFilter(request, response);

    }

}
