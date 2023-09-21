package com.example.mvctask.services;

import com.example.mvctask.model.User;
import com.example.mvctask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ModelAndView addUser(User user){
        ModelAndView mv = new ModelAndView();
        Optional<User> optionalUser = userRepository.findById((long)user.getId());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " already exists.");

        } else {
            userRepository.save(user);

        }

        System.out.println(userRepository.findAll());
        mv.addObject("listOfUsers",userRepository.findAll());
        mv.setViewName("userlist");
        return mv;
    }

    public ModelAndView removeUser(int id){
        ModelAndView mv = new ModelAndView();
        userRepository.deleteById((long) id);
        System.out.println(userRepository.findAll());
        mv.addObject("listOfUsers",userRepository.findAll());
        mv.setViewName("userlist");
        return mv;

    }

    public ModelAndView updateUser(User user){
        ModelAndView mv = new ModelAndView();
        userRepository.save(user);
//        Optional<User> optionalUser = userRepository.findById((long)user.getId());
//        if (optionalUser.isPresent()) {
//            User u = optionalUser.get();
//            u.setUsername(user.getUsername());
//            userRepository.save(u);
//        } else {
//            throw new IllegalArgumentException("User with ID " + user.getId() + " not found.");
//        }
        mv.addObject("listOfUsers",userRepository.findAll());
        mv.setViewName("userlist");
        return mv;
    }

    public ModelAndView renderUsers(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("listOfUsers",userRepository.findAll());
        mv.setViewName("usersonly");
        return mv;
    }


    public ModelAndView renderCustomUsers(int age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("listOfUsers",userRepository.findByAgeGreaterThan(age));
        mv.setViewName("userlist");
        return mv;

    }

    public ModelAndView renderCustomUsersByName(String name) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("listOfUsers",userRepository.findUsersbyName(name));
        mv.setViewName("userlist");
        return mv;

    }
}
