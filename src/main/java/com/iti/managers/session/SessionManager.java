/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers.session;

import com.iti.models.Admin;
import com.iti.models.Customer;
import com.iti.models.IUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author theda
 */
public class SessionManager {

    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        return mySession != null;
    }

    public static void startSession(HttpServletRequest request, IUser user) {
        HttpSession mySession = request.getSession(true);
        mySession.setAttribute("user", user);
    }

    public static void endSession(HttpServletRequest request) {
        HttpSession htps = request.getSession(false);
        htps.invalidate();
    }

    public static boolean isAdminSession(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        Object user = mySession.getAttribute("user");
        return user instanceof Admin;
    }
    
        public static boolean isCustomerSession(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        Object user = mySession.getAttribute("user");
        return user instanceof Customer;
    }
        
        public static boolean isCustomersSessionVerified(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        Object user = mySession.getAttribute("user");
        return user instanceof Customer;
    }
       
    public static Admin getSessionAdmin(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        IUser user = (IUser) mySession.getAttribute("user");
        if (user instanceof Admin) {
            return (Admin) user;
        } else {
            return null;
        }
    }

    public static Customer getSessionCustomer(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        IUser user = (IUser) mySession.getAttribute("user");
        if ((user instanceof Customer)) {
            return (Customer) user;
        } else {
            return null;
        }
    }

}
