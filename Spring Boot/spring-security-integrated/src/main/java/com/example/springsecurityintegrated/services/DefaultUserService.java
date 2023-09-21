package com.example.springsecurityintegrated.services;

import com.example.springsecurityintegrated.dtos.UserRegisteredDTO;
import com.example.springsecurityintegrated.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DefaultUserService extends UserDetailsService {

    User save(UserRegisteredDTO userRegisteredDTO);





}