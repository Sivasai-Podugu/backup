package com.example.springsecurityoauth.controller;

import com.example.springsecurityoauth.models.User;
import com.example.springsecurityoauth.services.UserDetailsServiceImpl;
import com.example.springsecurityoauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userService;

    @GetMapping
    public String loginpage(){
        return "login";
    }
    @PostMapping
    public String loginpage2(User user){
        userService.loadUserByUsername(user.getEmail());
        return "index";

    }

}
