package com.example.securitytask.repositories;

import com.example.securitytask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor {
    public Optional<User> findByUserName(String userName);
    public User findByEmail(String email);
}