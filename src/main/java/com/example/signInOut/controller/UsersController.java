package com.example.signInOut.controller;

import com.example.signInOut.model.UsersModel;
import com.example.signInOut.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel) {
        System.out.println("register request: " + usersModel);
        UsersModel registersUser =
                usersService.registerUser(usersModel.getFullName(),usersModel.getEmail(),
                        usersModel.getPhone(), usersModel.getStatus(), usersModel.getPassword());
        System.out.println(registersUser);
        if(registersUser != null) return "login_page"; else return "error_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model) {
        System.out.println("register request: " + usersModel);
        UsersModel authenticate = usersService.authenticate(usersModel.getEmail() ,usersModel.getPassword());
        if(authenticate != null) {
            model.addAttribute("userLogin", authenticate.getEmail());
            return "personal_page";
        }else{
            model.addAttribute("error", "dublicate_email");
            return "error_page";
        }
    }
}
