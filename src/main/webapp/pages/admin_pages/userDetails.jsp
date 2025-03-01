<%-- 
    Document   : userDetails
    Created on : Feb 28, 2025, 5:28:41 PM
    Author     : DeLL
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iti.models.Customer" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="css/common.css">
</head>
<body>

<div class="container mt-5">
    <h2><i class="fas fa-user"></i> User Details</h2>

    <div class="card">
        <div class="card-body">
            <%
                Customer user = (Customer) request.getAttribute("user");
                if (user != null) {
            %>
            <p><strong>ID:</strong> <%= user.getId() %></p>
            <p><strong>First Name:</strong> <%= user.getFirstName() %></p>
            <p><strong>Last Name:</strong> <%= user.getLastName() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <%
                } else {
            %>
            <p class="text-danger">User not found.</p>
            <%
                }
            %>
        </div>
    </div>

    <a href="adminUsers" class="btn btn-secondary mt-3">
        <i class="fas fa-arrow-left"></i> Back to Users List
    </a>
</div>

</body>
</html>
