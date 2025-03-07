/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers.messages;

import com.iti.api.BulkApiHandler;
import com.iti.api.MailApiHandler;
import com.iti.api.MessageApiHandler;
import com.iti.api.NexApiHandler;
import com.iti.api.PlivoApiHandler;
import com.iti.api.TwilioApiHandler;
import com.iti.models.Api_Info;

public class SendManager {

    public boolean sendMessage(int apiId, String to, String body) {
        ApiInfoManager apiManager =  new ApiInfoManager();
        Api_Info apiInfo = apiManager.getApiInfoById(apiId);
        return saveAndSendMessage(apiInfo, to, body);
    }

    public boolean saveAndSendMessage(Api_Info msgInfo, String to, String body) {
        boolean isSent = sendApiMessage(msgInfo, to, body);
        boolean isSaved = saveMessage(msgInfo.getSender_id(), to, body, isSent);
        return isSent && isSaved;
    }


    public boolean sendApiMessage(Api_Info msgInfo, String to, String body) {

        MessageApiHandler msgApiHandler = null;
        switch (msgInfo.getApi_code()) {
            case "TWI":
                msgApiHandler = new TwilioApiHandler();
                break;
            case "PLI":
                msgApiHandler = new PlivoApiHandler();
                break;
            case "BUK":
                msgApiHandler = new BulkApiHandler();
                break;
            case "EML":
                msgApiHandler = new MailApiHandler();
                break;
            case "NEX":
                msgApiHandler = new NexApiHandler();
                break;
        }
        return msgApiHandler.sendMessage(msgInfo, to, body);
    }
    
    
    
    public boolean saveMessage(String from, String to, String body, boolean isSent) {
        String status = isSent ? "sent" : "failed";
        System.out.println(status);
        MessageManager messagemanager = new MessageManager();
        messagemanager.createMessage(from, to, body, status);
        return true;
    }

}
