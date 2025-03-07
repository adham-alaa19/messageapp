 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add API Info</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="css/common.css"> <!-- Keeping styles consistent -->
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 sidebar">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item"><a class="nav-link" href="home"><i class="fas fa-home"></i> Dashboard</a></li>
                        <li class="nav-item"><a class="nav-link active" href="addApi"><i class="fas fa-plus"></i> Add API Info</a></li>
                    </ul>
                </div>
            </div>

            <!-- Main Content -->
            <div class="col-md-10 main-content">
                <div class="container mt-4">
                    <h2><i class="fas fa-plug"></i> Add API Information</h2>
                    <div id="error-summary" class="alert alert-danger d-none"></div>
                    <form action="addApiInfo" method="post" id="add-api-form">
                        <div class="form-group">
                            <label for="api_name">SMS Provider</label>
                            <select id="api_name" name="api_name" class="form-control" required>
                                <option value="" disabled selected>Select a provider</option>
                                <%
                                    // Fetch the list of APIs from the database
                                    List<Map<String, String>> apiList = (List<Map<String, String>>) request.getAttribute("apiList");
                                    if (apiList != null) {
                                        for (Map<String, String> api : apiList) {
                                            String apiName =  api.get("api_name");
                                            String apiCode =  api.get("api_code");
                                %>
                                            <option value="<%= apiCode %>"><%= apiName %></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                            <div class="my-error" id="error-api_name"></div>
                        </div>

                        <div class="form-group">
                            <label for="api_key">API Key</label>
                            <input type="text" id="api_key" name="api_key" class="form-control" maxlength="255" required>
                            <div class="my-error" id="error-api_key"></div>
                        </div>

                        <div class="form-group">
                            <label for="api_secret">API Secret</label>
                            <input type="text" id="api_secret" name="api_secret" class="form-control" maxlength="255" required>
                            <div class="my-error" id="error-api_secret"></div>
                        </div>

                        <div class="form-group">
                            <label for="sender_id">Sender ID</label>
                            <input type="text" id="sender_id" name="sender_id" class="form-control" maxlength="20" required>
                            <div class="my-error" id="error-sender_id"></div>
                        </div>

                        <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Save API</button>
                        <a href="home" class="btn btn-secondary">Cancel</a>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="js/addApi.js"></script> <!-- Keeping JS structure consistent -->
</body>
</html>