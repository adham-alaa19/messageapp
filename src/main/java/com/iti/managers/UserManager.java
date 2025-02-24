package com.iti.managers;


import com.iti.database.DB_Handler;
import com.iti.database.SQL_Condition;
import com.iti.database.psql.PSQL_Handler;
import com.iti.models.IUser;
import com.iti.models.Customer;
import com.iti.models.Admin;
import java.util.List;
import java.util.Map;

public class UserManager {

     public IUser getUser(String mail) {
        DB_Handler dbHandler = new PSQL_Handler();
        IUser user=null ;
        List<Customer> userList=null;
        List<Admin> adminList=null;
        dbHandler.connect();
               userList = (List<Customer>) dbHandler.readByValue(Customer.class, "email", SQL_Condition.EQUAL, mail);   
               if (!userList.isEmpty() )  user=userList.get(0);
               else{
               adminList = (List<Admin>) dbHandler.readByValue(Admin.class, "email", SQL_Condition.EQUAL, mail);
               if (!adminList.isEmpty()) user=adminList.get(0);
               }
         dbHandler.disconnect();
         return user;
    }
     
     
     public boolean checkUserPassword(IUser user , String password) {
               return user.getPassword().equals(password);
    }
     
     
         public void createUser(IUser user) {
             DB_Handler dbHandler = new PSQL_Handler();
             dbHandler.connect();
             dbHandler.create(user);
             dbHandler.disconnect();
    }


     public  boolean isAdmin(IUser user) {
        return user instanceof Admin;
    }


}
