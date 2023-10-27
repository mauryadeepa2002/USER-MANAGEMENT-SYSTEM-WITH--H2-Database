package com.geekster.UserManagementSystemWithH2.controller;

import com.geekster.UserManagementSystemWithH2.model.User;
import com.geekster.UserManagementSystemWithH2.service.IUserInterface;
import com.geekster.UserManagementSystemWithH2.util.UserValidation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/api/v1/user-management")

public class UserController {

    @Autowired
    IUserInterface UserInterface;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody String user){
        JSONObject json = new JSONObject(user);
        List<String>errors = UserValidation.checkValidation(json);
        List<User>userAlreadyPresent = UserInterface.getUser(json.getInt("userId"));
        if(!userAlreadyPresent.isEmpty()){
            return new ResponseEntity<>("User already Exists having user_id "+json.getInt("userId"), HttpStatus.BAD_REQUEST);
        }
        else
        if(errors.isEmpty()){
            User newUser = UserInterface.set(json);
            UserInterface.addUser(newUser);

            return new ResponseEntity<>("Validated and added", HttpStatus.CREATED);
        }else{
            String[] answer = Arrays.copyOf(
                    errors.toArray(), errors.size(), String[].class);

            return new ResponseEntity<>("These parameters are mandatory to send -> "+ Arrays.toString(answer), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAll_users")
    public List<User> getAll(){
        return UserInterface.getAllUsers();
    }

    @GetMapping("/get_User_By_Id")
    public List<User> getUserById(@RequestParam("userId") Integer id ){
        return UserInterface.getUser(id);
    }

    @PutMapping("/update_user_info")
    public ResponseEntity<String> updateUserInfo(@RequestBody String user){
        JSONObject json = new JSONObject(user);
        List<User> validations = UserInterface.getUser(json.getInt("userId"));
        if(validations.isEmpty()){
            return new ResponseEntity<>("User with user_id "+ json.getInt("userId")+" not found", HttpStatus.BAD_REQUEST);
        }
        List<String>errors = UserValidation.checkValidation(json);
        if(errors.isEmpty()){
            User newUser = UserInterface.set(json);
            UserInterface.addUser(newUser);
            return new ResponseEntity<>("Validated and updated", HttpStatus.CREATED);
        }else{
            String[] answer = Arrays.copyOf(
                    errors.toArray(), errors.size(), String[].class);
            return new ResponseEntity<>("These valid parameters are mandatory to send -> "+Arrays.toString(answer), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_user_with_id")
    public ResponseEntity<String> deleteUser(@RequestParam("userId") Integer id){
        List<String>check = UserInterface.deleteById(id);
        if(check.isEmpty()){
            return new ResponseEntity<>("No user found with id = "+id, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("Validated and deleted", HttpStatus.OK);
        }
    }
}
