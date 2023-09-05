package com.example.demo.Package;

import com.example.demo.Repo.LawyerRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Service.LawyerService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {
    @Autowired
    HomeController home;
    @Autowired
    UserService userService;
    @Autowired
    LawyerService lawyerService;
    @RequestMapping("/login")
    public String loginpageCon(){
        return "LogIn";
    }
    @PostMapping("/requestlogin")
    public String loginCon(
            @RequestParam("email")String email,
            @RequestParam("Password")String password,
            @RequestParam("type")String type){
    int lenU=userService.getAllUsers().size();
    int lenL=lawyerService.getAllLawyers().size();
    if(type.equals("user")){
        for(int i=0;i<lenU;i++){
            if(email.equals(userService.getUserById(i).getEmail()) && password.equals(userService.getUserById(i).getPassword())){
                home.database.user_mode=true;
                home.database.lawyer_mode=false;
                HomeController.current_user=i;
                return "UserHome";
            }
        }
    }
    else if(type.equals("lawyer")){
        for(int i=0;i<lenL;i++){
            if(email.equals(lawyerService.getLawyerById(i).getEmail()) && password.equals(lawyerService.getLawyerById(i).getLpassword())){
                home.database.lawyer_mode=true;
                home.database.user_mode=false;
                HomeController.current_user=i;
                return "LawyerHome";
            }
        }
    }

        return "LogIn";
    }

}
