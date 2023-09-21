package com.example.custorder.services;

import com.example.custorder.model.Customer;
import com.example.custorder.repos.CustRepository;
import com.example.custorder.specifications.CustomerSpecificationBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustService {

    private final CustRepository custRepository;

    public CustService(CustRepository custRepository) {
        this.custRepository = custRepository;
    }

    public List<Customer> getAllCustomers(){
        return custRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        return custRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return custRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return custRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        custRepository.deleteById(id);
    }

    public List<Customer> getCustomersBySpecification(String search) {
        CustomerSpecificationBuilder builder = new CustomerSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)([:<~\\[|>\\]])(\\*?)(\\w+?)(\\*?),");
        Matcher matcher = pattern.matcher( search+ ",");
        while (matcher.find()) {
            System.out.println("Group 1: "+matcher.group(1)+"Group 2: "+matcher.group(2)+"Group 3: "+matcher.group(3)+"Group 4: "+matcher.group(4)+"Group 5: "+matcher.group(5));
            builder = (CustomerSpecificationBuilder) builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5),"orders");
        }
        System.out.println(builder.getParams());
        Specification<Customer> spec = builder.build();
        System.out.println("builded: "+builder.getParams());
        return custRepository.findAll(spec);
    }
}
