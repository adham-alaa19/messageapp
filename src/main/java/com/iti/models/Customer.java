package com.iti.models;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class Customer implements IUser {
    private Integer customer_id;
    private UUID customer_pub_id;
    private String first_name;
    private String last_name;
    private Date birth_date;
    private String password;
    private String job;
    private String email;
    private Address address;
    private String msisdn;
    //private List<MessageApiInfo> apiInfos; 
    private Boolean is_valid;
    
    
    public Customer(){
        
    }
 
    
    public Customer(String first_name, String last_name, Date birth_date, String email, String password, String job, 
                String governorate, String district, String street, int building_no, String msisdn) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.birth_date = birth_date;
    this.email = email;
    this.password = password;
    this.job = job;
    this.address = new Address(governorate, district,street, building_no);
    this.msisdn = msisdn;
    this.is_valid = false; 
}


    
    public int getId() { return customer_id; }
    public void setId(int customer_id) { this.customer_id = customer_id; }

    public UUID getCustomer_pub_id() { return customer_pub_id; }
    public void setCustomer_pub_id(UUID customer_pub_id) { this.customer_pub_id = customer_pub_id; }

    @Override
    public String getFirstName() { return first_name; }
    public void setFirstName(String first_name) { this.first_name = first_name; }

    @Override
    public String getLastName() { return last_name; }
    public void setLastName(String last_name) { this.last_name = last_name; }

    public Date getBirth_date() { return birth_date; }
    public void setBirth_date(Date birth_date) { this.birth_date = birth_date; }

    @Override
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    @Override
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public String getMsisdn() { return msisdn; }
    public void setMsisdn(String msisdn) { this.msisdn = msisdn; }

    public boolean isIs_valid() { return is_valid; }
    public void setIs_valid(boolean is_valid) { this.is_valid = is_valid; }
    
//        public List<MessageApiInfo> getApiInfos() {
//        return apiInfos;
//    }
//
//    public void setApiInfos(List<MessageApiInfo> apiInfos) {
//        this.apiInfos = apiInfos;
//    }
}
