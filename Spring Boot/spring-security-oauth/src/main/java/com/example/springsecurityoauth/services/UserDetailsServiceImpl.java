package com.example.springsecurityoauth.services;

import com.example.springsecurityoauth.models.MyUserDetails;
import com.example.springsecurityoauth.models.User;
import com.example.springsecurityoauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        System.out.println(email+ user.getEmail());
        System.out.println(userRepository.findById(1));

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        System.out.println("not null");
        return new MyUserDetails(user);
    }

}
