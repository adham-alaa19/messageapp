/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.models.messages;

/**
 *
 * @author theda
 */
public class Pub_API_INFO {
    
    private Integer api_id;
    private String api_name;
    private String sender_id;
    
    
    
    public Pub_API_INFO(int api_id,String api_name,String sender_id){
     this.api_name=api_name;
     this.sender_id=sender_id;
     this.api_id=api_id;
   
    }
    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
    
    
 
    
}
