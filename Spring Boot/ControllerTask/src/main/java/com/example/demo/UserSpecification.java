package com.example.demo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

//import java.util.function.Predicate;

//public class UserSpecification {
//
//
//    public static Specification<User> withUserNameAndAge(String userName, int age){
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if(userName!=null &&!userName.isEmpty()){
//                predicates.add((Predicate) criteriaBuilder.like(criteriaBuilder.lower(root.get("username")),"%"+userName.toLowerCase()+"%"));
//            }
//            if(age !=0){
//                predicates.add((Predicate) criteriaBuilder.equal(root.get("age"),age));
//            }
//            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
//
//        };
//    }
//}

import jakarta.persistence.criteria.Predicate;
@SuppressWarnings({"rawtypes", "unchecked"})
public class UserSpecification implements Specification<User>{
    private SearchCriteria searchCriteria;

    public UserSpecification(final SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        switch (searchCriteria.getOp()) {
            case EQUALITY:
                return  builder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());


            case GREATER_THAN:
                return  builder.greaterThan(root.get(searchCriteria.getKey()),  searchCriteria.getValue());



            case LESS_THAN:
                return builder.lessThan(root.get(searchCriteria.getKey()), searchCriteria.getValue() );



            case STARTS_WITH:
                return  builder.like(builder.lower(root.get(searchCriteria.getKey())), searchCriteria.getValue().toLowerCase() + "%");



            case ENDS_WITH:
                return builder.like(builder.lower(root.get(searchCriteria.getKey())), "%" +searchCriteria.getValue().toLowerCase());


            case CONTAINS:
                return builder.like(builder.lower(root.get(searchCriteria.getKey())), "%" + searchCriteria.getValue().toLowerCase() + "%");

            default:
                return null;

        }

    }
}