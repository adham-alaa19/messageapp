 <%@ page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-lg p-4" style="width: 100%; max-width: 500px;">
        <div class="card-header">
            <h3 class="text-center mb-2">Send Message</h3>
        </div>
        <form action="send" method="post">
            <div class="card-body">
                <!-- Sender ID (Styled Input but Read-Only) -->
                <div class="mb-1">
                    <label for="from" class="form-label">From</label>
                    <input type="text" class="form-control from" id="from" value="<%= request.getAttribute("senderId") %>" readonly>
                </div>
                
                <div class="mb-1">
                    <label for="to" class="form-label">To</label>
                    <input type="text" name="to" class="form-control" id="to" placeholder="Recipient ..." required>
                </div>

                <!-- Message Body -->
                <div class="mb-1">
                    <label for="body" class="form-label">Body</label>
                    <textarea name="body" class="form-control" id="body" rows="4" placeholder="Write your message here..." required></textarea>
                </div>
            </div>
            <br>
            
            <!-- Send Button -->
            <div class="d-grid">
                <button type="submit" class="btn btn-primary btn-sm">
                    <i class="bi bi-paper-plane"></i> Send
                </button>
            </div>
        </form>
        <div id="message-container" class="mt-3"></div>
    </div>
</div>
