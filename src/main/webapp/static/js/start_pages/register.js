/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
 function toggleCollapse(element,sectionId) {
    // Get the collapse element
    let collapseElement = document.getElementById(sectionId);
    let bsCollapse = new bootstrap.Collapse(collapseElement, {
        toggle: false
    });

    // Get the corresponding icon
    let icon = document.getElementById(`icon-${sectionId}`);

    // Check if it's currently visible
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
 

