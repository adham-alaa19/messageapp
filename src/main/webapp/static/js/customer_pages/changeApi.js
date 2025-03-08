 document.addEventListener("DOMContentLoaded", function () {
    const apiSelect = document.getElementById("apiSelect");
    const apiContainer = document.querySelector(".api-container");

    apiContainer.addEventListener("click", function () {
        if (apiSelect.disabled) {
            window.location.href = "addApiInfo";
        }
    });

    apiSelect.addEventListener("change", function () {
        const selectedApi = apiSelect.value;
        if (!selectedApi || selectedApi === "addApi") return; 
        console.log("GOOOOT HHHHHHERERERE");
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
            location.reload(true); 
        })
        .catch(error => {
            console.error("Error:", error);
        });
    });
});
