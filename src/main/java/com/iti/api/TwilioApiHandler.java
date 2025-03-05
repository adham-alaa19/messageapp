 
package com.iti.api;
import com.iti.models.MessageApiInfo;
import com.iti.models.TwilioApiInfo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioApiHandler implements MessageApiHandler {

    @Override
    public boolean sendMessage(MessageApiInfo msgInfo, String to, String body) {
        if (!(msgInfo instanceof TwilioApiInfo)) {
            throw new IllegalArgumentException("Invalid API Info type for Twilio");
        }

        TwilioApiInfo twilioInfo = (TwilioApiInfo) msgInfo;

        try {
            Twilio.init(twilioInfo.getApi_key(), twilioInfo.getApi_secret());

            Message message = Message.creator(
                    new PhoneNumber("+"+to),
                    new PhoneNumber("+"+twilioInfo.getSender_id()), 
                    body
            ).create();

            return message.getStatus() != Message.Status.FAILED;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
