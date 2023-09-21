package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/control")
public class Control {
@RequestMapping("/greetuser")
    public String hi(){
    System.out.println("Hi");
    return "home";

}

@RequestMapping("/submit")
     public ModelAndView viewusers(@RequestParam(name = "username")String name){
    ModelAndView mv = new ModelAndView();
    mv.addObject("name",name);
    mv.setViewName("greet");
    System.out.println("Submitted");
    return  mv;
}
}
