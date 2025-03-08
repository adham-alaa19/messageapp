/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers.session;

import com.iti.models.users.Admin;
import com.iti.models.users.Customer;
import com.iti.models.users.IUser;
import com.iti.models.messages.Pub_API_INFO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    
    public static void setSessionApiList(HttpServletRequest request, List<Pub_API_INFO> apiInfoList) {
        HttpSession mySession = request.getSession(false);
        mySession.setAttribute("apiList", apiInfoList);
        if(apiInfoList.isEmpty())
            mySession.setAttribute("currentApi", -1);
        else 
        {  int firstId = apiInfoList.get(0).getApi_id();
           mySession.setAttribute("currentApi", firstId);
        }

    }
    
        public static void setSessionApi(HttpServletRequest request, int id) {
        HttpSession mySession = request.getSession(false);
        mySession.setAttribute("currentApi", id);
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
        if(mySession==null) return null;
        IUser user = (IUser) mySession.getAttribute("user");
        if (user instanceof Admin) {
            return (Admin) user;
        } else {
            return null;
        }
    }

    public static Customer getSessionCustomer(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        if(mySession==null) return null;
        IUser user = (IUser) mySession.getAttribute("user");
        if ((user instanceof Customer)) {
            return (Customer) user;
        } else {
            return null;
        }
    }
    
    public static List<Pub_API_INFO> getSessionApiInfoList(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        List<Pub_API_INFO> list = (List<Pub_API_INFO>) mySession.getAttribute("apiList");
        return list;
        
    }
    
    
    public static Integer getSessionApiInfo(HttpServletRequest request) {
        HttpSession mySession = request.getSession(false);
        Integer id = (Integer) mySession.getAttribute("currentApi");
        return id;
    }
    
    public static void setSessionAttribute(HttpServletRequest request,String key, Object obj) {
        HttpSession mySession = request.getSession(false);
        mySession.setAttribute(key, obj);
    }
      public static Object getSessionAttribute(HttpServletRequest request,String key) {
        HttpSession mySession = request.getSession(false);
        return mySession.getAttribute(key);
    }
      
     public static void startSession(HttpServletRequest request) {
        request.getSession(true);
    }
    
 

}
