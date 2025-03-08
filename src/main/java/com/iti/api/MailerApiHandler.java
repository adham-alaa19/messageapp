/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.api;

import com.iti.models.messages.MessageApiInfo;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.exceptions.MailerSendException;

/**
 *
 * @author theda
 */
public class MailerApiHandler implements MessageApiHandler {

    @Override
    public boolean sendMessage(MessageApiInfo msgInfo, String to, String body) {
    Email email = new Email();
    email.setFrom(msgInfo.getApi_key(),msgInfo.getSender_id());
    email.addRecipient("name",to);
    email.setSubject("Email From messaging App");
    
    MailerSend ms = new MailerSend();

    email.setPlain(body);
   // email.setHtml("This is the HTML content");
    ms.setToken(msgInfo.getApi_secret());

    try {

        MailerSendResponse response = ms.emails().send(email);
        System.out.println(response.messageId);
        return true;
    } catch (MailerSendException e) {
        e.printStackTrace();
        return false;
    }

    }
    
}
