package com.example.custorder.specifications;

import com.example.custorder.model.Customer;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public abstract class BasicSpecification<M> implements Specification<M> {
    private List<SearchCriteria> searchCriteriaList;

    public BasicSpecification(final List<SearchCriteria> searchCriteriaList) {
        super();
        this.searchCriteriaList = searchCriteriaList;
    }
    @Override
    public Predicate toPredicate(Root<M> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicateList = new ArrayList<>();

        for ( SearchCriteria searchCriteria: searchCriteriaList) {

            Object value = getEnumValueIfEnum(searchCriteria.getKey(), searchCriteria.getValue(), searchCriteria.getOp());

            switch (searchCriteria.getOp()) {
                case EQUALITY:
                    predicateList.add(builder.equal(root.get(searchCriteria.getKey()), value));
                    break;


                case GREATER_THAN:
                    predicateList.add(builder.greaterThan(root.get(searchCriteria.getKey()),Integer.parseInt((String)value)));
                    break;



                case LESS_THAN:
                    predicateList.add( builder.lessThan(root.get(searchCriteria.getKey()), Integer.parseInt((String)value)));
                    break;



                case STARTS_WITH:
                    predicateList.add(builder.like(builder.lower(root.get(searchCriteria.getKey())), value.toString().toLowerCase() + "%"));
                    break;


                case ENDS_WITH:
                    predicateList.add( builder.like(builder.lower(root.get(searchCriteria.getKey())), "%" +value.toString().toLowerCase()));
                    break;

                case CONTAINS:
                    predicateList.add( builder.like(builder.lower(root.get(searchCriteria.getKey())), "%" + value.toString().toLowerCase() + "%"));
                    break;
                case INNER_JOIN:
//                Subquery<Integer> subquery = query.subquery(Integer.class);
//                Root<Customer> subRoot = subquery.from(Customer.class);
//                subquery.select(subRoot.get("id"));
//
//
//                Predicate joinCondition = builder.equal(root.get("customer").get("id"), subquery);
//
//
//                Predicate whereCondition = builder.equal(subRoot.get(searchCriteria.getKey()), searchCriteria.getValue()); // Example where condition
//
//
//                return builder.and(joinCondition, whereCondition);

//                root.fetch(searchCriteria.getTable(), JoinType.INNER);
//                System.out.println(builder.conjunction());
//                 return builder.conjunction();

                    Join<M, Customer> join = root.join(searchCriteria.getTable(), JoinType.INNER);
                    predicateList.add(builder.equal(join.get(searchCriteria.getKey()), value));
                    break;

                case LEFT_JOIN:
//                root.join("customer", JoinType.LEFT);
//                return builder.in(root.get("customer").get("id")).value(subquery);
//                root.fetch(searchCriteria.getTable(), JoinType.LEFT);
//                return builder.conjunction();
//                root.join("customer", JoinType.LEFT);
                    Join<M, Customer> leftJoin = root.join(searchCriteria.getTable(), JoinType.LEFT);
                    Predicate leftJoinCondition = builder.equal(leftJoin.get(searchCriteria.getKey()), value);
                    return leftJoinCondition;
                case RIGHT_JOIN:
//                root.join("customer", JoinType.RIGHT);
//                return builder.in(root.get("customer").get("id")).value(subquery);
//                root.fetch(searchCriteria.getTable(), JoinType.RIGHT);
//                return builder.conjunction();
                    Join<M, Customer> rightJoin = root.join(searchCriteria.getTable(), JoinType.RIGHT);
                    Predicate rightJoinCondition = builder.equal(rightJoin.get(searchCriteria.getKey()), value);
                    return rightJoinCondition;

                case FULL_JOIN:
//                root.join("customer", JoinType.LEFT);
//                query.distinct(true);
//                return builder.in(root.get("customer").get("id")).value(subquery);
//                root.fetch(searchCriteria.getTable(), JoinType.LEFT);
//                query.distinct(true);

//                return builder.conjunction();

                    Join<M, Customer> fullJoin = root.join(searchCriteria.getTable(), JoinType.LEFT);
                    Predicate fullJoinCondition = builder.equal(fullJoin.get(searchCriteria.getKey()), value);
                    return fullJoinCondition;
                default:
                    return null;

            }




        }

        return builder.and(predicateList.toArray(new Predicate[0]));
    }

    protected abstract Object getEnumValueIfEnum(String object, String value, SearchOperation op);
}