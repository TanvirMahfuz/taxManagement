package com.example.demo.Package;

import com.example.demo.JavaClasses.Lawyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LawyerController {
    @Autowired
    HomeController home;

    /*****Lawyer Section start*****/
    @RequestMapping("/NewLawyer")
    public String showLawyerForm(Model model) {
        home.database.lawyer_mode=false;
        model.addAttribute("lawyerInput", home.lawyer);
        return "NewLawyer";
    }

    @RequestMapping(value = "/submitLawyer",method = RequestMethod.POST)
    public ModelAndView submitLawyerForm(@ModelAttribute Lawyer lawyer) {

        home.mv.setViewName("LawyerHome.html");
        if(home.database.lawyer_mode==false)
        {
            System.out.println("Control was here ");
            home.lawyer_Index++;
            lawyer.setLawyerID(home.lawyer_Index);
            home.current_user = lawyer.getLawyerID() - 1;
            home.database.addLawyer(lawyer);
            home.database.lawyer_mode = true;
            home.database.user_mode=false;
        }

        System.out.println("After submitting form"+ home.database.lawyer_mode);
        return home.mv;
    }
    @RequestMapping(value = "/ViewLawyerProfile",method = RequestMethod.GET)
    public ModelAndView lawyerProfileCon() {

        // Process the userInput object
        // (e.g., save it to a database, perform some logic)
        if(home.database.lawyer_mode==true) {
            home.mv.setViewName("ViewLawyerProfile.html");
            home.mv.addObject("lawyerTH", home.database.getLawyerList().get(home.current_user));
        }
        else
        {
            home.mv.setViewName("PreHome.html");
        }
        return home.mv;

    }



    /*****lawyer Section end*****/

}
