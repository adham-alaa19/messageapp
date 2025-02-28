/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers.users;

import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.PSQL_Handler;
import com.iti.models.Admin;
import com.iti.models.Customer;
import com.iti.models.IUser;
import java.util.List;

/**
 *
 * @author theda
 */
public class CustomerManager {
    
        public String getCustomerPhone(String mail) {
        DB_Handler dbHandler = new PSQL_Handler();
        Customer customer = null;
        String CustomerPhone=null;
        dbHandler.connect();
        List<Customer> customerList = (List<Customer>) dbHandler.readByValue(Customer.class, "email", SQL_Condition.EQUAL, mail);
        if (!customerList.isEmpty()) {
            customer = customerList.get(0);
        }
        if(customer!=null)
            CustomerPhone = customer.getMsisdn();
        dbHandler.disconnect();
        return CustomerPhone;
    }
    
}
