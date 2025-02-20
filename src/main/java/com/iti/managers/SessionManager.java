/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.managers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author theda
 */
public class SessionManager {
    
         public static boolean isLoggedIn(HttpServletRequest request){
           HttpSession mySession = request.getSession(false);
     if(mySession==null )
         return false;
     else return true; }
    
}
