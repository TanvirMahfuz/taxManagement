package com.example.demo.Package;

import com.example.demo.JavaClasses.Home;
import com.example.demo.JavaClasses.Lawyer;
import com.example.demo.JavaClasses.User;
import com.example.demo.JavaClasses.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    static int user_index=0;
    static int lawyer_Index=0;
    static int current_user=0;

    @Autowired
    Home home;
    @Autowired
    User user;
    @Autowired
    DataBase database;
    @Autowired
    Lawyer lawyer;
    ModelAndView mv = new ModelAndView();

    @RequestMapping("/PreHome")
    public ModelAndView HomeCon(){
        database.user_mode=false;
        mv.setViewName("PreHome.html");
        return mv;
    }
    @RequestMapping("/")
    public ModelAndView HomeCon1(){
        ModelAndView mv = new ModelAndView("PreHome");
        return mv;
    }
    @RequestMapping("/Home")
    public String Home(){
        if(database.lawyer_mode)
            return "LawyerHome";
        else if(database.user_mode)
            return "UserHome";
        else return "PreHome";

    }
}
