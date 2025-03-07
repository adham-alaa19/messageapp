 document.addEventListener("DOMContentLoaded", function () {
    const apiSelect = document.getElementById("apiSelect");
    const apiContainer = document.querySelector(".api-container"); // Wrapper div

    apiContainer.addEventListener("click", function () {
        if (apiSelect.disabled) {
            window.location.href = "addApiInfo"; // Redirect to add API page when disabled
        }
    });

    apiSelect.addEventListener("change", function () {
        const selectedApi = apiSelect.value;
        if (!selectedApi || selectedApi === "addApi") return; // Ignore if no selection

        fetch("changeApi", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `apiName=${encodeURIComponent(selectedApi)}`
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to switch API");
            }
            return response.text();
        })
        .then(data => {
            console.log("API changed successfully:", data);
            location.reload(); // Reload page to apply changes
        })
        .catch(error => {
            console.error("Error:", error);
        });
    });
});
