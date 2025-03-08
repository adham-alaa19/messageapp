/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.models.messages;

import com.iti.api.API_ENUM;

/**
 *
 * @author theda
 */

public class Api_Info implements MessageApiInfo {
    private Integer api_id;
    private String api_code;
    private String api_key;
    private String api_secret;
    private String sender_id;
    private Integer customer_id;
    
    
    
    
    public Api_Info(){
        
    }
    
    
    public Api_Info(String api_code,String api_key,String api_secret,String sender_id, Integer customer_id){
     this.api_code=api_code;
     this.api_key=api_key;
     this.api_secret=api_secret;
     this.sender_id=sender_id;
     this.customer_id=customer_id;
   
    }
    
    

    @Override
    public int getApi_id() {
        return api_id;
    }

    @Override
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Override
    public String getApi_code() {
        return api_code;
    }

    @Override
    public void setApi_code(String api_code) {
        this.api_code = api_code;
    }

    @Override
    public String getApi_key() {
        return api_key;
    }

    @Override
    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    @Override
    public String getApi_secret() {
        return api_secret;
    }

    @Override
    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }

    @Override
    public String getSender_id() {
        return sender_id;
    }

    @Override
    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
    
    
    @Override
    public int getCustomer_id() {
        return customer_id;
    }

    @Override
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}

