/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package com.iti.filters;

import com.iti.managers.session.SessionManager;
import java.io.IOException;
import jakarta.servlet.DispatcherType;
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
@WebFilter(filterName = "SendFilter", urlPatterns = {"/app/customer/send"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class SendFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpreq = (HttpServletRequest) request;
        HttpServletResponse httpres = (HttpServletResponse) response;
        int apiId = SessionManager.getSessionApiInfo(httpreq);
        if (apiId == -1) {
            httpres.sendRedirect("addApiInfo");
            return;
        }
       chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {

    }

}
