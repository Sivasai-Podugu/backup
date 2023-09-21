package com.example.springsecurityoauth.services;

import com.example.springsecurityoauth.models.User;
import com.example.springsecurityoauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void processOAuthPostLogin(String email) {
        User existUser = repo.getUserByEmail(email);

        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setProvider(User.Provider.GOOGLE);
            newUser.setEnabled(true);

            repo.save(newUser);
        }

    }

}
