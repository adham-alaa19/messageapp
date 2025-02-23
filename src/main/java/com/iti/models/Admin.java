 package com.iti.models;

import java.util.UUID;

public class Admin implements IUser {
    private int admin_id;
    private UUID admin_pub_id;
    private String first_name;
    private String last_name;
    private String password;
    private String email;

    // Getters and Setters
    @Override
    public int getId() { return admin_id; }
    public void setId(int admin_id) { this.admin_id = admin_id; }

    public UUID getAdmin_pub_id() { return admin_pub_id; }
    public void setAdmin_pub_id(UUID admin_pub_id) { this.admin_pub_id = admin_pub_id; }

    @Override
    public String getFirstName() { return first_name; }
    public void setFirstName(String first_name) { this.first_name = first_name; }

    @Override
    public String getLastName() { return last_name; }
    public void setLastName(String last_name) { this.last_name = last_name; }

    @Override
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
