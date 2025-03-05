/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.iti.models;
import com.iti.database.psql.PSQL_ENUM;

/**
 *
 * @author theda
 */

public enum MSG_STATUS implements PSQL_ENUM {
    sent("sent"),
    failed("failed");

    private final String value;

    MSG_STATUS(String value) {
        this.value = value;
    }

    @Override
    public String getEnumValue() {
        return value;
    }
    
    
}

