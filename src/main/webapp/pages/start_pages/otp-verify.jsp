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

    <div class="text-end mt-3">
        <a href="logout" class="btn btn-warning">Login with another account</a>
    </div>

    <div class="container d-flex flex-column justify-content-center align-items-center vh-100">

        <div style="width: 100%; max-width: 400px;"> 
            <div class="w-100 text-start mb-2">
                <medium class="text-muted ms-2">An OTP was sent to <strong><%= request.getAttribute("hiddenTarget") %></strong></medium>
            </div>

            <div class="otp-container shadow-lg p-4" style="width: 100%; max-width: 400px;">
                <h3 class="text-center mb-3">OTP Verification</h3>
                <p class="text-muted text-center">Enter the OTP sent to your phone.</p>

                <!-- OTP Verification Form -->
                <form method="post" action="verify" id="otp-form" class="d-flex flex-column align-items-center">
                    <div class="d-flex justify-content-center gap-2">
                        <input name="d1" type="text" class="otp-input form-control text-center" maxlength="1" required>
                        <input name="d2" type="text" class="otp-input form-control text-center" maxlength="1" required>
                        <input name="d3" type="text" class="otp-input form-control text-center" maxlength="1" required>
                        <input name="d4" type="text" class="otp-input form-control text-center" maxlength="1" required>
                    </div>

                    <!-- Hidden Input for User ID -->
                    <button type="submit" class="btn btn-primary mt-3 w-100">Verify OTP</button>
                </form>

                <!-- Resend OTP Form -->
                <form method="post" action="otp-send" id="resend-form" class="mt-2">
                    <button type="submit" class="btn btn-link w-100">Resend OTP</button>
                </form>

            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="static/js/start_pages/otp2.js"></script>

</body>
</html>
