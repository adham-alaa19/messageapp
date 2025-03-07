/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.iti.api;

/**
 *
 * @author theda
 */
public enum API_ENUM {
    TWI("twilio"),
    PLI("plivo"),
    NEX("nexmo"),
    BUK("bulk"),
    EML("email");
    private final String apiName;

    API_ENUM(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }

}
