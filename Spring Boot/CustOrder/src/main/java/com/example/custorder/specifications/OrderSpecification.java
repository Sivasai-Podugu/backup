package com.example.custorder.specifications;


import com.example.custorder.model.OrderStatus;
import com.example.custorder.model.Orders;

import java.util.List;

public class OrderSpecification extends BasicSpecification<Orders> {
//    public OrderSpecification(final SearchCriteria searchCriteria) {
//        super(searchCriteria);
//    }
     public  OrderSpecification(final List<SearchCriteria> searchCriteriaList){
         super(searchCriteriaList);
     }

    protected Object getEnumValueIfEnum(String key, String value, SearchOperation op) {
        switch (key) {
            case "orderStatus":
                if (op == SearchOperation.EQUALITY) {
                    if (value.getClass().getName().equals("java.lang.String")) {
                        OrderStatus.valueOf(value.toString());
                    } else {
                        return value;
                    }
                }
                return OrderStatus.valueOf(value.toString());
            case "paymentDone":
                return Boolean.parseBoolean(value.toString());
            default:
                return value;
        }
    }

}
