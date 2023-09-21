package com.example.custorder.specifications;



import com.example.custorder.model.Customer;
import com.example.custorder.model.Orders;
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
