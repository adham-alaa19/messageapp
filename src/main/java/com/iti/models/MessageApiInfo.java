 package com.iti.models;

public interface MessageApiInfo {
    int getApi_id();
    void setApi_id(int api_id);

    String getApi_name();
    void setApi_name(String api_name);

    String getApi_key();
    void setApi_key(String api_key);

    String getApi_secret();
    void setApi_secret(String api_secret);

    String getSender_id();
    void setSender_id(String sender_id);
}
