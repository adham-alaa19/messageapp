/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.iti.api;

import com.iti.models.messages.MessageApiInfo;

/**
 *
 * @author theda
 */
public interface MessageApiHandler {
    
    public boolean sendMessage(MessageApiInfo msgInfo,String to , String body);
    
}
