package com.example.custorder.repos;

import com.example.custorder.model.Customer;
import com.example.custorder.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor {
    public List<Orders> findByCustomer(Customer customer);

}
