<%@page session="false" contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OTP Verification</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/start_pages/otp.css">
</head>
<body>

<div class="container d-flex flex-column justify-content-center align-items-center vh-100">
    <!-- Left-aligned "OTP Sent" message above the card -->
    <div style="width: 100%; max-width: 400px;"> 
        <div class="w-100 text-start mb-2">
            <medium class="text-muted ms-2">An OTP was sent to <strong><%= request.getAttribute("hiddenTarget") %></strong></medium>
        </div>
        
        <div class="otp-container shadow-lg p-4" style="width: 100%; max-width: 400px;">
            <h3 class="text-center mb-3">OTP Login</h3>
            <p class="text-muted text-center">Enter the OTP sent to your phone.</p>
            
            <!-- OTP Login Form -->
            <form id="otp-form" action="otp-login-page" method="post" class="d-flex flex-column align-items-center">
                <div class="d-flex justify-content-center gap-2">
                    <input type="text" name="d1" class="otp-input form-control text-center" maxlength="1" required>
                    <input type="text" name="d2" class="otp-input form-control text-center" maxlength="1" required>
                    <input type="text" name="d3" class="otp-input form-control text-center" maxlength="1" required>
                    <input type="text" name="d4" class="otp-input form-control text-center" maxlength="1" required>
                </div>
                
                <input type="hidden" name="mail" value="<%= request.getAttribute("mail") %>">
                <button type="submit" class="btn btn-primary mt-3 w-100">Login</button>
            </form>

            <form method="post" action="otp-send" id="resend-form" class="mt-2">
                <input type="hidden" name="otpmail" value="<%= request.getAttribute("mail") %>">
                <button type="submit" class="btn btn-link w-100">Resend OTP</button>
            </form>
            
            <div class="text-end mt-3">
                <a href="login" class="text-decoration-none">Back to Regular Login</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Modal for Invalid OTP -->
<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="errorModalLabel">Invalid OTP</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                The OTP you entered is incorrect. Please try again.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener("DOMContentLoaded", function () {

        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get("WrongCode") === "True") {
            var errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
            errorModal.show();
        }
    });
</script>

</body>
</html>
