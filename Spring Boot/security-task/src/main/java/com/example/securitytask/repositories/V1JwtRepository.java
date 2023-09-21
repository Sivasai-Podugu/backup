package com.example.securitytask.repositories;

import com.example.securitytask.models.User;
import com.example.securitytask.models.V1Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface V1JwtRepository extends JpaRepository<V1Jwt, Integer> {
    public List<V1Jwt> findByUser(User user);
    public V1Jwt findByJwt(String jwt);
}
