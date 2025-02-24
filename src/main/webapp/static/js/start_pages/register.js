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
 

