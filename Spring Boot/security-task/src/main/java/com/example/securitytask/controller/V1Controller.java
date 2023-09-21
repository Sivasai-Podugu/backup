package com.example.securitytask.controller;


import com.example.securitytask.dtos.UserLoginDTO;
import com.example.securitytask.models.Orders;
import com.example.securitytask.models.User;
import com.example.securitytask.models.V1Jwt;
import com.example.securitytask.repositories.UserRepository;
import com.example.securitytask.repositories.V1JwtRepository;
import com.example.securitytask.services.DefaultUserService;
import com.example.securitytask.services.OrderService;
import com.example.securitytask.services.UserService;
import com.example.securitytask.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class V1Controller {
    @Autowired
    private V1JwtRepository v1JwtRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepo;

    @Autowired
    OrderService orderService;

    @ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping
    public ResponseEntity<String> loginV1(Authentication authentication) {

        return ResponseEntity.ok("Welcome to V1!");

    }

    @GetMapping("/token/generate")
    public ResponseEntity<String> generateToken(Authentication authentication, HttpServletResponse httpServletResponse){
        String usernameToGenerateToken;

        if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            System.out.println(user.getAttributes());
            usernameToGenerateToken = user.getAttribute("email")!= null ?user.getAttribute("email"): user.getAttribute("login")+"@gmail.com";
            System.out.println("usernameToGenerateToken: "+ usernameToGenerateToken);
        }else {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            usernameToGenerateToken = user.getUsername();
            System.out.println("usernameToGenerateToken: "+ usernameToGenerateToken);
        }
        String jwtUserName = usernameToGenerateToken+System.currentTimeMillis();
        String jwt = jwtTokenUtil.generateToken(jwtUserName);

        System.out.println(usernameToGenerateToken);
        User user = userRepo.findByEmail(usernameToGenerateToken);
        user.setApiUser(true);
        userRepo.save(user);
//        Cookie cookie = new Cookie("V1Jwt", jwt);
//        cookie.setPath("/");
//
//        cookie.setMaxAge(3600*24);
//
//        httpServletResponse.addCookie(cookie);
        V1Jwt v1Jwt = new V1Jwt();
        v1Jwt.setJwtName(jwtUserName);
        v1Jwt.setJwt(jwt);
        v1Jwt.setUser(user);
        v1JwtRepository.save(v1Jwt);



        String responseHtml = "<html><body>" +
                "<h1>Token Generated</h1>" +
                "<p>Generated JWT: " + jwt + "</p>" +
                "</body></html>";

        return ResponseEntity.ok()
                .header("Content-Type", "text/html; charset=UTF-8")
                .body(responseHtml);




    }

    @GetMapping("/users")
    public ResponseEntity<List<User>>  renderAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User User = userService.getUserById(id);
        if (User != null) {
            return ResponseEntity.ok(User);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User User) {
        User createdUser = userService.createUser(User);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User User) {
        if (User.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        User updatedUser = userService.updateUser(User);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/spec")
    public ResponseEntity<List<User>> getUsersBySpecification(@RequestParam(value = "search") String search){
        return ResponseEntity.ok(userService.getUsersBySpecification(search));

    }
    @GetMapping("/orders")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("order/customer/{customerId}")
    public List<Orders> getOrdersForCustomer(@PathVariable int customerId) {
        System.out.println(customerId);
        User customer = userService.getUserById(customerId);
        System.out.println(customer);
        if (customer == null) {
            return Collections.emptyList();
        }
        return orderService.getOrdersForCustomer(customer);
    }

    @PostMapping("/order")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        System.out.println("Order creating");
        Orders createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable int id, @RequestBody Orders order) {
        if (order.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Orders updatedOrder = orderService.updateOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        System.out.println("Deleted");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order/spec")
    public ResponseEntity<List<Orders>> getCustomersBySpecification(@RequestParam(value = "search") String search){
        return ResponseEntity.ok(orderService.getOrdersBySpecification(search));

    }



}
