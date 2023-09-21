package com.example.demo;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserService {

    private final UserRepository userRepository;
//    private User user1;
//    private User user2;
//    @Before("")
//    public void init() {
//        user1 = new User();
//        user1.setUsername("Siva");
//        user1.setId(101);
//
//        user1.setAge(22);
//        userRepository.save(user1);
//
//        user2 = new User();
//        user2.setUsername("Sivasai");
//
//        user2.setId(102);
//        user2.setAge(21);
//        userRepository.save(user2);
//    }
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> renderAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    public ResponseEntity<Optional<User>> renderUserWithId(int id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    public ResponseEntity<User> createUser(User user) {
        System.out.println("Accessed createUser");
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User user1 = new User(-1,"user already exists",0);
            return ResponseEntity.ok(user1);

        } else {
            return ResponseEntity.ok(userRepository.save(user));

        }

    }

    public ResponseEntity<User> updateUser(User user, int id) {
        return ResponseEntity.ok(userRepository.save(user));

    }

    public ResponseEntity<Optional<User>> deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return (ResponseEntity<Optional<User>>)ResponseEntity .ok(optionalUser);

        } else {
            User user1 = new User(-1,"user Doesnot exists",0);
            return ResponseEntity.ok(Optional.of(user1));

        }
    }
    public List getUsersBySpecification(String search){
        UserSpecificationBuilder builder = new UserSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\*?)(\\w+?)(\\*?),");
        Matcher matcher = pattern.matcher( search+ ",");
        while (matcher.find()) {
            System.out.println("Group 1: "+matcher.group(1)+"Group 2: "+matcher.group(2)+"Group 3: "+matcher.group(3)+"Group 4: "+matcher.group(4)+"Group 5: "+matcher.group(5));
            builder = builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }
        System.out.println(builder.getParams());
        Specification<User> spec = builder.build();
        System.out.println(builder.getParams());
        return userRepository.findAll(spec);

    }
}
