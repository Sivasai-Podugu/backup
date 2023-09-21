package com.example.mapstructimpli.controller;

import com.example.mapstructimpli.dto.UserDto;
import com.example.mapstructimpli.mapper.UserMapper;
import com.example.mapstructimpli.model.User;
import com.example.mapstructimpli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody UserDto userDto){
        return  new ResponseEntity<>(userRepository.save(
                userMapper.dtoToModel(userDto)), HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll(){
        return  new ResponseEntity<>(userMapper.modelsToDtos(userRepository.findAll()),HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable(value = "id") Integer id){
        return  new ResponseEntity<>(userMapper.modelToDto(userRepository.findById(id).get()),HttpStatus.OK);
    }


}
