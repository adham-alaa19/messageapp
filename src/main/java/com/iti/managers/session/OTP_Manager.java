package com.iti.managers.session;

import com.iti.managers.messages.SendManager;
import com.iti.models.TwilioApiInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;

public class OTP_Manager {

    private static final TwilioApiInfo API_INFO;

    static {
        API_INFO = new TwilioApiInfo();
        API_INFO.setApi_id(1);
        API_INFO.setApi_name("Twilio");
        API_INFO.setApi_key("ACd1eda764f9e664be3af9b4cca24070be");
        API_INFO.setApi_secret("ab8a744eeb04f143db6d1f95542e6582");
        API_INFO.setSender_id("18789999397");
    }

    public String generateOTP(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    public long generateExpiryTime(int minutes) {
        return System.currentTimeMillis() + (minutes * 60 * 1000);
    }

    public boolean validateOTP(HttpServletRequest request, String userInput) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String storedOTP = (String) session.getAttribute("otp");
        Long expiryTime = (Long) session.getAttribute("otp_expiry");

        if (storedOTP == null || expiryTime == null) {
            return false;
        }

        if (System.currentTimeMillis() > expiryTime) {
            return false;
        }

        return storedOTP.equals(userInput);
    }

    public boolean isExpired(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return true;
        }
        Long expiryTime = (Long) session.getAttribute("otp_expiry");
        if (expiryTime == null) {
            return true;
        }
        return System.currentTimeMillis() > expiryTime;
    }

    public void storeAndSendOTP(HttpServletRequest request,String phone) {
        HttpSession session = request.getSession(true);
        String otp =generateOTP(4);
        session.setAttribute("otp", otp);
        session.setAttribute("otp_expiry", generateExpiryTime(2));
        SendManager sender = new SendManager();
        sender.sendSmsMessage(API_INFO, phone, otp);
        
    }

    public void clearOTP(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("otp");
            session.removeAttribute("otp_expiry");
        }
    }
}
