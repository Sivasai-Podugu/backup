package com.example.securitytask.repositories;

import com.example.securitytask.models.Orders;
import com.example.securitytask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor {
    public List<Orders> findByUser(User user);

}
