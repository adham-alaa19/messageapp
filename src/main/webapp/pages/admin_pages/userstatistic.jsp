 <%@page import="com.iti.models.Customer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - User Statistics</title>
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
                        <a class="nav-link" href="home">
                            <i class="fas fa-home"></i> Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="userstatistic">
                            <i class="fas fa-users"></i> Users
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 main-content">
            <div class="container mt-4">
                <h2><i class="fas fa-users"></i> User Statistics</h2>

                <!-- Users Table -->
                <div class="card">
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email</th>
                                    <th>Messages Sent</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                                    Map<Integer, Integer> userMessageCounts = (Map<Integer, Integer>) request.getAttribute("userMessageCounts");
                                    if (customers != null) {
                                        for (Customer customer : customers) {
                                            int messageCount = userMessageCounts.getOrDefault(customer.getId(), 0);
                                %>
                                <tr>
                                    <td><%= customer.getFirstName() %></td>
                                    <td><%= customer.getLastName() %></td>
                                    <td><%= customer.getEmail() %></td>
                                    <td><%= messageCount %></td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>

                <a href="home" class="btn btn-secondary mt-3">
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