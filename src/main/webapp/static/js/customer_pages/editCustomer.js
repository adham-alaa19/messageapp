 document.addEventListener("DOMContentLoaded", function () {
    function getUrlParams() {
        let params = new URLSearchParams(window.location.search);
        return params.getAll("invalid");
    }

    function showErrors(invalidFields) {
        invalidFields.forEach(field => {
            let input = document.getElementById(field);
            let errorDiv = document.getElementById("error-" + field);
            if (input && errorDiv) {
                input.classList.add("is-invalid");
                errorDiv.textContent = "Invalid " + field.replace(/([A-Z])/g, " $1").toLowerCase();
                errorDiv.style.display = "block";
            }
        });
    }

    let invalidFields = getUrlParams();
    if (invalidFields.length > 0) {
        showErrors(invalidFields);
    }

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

    if (["firstname", "lastname", "job", "governorate", "district", "street"].includes(input.id)) {
        isValid = /^[A-Za-z\s]+$/.test(value);
        if (!isValid) errorText = "Only letters allowed.";
    }
    else if (input.id === "buildingno") {
        let num = parseInt(value, 10);
        isValid = !isNaN(num) && num >= 1 && num <= 3000;
        if (!isValid) errorText = "Enter a valid building number (1-3000).";
    }
    else if (input.id === "birthdate") {
        isValid = validateBirthDate(value);
        if (!isValid) errorText = "You must be between 15 and 100 years old.";
    }
    else if (input.id === "email") {
        isValid = validateEmail(value);
        if (!isValid) errorText = "Invalid email address.";
    }
    else if (input.id === "password") {
        isValid = value.length >= 8;
        if (!isValid) errorText = "Password must be at least 8 characters.";
    }
    else if (input.id === "confirmpassword") {
        let passwordInput = document.getElementById("password");
        isValid = passwordInput && value === passwordInput.value;
        if (!isValid) errorText = "Passwords do not match.";
    }
    else if (input.id === "msisdn") {
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
    let errorSummary = document.getElementById("error-summary");
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
    let errorSummary = document.getElementById("error-summary");
    if (errorSummary) {
        errorSummary.innerHTML = "";
        errorSummary.classList.add("d-none");
    }
}
