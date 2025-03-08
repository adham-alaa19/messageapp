/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.models.messages;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author theda
 */
public class Message {

    private Integer msg_id;
    private String msg_to;
    private String msg_from;
    private String body;
    private MSG_STATUS msg_status;
    private Integer customer_id;
    private Timestamp msg_date;
    private Time msg_time;

    public Message() {
    }

    public Message(String msg_from, String msg_to, String body) {
        this.msg_from = msg_from;
        this.msg_to = msg_to;
        this.body = body;
    }

    public Message(String msg_from, String msg_to, String body, String status) {
        this.msg_from = msg_from;
        this.msg_to = msg_to;
        this.body = body;
        this.msg_status = MSG_STATUS.valueOf(status);
    }
    
    public Message(String msg_from, String msg_to, String body, String status,int cid) {
        this.msg_from = msg_from;
        this.msg_to = msg_to;
        this.body = body;
        this.msg_status = MSG_STATUS.valueOf(status);
        this.customer_id=cid;
    }

    public Integer getMsg_id() {
        return msg_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public String getMsg_to() {
        return msg_to;
    }

    public String getMsg_from() {
        return msg_from;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getMsg_date() {
        return msg_date;
    }

    public Time getMsg_time() {
        return msg_time;
    }

    public String getMsg_status() {
        if(msg_status==null) return null;
        return msg_status.getEnumValue();
    }

    // Setters
    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public void setMsg_to(String msg_to) {
        this.msg_to = msg_to;
    }

    public void setMsg_from(String msg_from) {
        this.msg_from = msg_from;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setMsg_date(Timestamp msg_date) {
        this.msg_date = msg_date;
    }

    public void setMsg_status(String msg_status) {
        this.msg_status = MSG_STATUS.valueOf(msg_status);
    }

    public void setMsg_Time(Time msg_time) {
        this.msg_time = msg_time;
    }
}
