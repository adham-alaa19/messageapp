<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iti.models.users.Customer" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    Customer customer = (Customer) request.getAttribute("customer");

    String formattedBirthDate = "";
    if (customer != null && customer.getBirth_date() != null) {
        formattedBirthDate = new SimpleDateFormat("yyyy-MM-dd").format(customer.getBirth_date());
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit Profile</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="css/common.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 sidebar">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item"><a class="nav-link" href="home"><i class="fas fa-home"></i> Dashboard</a></li>
                            <li class="nav-item"><a class="nav-link" href="profile"><i class="fas fa-user"></i> Profile</a></li>
                            <li class="nav-item"><a class="nav-link active" href="edit"><i class="fas fa-edit"></i> Edit Profile</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-10 main-content">
                    <div class="container mt-4">
                        <h2><i class="fas fa-edit"></i> Edit Profile</h2>
                        <div id="error-summary" class="alert alert-danger d-none"></div>

                        <form action="edit_customer" method="post" id="edit-customer-form">
                            <div class="form-group">
                                <label for="firstname">First Name</label>
                                <input type="text" id="firstname" name="firstname" class="form-control" value="<%= customer.getFirstName()%>" required>
                                <div class="my-error" id="error-firstname"></div>
                            </div>
                            <div class="form-group">
                                <label for="lastname">Last Name</label>
                                <input type="text" id="lastname" name="lastname" class="form-control" value="<%= customer.getLastName()%>" required>
                                <div class="my-error" id="error-lastname"></div>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" class="form-control" value="<%= customer.getEmail()%>" required>
                                <div class="my-error" id="error-email"></div>
                            </div>
                            <div class="form-group">
                                <label for="job">Job</label>
                                <input type="text" id="job" name="job" class="form-control" value="<%= customer.getJob()%>">
                                <div class="my-error" id="error-job"></div>
                            </div>
                            <div class="form-group">
                                <label for="birthdate">Birth Date</label>
                                <input type="date" id="birthdate" name="birthdate" class="form-control" value="<%= formattedBirthDate%>">
                                <div class="my-error" id="error-birthdate"></div>
                            </div>
                            <div class="form-group">
                                <label for="msisdn">Phone Number</label>
                                <input type="text" id="msisdn" name="msisdn" class="form-control" value="<%= customer.getMsisdn()%>">
                                <div class="my-error" id="error-msisdn"></div>
                            </div>

                            <h4>Address</h4>
                            <div class="form-group">
                                <label for="governorate">Governorate</label>
                                <input type="text" id="governorate" name="governorate" class="form-control" value="<%= customer.getAddress().getGovernorate()%>">
                                <div class="my-error" id="error-governorate"></div>
                            </div>
                            <div class="form-group">
                                <label for="district">District</label>
                                <input type="text" id="district" name="district" class="form-control" value="<%= customer.getAddress().getDistrict()%>">
                                <div class="my-error" id="error-district"></div>
                            </div>
                            <div class="form-group">
                                <label for="street">Street</label>
                                <input type="text" id="street" name="street" class="form-control" value="<%= customer.getAddress().getStreet()%>">
                                <div class="my-error" id="error-street"></div>
                            </div>
                            <div class="form-group">
                                <label for="buildingno">Building No</label>
                                <input type="text" id="buildingno" name="buildingno" class="form-control" value="<%= customer.getAddress().getBuilding()%>">
                                <div class="my-error" id="error-buildingno"></div>
                            </div>
                            <input type="hidden" id="uid" name="uid" class="form-control" value="<%= customer.getCustomer_pub_id()%>">

                            <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Save Changes</button>
                            <a href="profile" class="btn btn-secondary">Cancel</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="../../static/js/customer_pages/editCustomer.js"></script>
    </body>
</html>
