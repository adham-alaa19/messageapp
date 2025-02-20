package com.iti.managers;


import com.iti.database.DB_Handler;
import com.iti.database.psql.Psql_Handler;
import com.iti.models.IUser;
import com.iti.models.Customer;
import com.iti.models.Admin;


import java.util.List;
import java.util.Map;

public class UserManager {

    public IUser getUser(String mail, String password) {
        DB_Handler dbHandler = new Psql_Handler();
        dbHandler.connect();
        try {
            String customerQuery = "SELECT * FROM customer WHERE email = '" + mail 
                                   + "' AND password = '" + password + "'";
            List<Map<String, Object>> result = dbHandler.executeSelectQuery(customerQuery,Customer.class);
            if (result != null && !result.isEmpty()) {
                Map<String, Object> row = result.get(0);
                Customer customer = new Customer();
                customer.setId(((Number) row.get("customer_id")).intValue());
                customer.setFirstName((String) row.get("first_name"));
                customer.setLastName((String) row.get("last_name"));
                customer.setEmail((String) row.get("email"));
                customer.setPassword((String) row.get("password"));
                return customer;
            }

            String adminQuery = "SELECT * FROM admin WHERE email = '" + mail 
                                + "' AND password = '" + password + "'";
            result = dbHandler.executeSelectQuery(adminQuery,Admin.class);
            if (result != null && !result.isEmpty()) {
                Map<String, Object> row = result.get(0);
                Admin admin = new Admin();
                admin.setId(((Number) row.get("admin_id")).intValue());
                admin.setFirstName((String) row.get("first_name"));
                admin.setLastName((String) row.get("last_name"));
                admin.setEmail((String) row.get("email"));
                admin.setPassword((String) row.get("password"));
                return admin;
            }
        } finally {
            dbHandler.disconnect();
        }
        return null;
    }


    public boolean isAdmin(IUser user) {
        return user instanceof Admin;
    }
    
}
