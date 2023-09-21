package com.example.securitytask.services;

import com.example.securitytask.models.User;
import com.example.securitytask.repositories.UserRepository;
import com.example.securitytask.specifications.CustomerSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User User) {
        return userRepository.save(User);
    }

    public User updateUser(User User) {
        return userRepository.save(User);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersBySpecification(String search) {
        CustomerSpecificationBuilder builder = new CustomerSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)([:<~\\[|>\\]])(\\*?)(\\w+?)(\\*?),");
        Matcher matcher = pattern.matcher( search+ ",");
        while (matcher.find()) {
            System.out.println("Group 1: "+matcher.group(1)+"Group 2: "+matcher.group(2)+"Group 3: "+matcher.group(3)+"Group 4: "+matcher.group(4)+"Group 5: "+matcher.group(5));
            builder = (CustomerSpecificationBuilder) builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5),"orders");
        }
        System.out.println(builder.getParams());
        Specification<User> spec = builder.build();
        System.out.println("builded: "+builder.getParams());
        return userRepository.findAll(spec);
    }
}
