<%@page import="com.iti.models.Message"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Messages</title>
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
                        <a class="nav-link active" href="SearchServlet">
                            <i class="fas fa-envelope"></i> Messages
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <!-- Main Content -->
        <div class="col-md-10 main-content">
            <div class="container mt-4">
                <h2><i class="fas fa-envelope"></i> Message Management</h2>

                <!-- Search Bar -->
                <div class="card mb-4">
                    <div class="card-body">
                        <form action="SearchServlet" method="POST">
                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="from">From</label>
                                    <input type="text" class="form-control" id="from" name="from" placeholder="Sender">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="to">To</label>
                                    <input type="text" class="form-control" id="to" name="to" placeholder="Receiver">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="startDate">Start Date</label>
                                    <input type="date" class="form-control" id="startDate" name="startDate">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="endDate">End Date</label>
                                    <input type="date" class="form-control" id="endDate" name="endDate">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-search"></i> Search
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Error message if date parsing failed -->
                <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger mb-4">
                    <%= request.getAttribute("errorMessage") %>
                </div>
                <% } %>

                <!-- Messages Table (Conditional) -->
                <%
                    List<Message> messages = (List<Message>) request.getAttribute("messages");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (messages != null && !messages.isEmpty()) {
                %>
                <div class="card">
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>From</th>
                                    <th>To</th>
                                    <th>Body</th>
                                    <th>Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Message message : messages) {
                                        String formattedDate = "";
                                        if (message.getMsg_date() != null) {
                                            formattedDate = dateFormat.format(message.getMsg_date());
                                        }
                                %>
                                <tr>
                                    <td><%= message.getMsg_from() %></td>
                                    <td><%= message.getMsg_to() %></td>
                                    <td><%= message.getBody() %></td>
                                    <td><%= formattedDate %></td>
                                    <td>
                                        <form action="DisplayServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="messageId" value="<%= message.getMsg_id() %>">
                                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this message?');">
                                                <i class="fas fa-trash"></i> Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <%
                    } else if (messages != null && messages.isEmpty()) {
                %>
                <div class="alert alert-info mt-4">
                    No messages found matching your search criteria.
                </div>
                <%
                    }
                %>

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