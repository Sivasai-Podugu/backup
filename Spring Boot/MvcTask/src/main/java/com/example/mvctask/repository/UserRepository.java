package com.example.mvctask.repository;

import com.example.mvctask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long> {


    User findByAgeGreaterThan(int age);


    @Query("select u from User u WHERE u.username= :name")
    List<User> findUsersbyName(@Param("name") String name);
}