/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers.messages;

import com.iti.api.MessageApiHandler;
import com.iti.api.TwilioApiHandler;
import com.iti.models.MessageApiInfo;

public class SendManager {

    public boolean sendMessage(String from, String to, String body) {
        System.out.println("Got in send");
        saveMessage(from, to, body, true);
        return true;
    }

    public boolean sendMessageByID(String uid, String to, String body) {

        return true;
    }

    public boolean saveAndSendMessage(MessageApiInfo msgInfo, String to, String body) {
        boolean isSent = sendSmsMessage(msgInfo, to, body);
        boolean isSaved = saveMessage(msgInfo.getSender_id(), to, body, isSent);
        return isSent && isSaved;
    }

    public boolean saveMessage(String from, String to, String body, boolean isSent) {
        String status = isSent? "sent":"failed" ;
        System.out.println(status);
        MessageManager messagemanager = new MessageManager();
        System.out.println("Got in save");
        messagemanager.createMessage(from,to, body,status);
        return true;
    }

    public boolean sendSmsMessage(MessageApiInfo msgInfo, String to, String body) {
        MessageApiHandler msgApiHandler = new TwilioApiHandler();
        return msgApiHandler.sendMessage(msgInfo, to, body);
    }

}
