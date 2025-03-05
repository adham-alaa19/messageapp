/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers.users;

import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.PSQL_Handler;
import com.iti.exceptions.CustomerNotFoundException;
import com.iti.models.Customer;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author theda
 */
public class CustomerManager {

   
    public String getCustomerPhone(String mail) {
        DB_Handler dbHandler = new PSQL_Handler();
        Customer customer = null;
        String CustomerPhone = null;
        dbHandler.connect();
        List<Customer> customerList = (List<Customer>) dbHandler.readByValue(Customer.class, "email", SQL_Condition.EQUAL, mail);
        if (!customerList.isEmpty()) {
            customer = customerList.get(0);
        }
        if (customer != null) {
            CustomerPhone = customer.getMsisdn();
        }
        dbHandler.disconnect();
        return CustomerPhone;
    }
    
    
    
        public Customer updateCustomer(int cid,String firstName, String lastName, Date birthDate,
            String email,  String job,
            String governorate, String district, String street,
            int buildingNo, String msisdn) throws CustomerNotFoundException  {
        if (!customerIdExists(cid)) {
            throw new CustomerNotFoundException("User Not Found ");
        }
        Customer updatedCustomer = new Customer(firstName, lastName, birthDate, email, job,
                governorate, district, street, buildingNo, msisdn);
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        Customer newCustomer = dbHandler.updateByValue(updatedCustomer, "customer_id", SQL_Condition.EQUAL,cid);
        dbHandler.disconnect();
        return updatedCustomer;
    }

    public Customer verifyCustomer(Customer customer) {
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        customer.setIs_valid(true);
        customer = (Customer) dbHandler.updateByValue(customer, "customer_id", SQL_Condition.EQUAL, customer.getId());
        dbHandler.disconnect();
        return customer;
    }

    public boolean isCustomerVerfied(Customer customer) {
        return customer.is_valid();
    }
    
        public boolean customerMailExists(String mail) {
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        List<Customer> customerList;
        customerList = (List<Customer>) dbHandler.readByValue(Customer.class, "email", SQL_Condition.EQUAL, mail);
        dbHandler.disconnect();
        return !customerList.isEmpty();
    }
        
       public boolean customerIdExists(int id) {
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        List<Customer> customerList;
        customerList = (List<Customer>) dbHandler.readByValue(Customer.class, "customer_id", SQL_Condition.EQUAL, id);
        dbHandler.disconnect();
        return !customerList.isEmpty();
    }

}
 