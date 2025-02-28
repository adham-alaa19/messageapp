/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const sendOtpBtn = document.getElementById("sendOtpBtn");
const emailInput = document.getElementById("email");
const otpMailInput = document.getElementById("otpmail");
const otpForm = document.getElementById("otpForm");

sendOtpBtn.addEventListener("click", function () {
    const email = emailInput.value.trim();
    
    console.log(otpMailInput)

    if (!email) {
        console.log("No email entered. Showing modal.");
        const emailAlertModalEl = document.getElementById("emailAlertModal");

        if (emailAlertModalEl) {
            const emailAlertModal = new bootstrap.Modal(emailAlertModalEl);
            emailAlertModal.show();
        } else {
            console.error("Modal not found in the document.");
        }
        return;
    }

    console.log("Email entered:", email);
    otpMailInput.value = email;
    otpForm.submit();
});

