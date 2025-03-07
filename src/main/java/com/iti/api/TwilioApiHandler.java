 
package com.iti.api;
import com.iti.models.MessageApiInfo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioApiHandler implements MessageApiHandler {

    @Override
    public boolean sendMessage(MessageApiInfo msgInfo, String to, String body) {

     
        try {
            Twilio.init(msgInfo.getApi_key(), msgInfo.getApi_secret());

            Message message = Message.creator(
                    new PhoneNumber("+"+to),
                    new PhoneNumber("+"+msgInfo.getSender_id()), 
                    body
            ).create();

            return message.getStatus() != Message.Status.FAILED;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
