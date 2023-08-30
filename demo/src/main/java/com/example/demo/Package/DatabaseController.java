package com.example.demo.Package;

import com.example.demo.JavaClasses.Lawyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DatabaseController {
    @Autowired
    HomeController home;

    @RequestMapping("/seeLawyerList")
    public String getLawyerList(Model model){
        System.out.println(home.database.getLawyerList().size());
        model.addAttribute("lawyerlistTH",home.database.getLawyerList());
        return "LawyerList";
    }
    @PostMapping("/DemoLawyerProfile")
    public String processExtraAction(@RequestParam("id") int id, Model model ) {

        model.addAttribute("lawyerTH",home.database.getLawyerList().get(id));
        return "DemoLawyerProfile";
    }
}
