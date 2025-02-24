 document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const messageContainer = document.getElementById("message-container");

    if (urlParams.has("WrongPassword")) {
        showMessage("Incorrect password. Please try again.", "danger");
    } else if (urlParams.has("NotFound")) {
        showMessage("Email not found. Please register first.", "danger");
    }

    function showMessage(message, type) {
        messageContainer.innerHTML = `
            <div class="alert alert-${type} alert-dismissible fade show" role="alert">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>`;
    }
});
