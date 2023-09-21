package com.example.securitytask.specifications;

import com.example.securitytask.models.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class CustomerSpecificationBuilder extends BasicSpecificationBuilder{
    public CustomerSpecificationBuilder(){
        super((new ArrayList<>()));
    }


    @Override
    public Specification<User> build() {
        if(getParams().isEmpty()){
            return null;
        }
        System.out.println("Hiiiiiiii Customer");
        System.out.println(getParams().size());
        Specification<User> result = new CustomerSpecification(getParams());
//        for (int i = 1; i < getParams().size() ; i++) {
//            result = Specification.where(result).and(new CustomerSpecification(getParams().get(i)));
//
//        }
        return result;
    }
}
