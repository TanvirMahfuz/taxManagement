package com.example.demo.JavaClasses;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataBase {
    List<User> userList = new ArrayList<>();

    public void addUser(User user){
        userList.add(user);
    }
    public List<User> getUserList(){
        return userList;
    }
    public boolean user_mode=false;

    List<Lawyer> lawyerList= new ArrayList<>();

    public void addLawyer(Lawyer lawyer)
    {
        lawyerList.add(lawyer);
    }
    public List<Lawyer> getLawyerList(){
        return lawyerList;
    }

    public boolean lawyer_mode=false;

}
