package com.iti.utils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class UserValidator {

    private static final RegexValidator ONLY_LETTERS_VALIDATOR = new RegexValidator("^[A-Za-z\\s]+$");
    private static final RegexValidator PHONE_VALIDATOR = new RegexValidator("^\\+?[0-9]{10,15}$");
    private static final RegexValidator PASSWORD_VALIDATOR = new RegexValidator("^.{8,}$"); // Min 8 chars

    public static ArrayList<String> validateUser(String firstName, String lastName, String birthDateString,
                                                 String email, String password, String confirmPassword,
                                                 String job, String governorate, String district, String street,
                                                 String buildingNoStr, String msisdn) {
        ArrayList<String> errors = new ArrayList<>();
       
        if (!validateOnlyLetters(firstName)) errors.add("invalid=firstname");
        if (!validateOnlyLetters(lastName)) errors.add("invalid=lastname");
        if (!validateBirthDate(birthDateString)) errors.add("invalid=birthdate");
        if (!validateEmail(email)) errors.add("invalid=email");
        if (!validatePassword(password)) errors.add("invalid=password");
        if (!password.equals(confirmPassword)) errors.add("invalid=passwordmismatch");
        if (!validateMsisdn(msisdn)) errors.add("invalid=msisdn");
        if (!validateOnlyLetters(governorate)) errors.add("invalid=governorate");
        if (!validateOnlyLetters(district)) errors.add("invalid=district");
        if (!validateOnlyLetters(street)) errors.add("invalid=street");
        if (!validateBuildingNo(buildingNoStr)) errors.add("invalid=buildingno");
//         for(String error : errors)
//         {
//              System.out.println(error);
//         }
        return errors;
    }



public static ArrayList<String> validateUpdate(String firstName, String lastName, String birthDateString,
                                                   String email, String job, String governorate, String district,
                                                   String street, String buildingNoStr, String msisdn) {
        ArrayList<String> errors = new ArrayList<>();

        if (firstName != null && !validateOnlyLetters(firstName)) errors.add("invalid=firstname");
        if (lastName != null && !validateOnlyLetters(lastName)) errors.add("invalid=lastname");
        if (birthDateString != null && !validateBirthDate(birthDateString)) errors.add("invalid=birthdate");
        if (email != null && !validateEmail(email)) errors.add("invalid=email");
        if (msisdn != null && !validateMsisdn(msisdn)) errors.add("invalid=msisdn");
        if (governorate != null && !validateOnlyLetters(governorate)) errors.add("invalid=governorate");
        if (district != null && !validateOnlyLetters(district)) errors.add("invalid=district");
        if (street != null && !validateOnlyLetters(street)) errors.add("invalid=street");
        if (buildingNoStr != null && !validateBuildingNo(buildingNoStr)) errors.add("invalid=buildingno");

        return errors;
    }
 




    private static boolean validateOnlyLetters(String input) {
        return input != null && ONLY_LETTERS_VALIDATOR.isValid(input);
    }

    private static boolean validateEmail(String email) {
        return email != null && EmailValidator.getInstance().isValid(email);
    }

    private static boolean validatePassword(String password) {
        return password != null && PASSWORD_VALIDATOR.isValid(password);
    }

    private static boolean validateMsisdn(String msisdn) {
        return msisdn != null && PHONE_VALIDATOR.isValid(msisdn);
    }

    private static boolean validateBirthDate(String birthDateString) {
        if (birthDateString == null || birthDateString.isEmpty()) return false;
        try {
            LocalDate birthDate = LocalDate.parse(birthDateString); 
            LocalDate today = LocalDate.now();
            int age = Period.between(birthDate, today).getYears();
            return age >= 15 && age <= 100;
        } catch (DateTimeParseException e) {
            return false; 
        }
    }

    private static boolean validateBuildingNo(String buildingNoStr) {
        if (buildingNoStr == null || buildingNoStr.isEmpty()) return false;
        try {
            int buildingNo = Integer.parseInt(buildingNoStr);
            return buildingNo >= 1 && buildingNo <= 3000;
        } catch (NumberFormatException e) {
            return false; 
        }
    }
}
