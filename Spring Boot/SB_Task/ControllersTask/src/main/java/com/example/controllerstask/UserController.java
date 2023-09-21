package com.example.controllerstask;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    @GetMapping(value ="/")
    public String home() {
        System.out.println("Started...");
        return "home.html";
    }

    @PostMapping(value ="/submit")
    public String submit(@RequestParam(name="username") String user, @RequestParam(name = "email") String email) {
        System.out.println("Callled ;"+user+"; "+email);
        return "submit.html";
    }



}
