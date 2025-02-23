/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.iti.models;

/**
 *
 * @author theda
 */
public interface IUser {
    
     int getId();
    void setId(int id);
    String getFirstName();
    void setFirstName(String first_name);

    String getLastName();
    void setLastName(String last_name);

    String getEmail();
    void setEmail(String email);

    String getPassword();
    void setPassword(String password);

    
}
