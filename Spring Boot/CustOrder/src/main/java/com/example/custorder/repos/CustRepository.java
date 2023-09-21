package com.example.custorder.repos;

import com.example.custorder.model.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {


}
