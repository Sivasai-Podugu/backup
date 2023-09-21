package com.example.securitytask.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.securitytask.dtos.UserRegisteredDTO;
import com.example.securitytask.models.Role;
import com.example.securitytask.models.User;
import com.example.securitytask.models.V1Jwt;
import com.example.securitytask.repositories.RoleRepository;
import com.example.securitytask.repositories.UserRepository;
import com.example.securitytask.repositories.V1JwtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserServiceImpl implements DefaultUserService{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private V1JwtRepository v1JwtRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");

        User user = userRepo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public User save(UserRegisteredDTO userRegisteredDTO) {
        Role role = roleRepo.findByRole("USER");

        User user = new User();
        user.setEmail(userRegisteredDTO.getEmail_id());
        user.setUserName(userRegisteredDTO.getName());
        user.setPassword(passwordEncoder.encode(userRegisteredDTO.getPassword()));
        user.setRoles((Set<Role>) role);

        return userRepo.save(user);
    }

    public boolean isSecure(String jwt){
        List<V1Jwt> listofV1Jwt = v1JwtRepository.findAll();
        for (V1Jwt v1Jwt: listofV1Jwt) {

            if(v1Jwt.getJwt().equals(jwt)){
                System.out.println("secure jwt");
                return true;

            }


        }
        System.out.println("Insecure jwt");

        return false;

    }
    public Optional<User> getUserDetailsFromJwt(String jwt){
        Optional<User> user = userRepo.findById(((v1JwtRepository.findByJwt(jwt)).getUser()).getId());
        return user;
    }
}