package com.example.mvctask.controller;

import com.example.mvctask.model.User;
import com.example.mvctask.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MvcControl {
    private UserService userService;

    public MvcControl(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/ops")
    public  String showOps(){
        return "actions";
    }

    @RequestMapping("/usersview")
    public ModelAndView renderUsersOnly(){
        return userService.renderUsers();
    }

    @RequestMapping("/users")
    public String showUsers(){
        return "userlist";
    }

    @RequestMapping("/adduser")
    public String sayhello(){
        return "index";
    }

    @RequestMapping("/submit")
    public ModelAndView createUser(@RequestParam(name = "username") String name, @RequestParam(name = "id") int id, @RequestParam(name = "age") int age) {
        User user = new User(id, name, age);
        return userService.addUser(user);
    }
    @RequestMapping("/deleteuser")
    public String deleteView(){
        return "delete";
    }

    @RequestMapping("/delete")
    public ModelAndView deleteUser( @RequestParam(name = "id") int id){

        return userService.removeUser(id);
    }

    @RequestMapping("/updateuser")
    public String updateView( ){

        return "update";
    }

    @RequestMapping("/update")
    public ModelAndView updateUser( @RequestParam(name = "username") String name, @RequestParam(name = "id") int id,@RequestParam(name = "age") int age){

        User user = new User(id, name,age);
        return userService.updateUser(user);
    }

    @RequestMapping("/userbyage")
    public String viewCustomQuery(){
        return "custom";
    }

    @RequestMapping("/executecustom")
    public ModelAndView rendercustom(@RequestParam int age){
        return userService.renderCustomUsers(age);
    }

    @RequestMapping("/userbyname")
    public String viewCustomQueryByName(){
        return "customName";
    }

    @RequestMapping("/executecutsombyname")
    public ModelAndView renderCustomByName(@RequestParam String name){
        return userService.renderCustomUsersByName(name);
    }
}
