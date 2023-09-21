package com.example.custorder.controllers;

import com.example.custorder.model.Customer;
import com.example.custorder.services.CustService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustController {
    private CustService custService;

    public CustController(CustService custService) {
        this.custService = custService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>>  renderAllCustomers(){
        return ResponseEntity.ok(custService.getAllCustomers());
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = custService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = custService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        if (customer.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Customer updatedCustomer = custService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        custService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/spec")
    public ResponseEntity<List<Customer>> getCustomersBySpecification(@RequestParam(value = "search") String search){
        return ResponseEntity.ok(custService.getCustomersBySpecification(search));

    }
}
