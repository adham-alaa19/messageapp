 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iti.models.users.Customer" %>
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
            <p><strong>ID:</strong> <%= user.getCustomer_pub_id() %></p>
            <p><strong>First Name:</strong> <%= user.getFirstName() %></p>
            <p><strong>Last Name:</strong> <%= user.getLastName() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>

            <!-- Hidden Input for User ID inside a form -->
            <form action="edit_customer" method="get">
                <input type="hidden" name="uid" value="<%=  user.getCustomer_pub_id() %>">
                <button type="submit" class="btn btn-primary mt-3">
                    <i class="fas fa-edit"></i> Edit User
                </button>
            </form>

            <%
                } else {
            %>
            <p class="text-danger">User not found.</p>
            <%
                }
            %>
        </div>
    </div>

    <a href="viewusers" class="btn btn-secondary mt-3">
        <i class="fas fa-arrow-left"></i> Back to Users List
    </a>
</div>

</body>
</html>
