package com.iti.pages.admin;

import com.iti.exceptions.CustomerNotFoundException;
import com.iti.managers.session.SessionManager;
import com.iti.managers.users.CustomerManager;
import com.iti.models.users.Customer;
import com.iti.utils.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EditCustomer", urlPatterns = {"/app/admin/edit_customer"})
public class EditCustomerAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CustomerManager customeManager = new CustomerManager();

        Customer customer = customeManager.getCustomerByPubid(request.getParameter("uid"));

        request.setAttribute("customer", customer);

        request.getRequestDispatcher("../../pages/admin_pages/editCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String pubid = request.getParameter("uid");
        String birthDateString = request.getParameter("birthdate");
        String email = request.getParameter("email");
        String job = request.getParameter("job");
        String phone = request.getParameter("phone");
        String governorate = request.getParameter("governorate");
        String district = request.getParameter("district");
        String street = request.getParameter("street");
        String buildingNoStr = request.getParameter("building");
        List<String> errors = UserValidator.validateUpdate(firstName, lastName, birthDateString,
                email, job, governorate, district, street, buildingNoStr, phone);

        if (!errors.isEmpty()) {
            response.sendRedirect("editCustomer.jsp?" + String.join("&", errors));
            return;
        }

        Date birthDate = null;
        try {
            birthDate = java.sql.Date.valueOf(birthDateString);
        } catch (IllegalArgumentException e) {
            // Should not occur if validation passed
        }
        int buildingNo = 1;
        try {
            buildingNo = Integer.parseInt(buildingNoStr);
        } catch (NumberFormatException e) {
            // Should not occur if validation passed
        }
        CustomerManager customerManager = new CustomerManager();
        try {
            System.out.println("HERERERERER =___="+pubid);
            Customer customer = customerManager.getCustomerByPubid(pubid);
            customerManager.updateCustomer(customer.getId(), firstName, lastName, birthDate, email,
                    job, governorate, district, street, buildingNo, phone);
            response.sendRedirect("viewusers");
        } catch (CustomerNotFoundException ex) {
            response.sendRedirect("edit_customer?NotFound=True");
        }
    }

}
