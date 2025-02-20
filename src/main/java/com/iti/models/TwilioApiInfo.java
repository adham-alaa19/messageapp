 package com.iti.models;

public class TwilioApiInfo implements MessageApiInfo {
    private int api_id;
    private String api_name;
    private String api_key;
    private String api_secret;
    private String sender_id;

    @Override
    public int getApi_id() {
        return api_id;
    }

    @Override
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Override
    public String getApi_name() {
        return api_name;
    }

    @Override
    public void setApi_name(String api_name) {
        this.api_name = api_name;
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
}
