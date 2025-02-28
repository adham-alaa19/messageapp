/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
 function toggleCollapse(element,sectionId) {
    let collapseElement = document.getElementById(sectionId);
    let bsCollapse = new bootstrap.Collapse(collapseElement, {
        toggle: false
    });
    let icon = document.getElementById(`icon-${sectionId}`);
    if (collapseElement.classList.contains('show')) {
        bsCollapse.hide();
        if (icon) {
            icon.classList.remove('rotate-icon');
        }
    } else {
        bsCollapse.show();
        if (icon) {
            icon.classList.add('rotate-icon');
        }
    }
}


 document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (event) {
        let isFormValid = true;

        form.querySelectorAll("input").forEach(input => {
            if (!validateField(input)) {
                isFormValid = false;
            }
        });

        if (!isFormValid) {
            event.preventDefault();
            event.stopPropagation();
            displayErrorSummary();
            return;
        }
    });

    form.querySelectorAll("input").forEach(input => {
        input.addEventListener("input", function () {
            validateField(input);
            hideErrorSummary();
        });
    });
});

function validateField(input) {
    let value = input.value.trim();
    let isValid = true;
    let errorText = "";

    if (input.id === "firstName" || input.id === "lastName") {
        isValid = /^[A-Za-z\s]+$/.test(value);
        if (!isValid) errorText = "Only letters allowed.";
    }
    else if (input.id === "job" || input.id === "city" || input.id === "district" || input.id === "street") {
        isValid = /^[A-Za-z\s]+$/.test(value);
        if (!isValid) errorText = "Only letters allowed.";
    }
    else if (input.id === "building") {
        let num = parseInt(value, 10);
        isValid = !isNaN(num) && num >= 1 && num <= 3000;
        if (!isValid) errorText = "Enter a valid building number (1-3000).";
    }
    else if (input.id === "dateOfBirth") {
        isValid = validateBirthDate(value);
        if (!isValid) errorText = "You must be between 15 and 100 years old.";
    }
    else if (input.id === "email") {
        isValid = validateEmail(value);
        if (!isValid) errorText = "Invalid email address.";
    }
    else if (input.id === "password") {
        isValid = value.length >= 8;
        if (!isValid) errorText = "Minimum 8 characters required.";
    }
    else if (input.id === "confirmPassword") {
        isValid = value === document.getElementById("password").value;
        if (!isValid) errorText = "Passwords do not match.";
    }
    else if (input.id === "phone") {
        isValid = /^\+?[0-9]{10,15}$/.test(value);
        if (!isValid) errorText = "Invalid phone number.";
    }

    const errorDiv = document.getElementById("error-" + input.id);

    if (!isValid) {
        input.classList.remove("is-valid");
        input.classList.add("is-invalid");
        if (errorDiv) {
            errorDiv.textContent = errorText;
            errorDiv.style.display = "block";
        }
    } else {
        input.classList.remove("is-invalid");
        input.classList.add("is-valid");
        if (errorDiv) {
            errorDiv.textContent = "";
            errorDiv.style.display = "none";
        }
    }
    
    return isValid;
}

function validateBirthDate(date) {
    if (!date) return false;
    const birthDate = new Date(date);
    const today = new Date();
    let age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age >= 15 && age <= 100;
}

function validateEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function displayErrorSummary() {
    let errorSummary = document.getElementById("errorSummary");
    if (!errorSummary) return;
    let errors = [];
    document.querySelectorAll("input").forEach(input => {
        if (!input.checkValidity() || input.classList.contains("is-invalid")) {
            let labelElem = document.querySelector(`label[for="${input.id}"]`);
            let labelText = labelElem ? labelElem.innerText : input.id;
            let errorDiv = document.getElementById("error-" + input.id);
            let errorText = errorDiv ? errorDiv.textContent : "";
            if (errorText === "") {
                errorText = input.validationMessage;
            }
            errors.push("Problem in " + labelText + " " + errorText);
        }
    });
    if (errors.length > 0) {
        errorSummary.innerHTML = "<ul><li>" + errors.join("</li><li>") + "</li></ul>";
        errorSummary.classList.remove("d-none");
    } else {
        errorSummary.innerHTML = "";
        errorSummary.classList.add("d-none");
    }
}

function hideErrorSummary() {
    let errorSummary = document.getElementById("errorSummary");
    if (errorSummary) {
        errorSummary.innerHTML = "";
        errorSummary.classList.add("d-none");
    }
}
