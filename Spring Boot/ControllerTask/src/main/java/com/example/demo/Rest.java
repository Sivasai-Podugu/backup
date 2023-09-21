package com.example.demo;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class Rest {
//    List<User> listofusers = new ArrayList<>();
    private UserService userService;

    public Rest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> index(@RequestParam(value = "search") String search){
        System.out.println("index method"+ search);
//        UserSpecification userSpecification = (UserSpecification) UserSpecification.withUserNameAndAge(username,age);
        List<User> usersWithUsernameAndAge = userService.getUsersBySpecification(search);
        return ResponseEntity.ok(usersWithUsernameAndAge);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> viewUserwithid(@PathVariable int id){
//        User userfound ;
//        for (User user:listofusers) {
//            if(id == user.getId()){
//                userfound = user;
//                return ResponseEntity.ok(user);
//            }
//        }
//        userfound = new User();
//        System.out.println(userfound);
//        return  ResponseEntity.ok(userfound);

        return userService.renderUserWithId(id);


    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> viewUsers(){
//        return ResponseEntity.ok(listofusers);
        return userService.renderAllUsers();
    }


    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user){
//        listofusers.add(user);
//        return  ResponseEntity.ok(user);
        System.out.println("post accessed");
        return userService.createUser(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id){
//        User userfound = null;
//        for (User u:listofusers) {
//            if(id == u.getId()){
//                userfound = u;
//               u.setUsername(user.getUsername());
//               u.setId(user.getId());
//            }
//        }
//        return ResponseEntity.ok(user);
        return userService.updateUser(user,id);

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Optional<User>> deleteUser(@PathVariable int id){
//        for (User u:listofusers) {
//            if(id == u.getId()){
//
//               listofusers.remove(u);
//               return ResponseEntity.ok(u);
//            }
//        }
//        return  ResponseEntity.ok(new User());
        return userService.deleteUser(id);

    }



}
