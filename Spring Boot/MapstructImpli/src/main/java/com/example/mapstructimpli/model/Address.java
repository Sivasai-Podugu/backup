package com.example.mapstructimpli.model;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    @Id
    private int id;
    private String district;
    private String state;
    private int pincode;
}
