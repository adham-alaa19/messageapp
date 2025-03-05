<%-- 
    Document   : header
    Created on : 21 Feb 2025, 12:14:18
    Author     : theda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <%
            String pageTitle = (String) request.getAttribute("pageTitle");
            if (pageTitle == null) {
                pageTitle = "Messenger App";
            }
        %>
        <title><%= request.getAttribute("pageTitle")%></title>    
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../static/css/common.css">
        <%
            String cssFile = (String) request.getAttribute("cssFile");
            if (cssFile == null) {
                cssFile = "";
            }
        %>
        <link rel="stylesheet" href="<%= request.getAttribute("cssFile")%>">
    </head>