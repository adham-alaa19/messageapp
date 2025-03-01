<%@page import="com.iti.models.Customer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Users List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/common.css">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="adminHome">
                            <i class="fas fa-home"></i> Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="adminUsers">
                            <i class="fas fa-users"></i> Users
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 main-content">
            <div class="container mt-4">
                <h2><i class="fas fa-users"></i> User Management</h2>

                <!-- Users Table -->
                <div class="card">
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                                    if (customers != null) {
                                        for (Customer customer : customers) {
                                %>
                                <tr>
                                    <td><%= customer.getId() %></td>
                                    <td><%= customer.getFirstName() %></td>
                                    <td><%= customer.getLastName() %></td>
                                    <td><%= customer.getEmail() %></td>
                                    <td>
                                        <a href="userDetails?id=<%= customer.getId() %>" class="btn btn-info btn-sm">
                                            <i class="fas fa-info-circle"></i> Details
                                        </a>
                                        <form action="deleteUser" method="post" style="display:inline;">
                                            <input type="hidden" name="id" value="<%= customer.getId() %>">
                                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">
                                                <i class="fas fa-trash"></i> Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>

                <a href="adminHome" class="btn btn-secondary mt-3">
                    <i class="fas fa-arrow-left"></i> Back to Admin Home
                </a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
