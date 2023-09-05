package com.example.demo.Package;

import com.example.demo.JavaClasses.Lawyer;
import com.example.demo.Service.LawyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class LawyerController {
    @Autowired
    HomeController home;

    @Autowired
    LawyerService lawyerService;
    static int lawyer_Index=0;

    private int generateLawyerIndex(){
        int i,l=lawyerService.getAllLawyers().size();
        for ( i = 0; i < l; i++) {
            if(lawyerService.getLawyerById(i)==null){
                break;
            }
        }
        return i;
    }
    /*****Lawyer Section start*****/
    @RequestMapping("/NewLawyer")
    public String showLawyerForm(Model model) {
        home.database.lawyer_mode=false;
        model.addAttribute("lawyerInput", new Lawyer());
        return "LawyerRegistration";
    }

    @RequestMapping("/editLawyer")
    public String editLawyerForm(@RequestParam("lawyerID")int lid, Model model) {
        if(home.database.lawyer_mode)
        {
            System.out.println(lid);
            model.addAttribute("lawyerTH",lawyerService.getLawyerById(lid));
        }
        return "NewLawyer";
    }
    @PostMapping("/lawyer-register")
    public String lawyerLoginCon(
            @RequestParam("firstName")String firstName,
            @RequestParam("lastName")String lastName,
            @RequestParam("email")String email,
            @RequestParam("Password")String Password,
            @RequestParam("RePassword")String RePassword){
        Lawyer lawyer = new Lawyer();
        String name=firstName+" "+lastName;

        if(Password.equals(RePassword)) {
            lawyer.setLawyerName(name);
            lawyer.setEmail(email);
            lawyer.setLpassword(Password);

            if(!home.database.lawyer_mode)
            {
                lawyer_Index=generateLawyerIndex();
                lawyer.setLawyerID(lawyer_Index);

                HomeController.current_user = lawyer.getLawyerID();
                lawyerService.createLawyer((lawyer));

                home.database.user_mode = false;
                home.database.lawyer_mode=true;
                return "LawyerHome";
            }
            return "PreHome";
        }
        else
            return "Registration";
    }

    @PostMapping("/updateLawyer")
        public String updateLawyerform(@ModelAttribute Lawyer lawyer){

        if(home.database.lawyer_mode)
        {
            System.out.println("user from submit User: "+ lawyer.getLawyerID());
            lawyerService.updateLawyer(lawyer);
            return "LawyerHome";
        }

        return "PreHome";
    }
    @RequestMapping("/ViewLawyerProfile")
    public String lawyerProfileCon(Model model) {

        // Process the userInput object
        // (e.g., save it to a database, perform some logic)
        if(home.database.lawyer_mode) {

            model.addAttribute("lawyerTH", lawyerService.getLawyerById(HomeController.current_user));
            return "ViewLawyerProfile";
        }

        return "PreHome";


    }

    @PostMapping("/deleteLawyer")
    public String deleteLawyer(){
        lawyerService.deleteLawyer(HomeController.current_user);
        return "PreHome";
    }


    /*****lawyer Section end*****/

}
