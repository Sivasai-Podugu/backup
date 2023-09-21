package com.example.mapstructimpli.repository;

import com.example.mapstructimpli.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
