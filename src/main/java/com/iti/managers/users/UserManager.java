package com.iti.managers.users;

import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.PSQL_Handler;
import com.iti.models.IUser;
import com.iti.models.Customer;
import com.iti.models.Admin;
import java.util.List;
import com.iti.exceptions.UserAlreadyExistsException;
import java.sql.Date;

public class UserManager {

    public IUser getUser(String mail) {
        DB_Handler dbHandler = new PSQL_Handler();
        IUser user = null;
        dbHandler.connect();
        List<Customer> customerList = (List<Customer>) dbHandler.readByValue(Customer.class, "email", SQL_Condition.EQUAL, mail);
        if (!customerList.isEmpty()) {
            user = customerList.get(0);
        } else {
            List<Admin> adminList = (List<Admin>) dbHandler.readByValue(Admin.class, "email", SQL_Condition.EQUAL, mail);
            if (!adminList.isEmpty()) {
                user = adminList.get(0);
            }
        }
        dbHandler.disconnect();
        return user;
    }

    public boolean checkUserPassword(IUser user, String password) {
        return user.getPassword().equals(password);
    }

    public Customer createCustomer(String firstName, String lastName, Date birthDate,
            String email, String password, String job,
            String governorate, String district, String street,
            int buildingNo, String msisdn) throws UserAlreadyExistsException {
        if (userExists(email)) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists.");
        }
        Customer customer = new Customer(firstName, lastName, birthDate, email, password, job,
                governorate, district, street, buildingNo, msisdn);
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        Customer newCustomer = dbHandler.create(customer);
        dbHandler.disconnect();
        return newCustomer;
    }

    public boolean userExists(String mail) {
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        List<Customer> customerList;
        customerList = (List<Customer>) dbHandler.readByValue(Customer.class, "email", SQL_Condition.EQUAL, mail);
        dbHandler.disconnect();
        return !customerList.isEmpty();
    }

    public boolean createUser(IUser user) {
        DB_Handler dbHandler = new PSQL_Handler();
        dbHandler.connect();
        dbHandler.create(user);
        dbHandler.disconnect();
        return true;
    }

    public boolean isAdmin(IUser user) {
        return user instanceof Admin;
    }

}
