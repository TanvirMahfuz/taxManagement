package com.example.demo.Package;

import com.example.demo.JavaClasses.Lawyer;
import com.example.demo.JavaClasses.User;
import com.example.demo.Service.LawyerService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    HomeController home;

    @Autowired
    UserService userService;
    @Autowired
    LawyerService lawyerService;
    static int user_index;
    private int generateUserIndex(){
        int i=0,l=userService.getAllUsers().size();
        for ( i = 0; i < l; i++) {
            if(userService.getUserById(i)==null){
                break;
            }
        }
        return i;
    }
    /*****User Section start*****/
    @RequestMapping("/NewUser")
    public String showUserForm() {
        home.database.user_mode=false;
        return "Registration";

    }

    @PostMapping("/editUser")
    public String showUsereditForm(@RequestParam("userID") int uid, Model model) {
        System.out.println("Uid "+uid);
        model.addAttribute("userInput",userService.getUserById(uid));
        return "NewUser";

    }

    @PostMapping("/userlogin")
    public String userLoginCon(
            @RequestParam("firstName")String firstName,
            @RequestParam("lastName")String lastName,
            @RequestParam("email")String email,
            @RequestParam("Password")String Password,
            @RequestParam("RePassword")String RePassword,
            Model model){
        User nuser= new User();
        String name=firstName+" "+lastName;

        if(Password.equals(RePassword)){
            nuser.setName(name);
             nuser.setEmail(email);
                nuser.setPassword(Password);

            if(!home.database.user_mode)
            {
                if(nuser.getLawyer()==null){
                    nuser.setLawyer(new Lawyer().emptyLawyer());
                }
                user_index=generateUserIndex();
                nuser.setId(user_index);
                home.current_user = nuser.getId();
                userService.createUser(nuser);
                home.database.user_mode = true;
                home.database.lawyer_mode=false;
                return "UserHome";
            }
            return "PreHome";
        }
        else
            return "Registration";
    }
    @PostMapping("/updateUser")
    public String submitUserForm(@ModelAttribute User userInput) {
        System.out.println(userInput.getId());
        System.out.println(userInput.getName());
        System.out.println(user_index);
        System.out.println(home.current_user);
        System.out.println(home.database.user_mode);
        if(home.database.user_mode)
        {
            System.out.println("user from submit User: "+ userInput.getId());
            userService.updateUser(userInput);

            return "UserHome";
        }

        return "PreHome";
    }
    @RequestMapping(value = "/ViewUserProfile",method = RequestMethod.GET)
    public String userProfileCon(Model model) {

        // Process the userInput object
        // (e.g., save it to a database, perform some logic)
        System.out.println(home.database.user_mode);
        if(home.database.user_mode) {
            model.addAttribute("userTH",userService.getUserById(home.current_user));
        }
        else
        {
            return "PreHome";
        }
        return "ViewUserProfile";

    }
    /*****User Section end*****/

    @PostMapping("/addLawyer")
    public String addLawyer(@RequestParam("lawyerID")int id,Model model){
        if(home.database.user_mode){
            userService.updateLawyerofUser(home.current_user,id);
            model.addAttribute("userTH",userService.getUserById(home.current_user));
            return "UserHome";
        }
        else return "PreHome";
    }
    @PostMapping("/deleteUser")
    public String deleteLawyer(@ModelAttribute User user){
        System.out.println(user.getId());
        userService.deleteUser(user.getId());
        return "PreHome";
    }


}
