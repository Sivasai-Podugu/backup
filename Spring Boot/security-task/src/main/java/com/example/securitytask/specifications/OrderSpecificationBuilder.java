package com.example.securitytask.specifications;



import com.example.securitytask.models.Orders;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class OrderSpecificationBuilder extends BasicSpecificationBuilder{
    public OrderSpecificationBuilder(){
        super((new ArrayList<>()));
    }


    @Override
    public Specification<Orders> build() {
        if(getParams().isEmpty()){
            return null;
        }
        System.out.println("Hiiiiiiii Customer");
        System.out.println(getParams().size());
        Specification<Orders> result = new OrderSpecification(getParams());

        return result;
    }
}
