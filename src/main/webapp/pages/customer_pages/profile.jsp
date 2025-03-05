 <%@ page contentType="text/html" pageEncoding="UTF-8" import="com.iti.models.Customer, com.iti.models.Address" %>
<%
    // Retrieve the Customer object from the request
    Customer customer = (Customer) request.getAttribute("customer");
    if (customer == null) {
        out.println("No customer data found!");
        return;
    }
%>
<div class="container mt-5">
    <h2>Profile</h2>
    <hr>
    <!-- Display customer information -->
    <div class="row">
        <div class="col-md-6">
            <p><strong>First Name:</strong> <%= customer.getFirstName() %></p>
            <p><strong>Last Name:</strong> <%= customer.getLastName() %></p>
            <p><strong>Email:</strong> <%= customer.getEmail() %></p>
            <p><strong>Job:</strong> <%= customer.getJob() %></p>
            <p><strong>Birth Date:</strong> <%= customer.getBirth_date() %></p>
            <p><strong>MSISDN:</strong> <%= customer.getMsisdn() %></p>
        </div>
        <div class="col-md-6">
            <h4>Address</h4>
            <p><strong>Governorate:</strong> <%= customer.getAddress().getGovernorate() %></p>
            <p><strong>District:</strong> <%= customer.getAddress().getDistrict() %></p>
            <p><strong>Street:</strong> <%= customer.getAddress().getStreet() %></p>
            <p><strong>Building No:</strong> <%= customer.getAddress().getBuilding() %></p>
        </div>
    </div>
    <div class="mt-4">
        <!-- Buttons to edit profile and add new API info -->
        <a href="edit_customer" class="btn btn-primary">Edit Profile</a>
        <a href="addApiInfo" class="btn btn-secondary">Add New API Info</a>
    </div>
</div>
