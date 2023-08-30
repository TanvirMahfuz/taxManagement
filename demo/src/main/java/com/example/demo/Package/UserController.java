package com.example.demo.Package;

import com.example.demo.JavaClasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class UserController {
    @Autowired
    HomeController home;
    /*****User Section start*****/
    @RequestMapping("/NewUser")
    public String showUserForm(Model model) {
        System.out.println("-------------new user");
        home.database.user_mode=false;
        model.addAttribute("userInput", home.user);
        return "NewUser";

    }


    @RequestMapping(value = "/submitUser",method = RequestMethod.POST)
    public ModelAndView submitUserForm(@ModelAttribute User user) {

        if(home.database.user_mode==false)
        {
            System.out.println("Control was here ");
            home.user_index++;
            user.setId(home.user_index);
            home.current_user = user.getId() - 1;
            home.database.addUser(user);
            home.database.user_mode = true;
            home.database.lawyer_mode=false;
            home.mv.setViewName("UserHome.html");
        }
        else
        {
            home.mv.setViewName("UserHome.html");

        }
        System.out.println("After submitting form"+ home.database.user_mode);
        return home.mv;
    }
    @RequestMapping(value = "/ViewUserProfile",method = RequestMethod.GET)
    public ModelAndView userProfileCon() {

        // Process the userInput object
        // (e.g., save it to a database, perform some logic)
        if(home.database.user_mode==true) {
            home.mv.setViewName("ViewUserProfile.html");
            home.mv.addObject("userTH", home.database.getUserList().get(home.current_user));
        }
        else
        {
            home.mv.setViewName("PreHome.html");
        }
        return home.mv;

    }
    /*****User Section end*****/

}
