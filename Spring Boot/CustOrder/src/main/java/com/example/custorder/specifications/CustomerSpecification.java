package com.example.custorder.specifications;

import com.example.custorder.model.Customer;
import com.example.custorder.model.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification extends BasicSpecification<Customer> {
    public CustomerSpecification(final List<SearchCriteria> searchCriteriaList) {
        super(searchCriteriaList);
    }

    @Override
    protected Object getEnumValueIfEnum(String object, String value, SearchOperation op) {
        return value;
    }


}
