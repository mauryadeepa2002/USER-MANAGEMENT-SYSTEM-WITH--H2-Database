package com.geekster.UserManagementSystemWithH2.service;

import com.geekster.UserManagementSystemWithH2.model.User;
import com.geekster.UserManagementSystemWithH2.repository.IUserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class UserService implements IUserInterface{
    @Autowired
    IUserRepository studentRepository;


    public void addUser(User user) {
        user.setDate(new Date());
        user.setTime(new Timestamp(System.currentTimeMillis()));
        studentRepository.save(user);
//        return "added";
    }

    public List<User> getAllUsers() {
        return studentRepository.findAll();
    }

    public User set(JSONObject user) {
        User newUser = new User();
        newUser.setUserId(user.getInt("userId"));
        newUser.setUserName(user.getString("userName"));
        newUser.setDate_of_birth(user.getString("date_of_birth"));
        newUser.setEmail(user.getString("email"));
        newUser.setPhone_number(user.getString("phone_number"));
        return newUser;
    }

    public List<User> getUser(Integer id) {
        List<Integer>ids = new ArrayList<>();
        ids.add(id);
        return studentRepository.findAllById(ids);
    }


    public List<String> deleteById(Integer id) {

        List<Integer>ids = new ArrayList<>();
        List<String>forChecking = new ArrayList<>();
        ids.add(id);
        List<User>users =  studentRepository.findAllById(ids);
        if(users.isEmpty()){
            return forChecking;
        }else{
            studentRepository.deleteById(id);
            forChecking.add("User deleted");
            return forChecking;
        }



    }


}
