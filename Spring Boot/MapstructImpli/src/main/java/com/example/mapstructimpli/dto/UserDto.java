package com.example.mapstructimpli.dto;

import com.example.mapstructimpli.model.Address;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @Id
    private int id;
    private String name;
    private int age;
//    private Address address;
}
