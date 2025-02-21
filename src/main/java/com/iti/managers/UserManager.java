package com.iti.managers;


import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.Psql_Handler;
import com.iti.models.IUser;
import com.iti.models.Customer;
import com.iti.models.Admin;


import java.util.List;
import java.util.Map;

public class UserManager {

    public IUser getUser2(String mail, String password) {
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
    
    
     public IUser getUser(String mail, String password) {
        DB_Handler dbHandler = new Psql_Handler();
        IUser user=null ;
        List<Customer> userList=null;
        List<Admin> adminList=null;
        dbHandler.connect();
               userList = (List<Customer>) dbHandler.readByValue(Customer.class, "email", SQL_Condition.EQUAL, mail);   
               if (!userList.isEmpty() )
               {if(userList.get(0).getPassword().equals(password))  user=userList.get(0);}
               else{
               adminList = (List<Admin>) dbHandler.readByValue(Admin.class, "email", SQL_Condition.EQUAL, mail);
               if (!adminList.isEmpty()  )
               if(adminList.get(0).getPassword().equals(password)) 
                   user=adminList.get(0);
               }
         dbHandler.disconnect();
         return user;
    }
     
     
         public void createUser(IUser user) {
             DB_Handler dbHandler = new Psql_Handler();
             dbHandler.connect();
             dbHandler.create(user);
             dbHandler.disconnect();
    }



    public boolean isAdmin(IUser user) {
        return user instanceof Admin;
    }
    
}
