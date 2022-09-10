package com.elizabeth.mytodoapp.controller;

import com.elizabeth.mytodoapp.DTO.UserDto;
import com.elizabeth.mytodoapp.model.User;
import com.elizabeth.mytodoapp.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class RegistrationController {

    private UserServiceImpl userService;
@Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public  String showRegistrationForm(Model model){
        model.addAttribute("userRegistrationDetails" , new UserDto());
        return  "register";
    }

    @PostMapping(value = "/userRegistration")
    public String registration(@ModelAttribute UserDto userDTO){

        User registeredUser = userService.create(userDTO);
        System.out.println(registeredUser);
        if (registeredUser != null){

            return "redirect:/user/login";
        }else {
            return "redirect:/user/register";
        }
    }

    @GetMapping(value = "/login")
    public String displayLoginPage(Model model){
        model.addAttribute("userDetails" , new UserDto());
        return "login";
    }

    @PostMapping(value = "/loginUser")
    public String loginUser(@RequestParam String email , @RequestParam String password , HttpSession session , Model model){
        String message =  userService.Login(email ,  password);

        if(message.equals("Success")){
            User user = userService.getUserByEmail(email);
            session.setAttribute("email" , user.getEmail());
            session.setAttribute("id" , user.getId());
            session.setAttribute("name" , user.getName());
            return "redirect:/user/dashboard";
        }else{
            model.addAttribute("errorMessage" , message);
            return  "redirect:/user/login";
        }
    }
}
