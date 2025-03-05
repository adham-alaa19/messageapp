/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.querySelectorAll(".otp-input").forEach((input, index, arr) => {
    input.addEventListener("input", () => {
        if (input.value && index < arr.length - 1) {
            arr[index + 1].focus();
        }
    });
});
