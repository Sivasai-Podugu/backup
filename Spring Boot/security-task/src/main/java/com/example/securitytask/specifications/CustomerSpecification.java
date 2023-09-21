package com.example.securitytask.specifications;

import com.example.securitytask.models.User;

import java.util.List;

public class CustomerSpecification extends BasicSpecification<User> {
    public CustomerSpecification(final List<SearchCriteria> searchCriteriaList) {
        super(searchCriteriaList);
    }

    @Override
    protected Object getEnumValueIfEnum(String object, String value, SearchOperation op) {
        return value;
    }


}
