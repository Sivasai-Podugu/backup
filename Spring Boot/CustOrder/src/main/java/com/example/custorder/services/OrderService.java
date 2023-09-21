package com.example.custorder.services;

import com.example.custorder.model.Customer;
import com.example.custorder.model.Orders;
import com.example.custorder.repos.OrderRepository;
import com.example.custorder.specifications.JoinCriteria;
import com.example.custorder.specifications.JoinTypes;
import com.example.custorder.specifications.OrderSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getOrdersForCustomer(Customer customer) {
        System.out.println(customer);
        return orderRepository.findByCustomer(customer);
    }
    private JoinCriteria parseJoinParam(String joinParam) {
        String[] parts = joinParam.split("~");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid join parameter format");
        }

        JoinTypes joinTypes = JoinTypes.valueOf(parts[0]); // e.g., INNER_JOIN
        String joinTable = parts[1];                    // e.g., "customer"

        return new JoinCriteria(joinTypes, joinTable);
    }


    public Orders createOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    public Orders updateOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Orders> getOrdersBySpecification(String search, String join) {
//        JoinCriteria joinCriteria = parseJoinParam(join);
        OrderSpecificationBuilder builder = new OrderSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)([:<\\~\\[\\|>\\]])(\\*?)(\\w+?)(\\*?),");
        Matcher matcher = pattern.matcher( search+ ",");
        while (matcher.find()) {
            System.out.println("Group 1: "+matcher.group(1)+"Group 2: "+matcher.group(2)+"Group 3: "+matcher.group(3)+"Group 4: "+matcher.group(4)+"Group 5: "+matcher.group(5));
            builder = (OrderSpecificationBuilder) builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5),"customer");
        }
        System.out.println(builder.getParams());
        Specification<Orders> spec = builder.build();
        System.out.println("builded: "+builder.getParams());
        return orderRepository.findAll(spec);
    }
}