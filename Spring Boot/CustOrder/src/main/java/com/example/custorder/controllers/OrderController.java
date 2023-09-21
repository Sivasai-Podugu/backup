package com.example.custorder.controllers;

import com.example.custorder.model.Customer;
import com.example.custorder.model.Orders;
import com.example.custorder.services.CustService;
import com.example.custorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

public class OrderController {

    private OrderService orderService;
    private CustService custService;

    public OrderController(OrderService orderService, CustService custService) {
        this.orderService = orderService;
        this.custService = custService;
    }
    @GetMapping("/orders")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("order/customer/{customerId}")
    public List<Orders> getOrdersForCustomer(@PathVariable int customerId) {
        System.out.println(customerId);
        Customer customer = custService.getCustomerById(customerId);
        System.out.println(customer);
        if (customer == null) {
            return Collections.emptyList();
        }
        return orderService.getOrdersForCustomer(customer);
    }

    @PostMapping("/order")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        System.out.println("Order creating");
        Orders createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable int id, @RequestBody Orders order) {
        if (order.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Orders updatedOrder = orderService.updateOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        System.out.println("Deleted");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order/spec")
    public ResponseEntity<List<Orders>> getCustomersBySpecification(@RequestParam(value = "search") String search, @RequestParam(value = "join") String join){
        return ResponseEntity.ok(orderService.getOrdersBySpecification(search,join));

    }


}
