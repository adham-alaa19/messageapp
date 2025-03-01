/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.managers;



import com.iti.models.Customer;
import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.PSQL_Handler;
import java.util.List;
import java.util.Map;

public class AdminManager {
    
    private final DB_Handler dbh;
    
    public AdminManager() {
        dbh = new PSQL_Handler();
    }
    
    // Get All Users
    public List<Customer> getAllUsers() {
        dbh.connect();
        List<Customer> users = (List<Customer>) dbh.readAll(Customer.class);
        dbh.disconnect();
        return users;
    }

    // Get Single User
    public Customer getUser(int id) {
        dbh.connect();
        List<Customer> users = (List<Customer>) dbh.readByValue(Customer.class, "id", SQL_Condition.EQUAL, id);
        dbh.disconnect();
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    // Add New User
    public Customer addUser(Customer user) {
        dbh.connect();
        Customer result = dbh.create(user);
        dbh.disconnect();
        return result;
    }

    // Update User
    public Customer updateUser(Customer user) {
        dbh.connect();
        Customer result = dbh.updateByValue(user, "id", SQL_Condition.EQUAL, user.getId());
        dbh.disconnect();
        return result;
    }

    // Delete User
    public boolean deleteUser(int id) {
        dbh.connect();
        boolean result = dbh.deleteByValue(Customer.class, "id", SQL_Condition.EQUAL, id);
        dbh.disconnect();
        return result;
    }
    // Get message statistics for a user
public int getMessageCount(int userId) {
    dbh.connect();
    String query = "SELECT COUNT(*) AS message_count FROM messages WHERE user_id = " + userId;
    List<Map<String, Object>> result = dbh.executeSelectQuery(query, Customer.class);
    dbh.disconnect();
    if (!result.isEmpty()) {
        return (int) result.get(0).get("message_count");
    }
    return 0;
}

}