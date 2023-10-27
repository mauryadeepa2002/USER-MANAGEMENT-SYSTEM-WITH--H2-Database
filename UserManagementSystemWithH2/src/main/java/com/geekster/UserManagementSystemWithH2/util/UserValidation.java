package com.geekster.UserManagementSystemWithH2.util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

    public static List<String> checkValidation(JSONObject json){

        List<String>errors = new ArrayList<>();

        if(json.getString("userName").length()==0){
            errors.add("userName : user_name is mandatory");
        }

        if(!json.has("userId")){
            errors.add("userId : user_id is mandatory");
        }

        if(json.getString("email").length()==0){
            errors.add("email : email is mandatory");
        }else if(!UserValidation.isValidEmail(json.getString("email"))){
            errors.add("email : Please provide valid email");
        }
        if((!json.has("phone_number")) || json.getString("phone_number").length()!=12 ||(json.getString("phone_number").charAt(0)!='9'
                ||(json.getString("phone_number").charAt(1)!='1'))){
            errors.add("phone_number : phone_number having 12 digits is mandatory starting with 91");
        }else if(!isValidMobileNo(json.getString("phone_number").substring(2,12))){
            errors.add("phone_number : Please provide valid phone_number");
        }

        if(!json.has("date_of_birth")){
            errors.add("date_of_birth : date_of_birth is mandatory");
        }
        String dob = json.getString("date_of_birth");
        if(dob.charAt(2)!='-' || dob.charAt(5)!='-' || dob.length()!=10){
            errors.add("date_of_birth : date_of_birth should be in dd-mm-yyyy format ");
        }
        return errors;
    }


    public static boolean isValidEmail(String value){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        String out = ((matcher.matches() ? "valid" : "invalid"));
        return out.equals("valid");
    }

    public static boolean isValidMobileNo(String str)
    {
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");

        Matcher match = ptrn.matcher(str);

        return (match.find() && match.group().equals(str));
    }
}
