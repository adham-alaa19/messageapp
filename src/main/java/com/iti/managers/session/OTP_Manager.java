package com.iti.managers.session;

import com.iti.managers.messages.ApiInfoManager;
import com.iti.managers.messages.SendManager;
import com.iti.models.messages.Api_Info;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;

public class OTP_Manager {

  
    private final int apiId;
    public OTP_Manager()
    {
        this(0);
    }
      public OTP_Manager(int apiId)
    {
        this.apiId=apiId;
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

    public void storeAndSendOTP(HttpServletRequest request,String mail) {
        HttpSession session = request.getSession(true);
        String otp =generateOTP(4);
        session.setAttribute("otp", otp);
        session.setAttribute("otp_expiry", generateExpiryTime(2));
        SendManager sender = new SendManager();
        ApiInfoManager apiManager = new ApiInfoManager();
        Api_Info apiInfo = apiManager.getApiInfoById(apiId);
        sender.sendApiMessage(apiInfo, mail, otp);
        
    }

    public void clearOTP(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("otp");
            session.removeAttribute("otp_expiry");
        }
    }
    
    public String getOtpType()
    {
        switch (apiId) {
            case 0:
                return "mail";
            case 1:
                return "phone";
            default:
                return "unsupported";
        }

    }
}
