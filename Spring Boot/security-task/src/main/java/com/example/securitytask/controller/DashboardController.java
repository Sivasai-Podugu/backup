package com.example.securitytask.controller;

import com.example.securitytask.repositories.UserRepository;
import com.example.securitytask.repositories.V1JwtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    V1JwtRepository v1JwtRepository;

    @GetMapping
    public String displayDashboard(Model model, HttpServletRequest request, Authentication authentication){
        System.out.println(request.getCookies().length);
        List<Cookie> cookies = List.of(request.getCookies());
        Cookie jwtCookie = null;
        boolean jwtPresent = false;
        boolean v1JwtPresent = false;
        boolean businessJwtPresent =false;
        for (Cookie temp: cookies) {
            if ((temp.getName()).equals("V1Jwt")) {
                jwtPresent = true;
                jwtCookie = temp;
                v1JwtPresent = true;
            } else if ((temp.getName()).equals("BusinessJwtToken")) {
                jwtPresent = true;
                jwtCookie = temp;
                businessJwtPresent = true;

            }
        }

        String username;

        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext.getAuthentication().getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User user = (DefaultOAuth2User) securityContext.getAuthentication().getPrincipal();
            System.out.println(user.getAttributes());
            username = user.getAttribute("email")!= null ?user.getAttribute("email"): user.getAttribute("login");
            model.addAttribute("userDetails", user.getAttribute("email")!= null ?user.getAttribute("email"): user.getAttribute("login"));
        }else {
            User user = (User) securityContext.getAuthentication().getPrincipal();
            com.example.securitytask.models.User users = userRepo.findByEmail(user.getUsername());
            model.addAttribute("userDetails", users.getUserName());
            username = user.getUsername();
        }
//        if(v1JwtPresent){
//            com.example.securitytask.models.User user = userRepo.findByEmail(((UserDetails)authentication.getPrincipal()).getUsername());
//            user.setApiUser(true);
//            userRepo.save(user);
//        }
//        else{
//            com.example.securitytask.models.User user = userRepo.findByEmail(((UserDetails)authentication.getPrincipal()).getUsername());
//            user.setApiUser(false);
//            userRepo.save(user);
//        }
        System.out.println(userRepo.findByEmail(username));
        model.addAttribute("listofV1jwt",v1JwtRepository.findByUser(userRepo.findByEmail(username)) );

        return "dashboard";


    }


}
