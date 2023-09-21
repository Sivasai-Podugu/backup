package com.example.securitytask.specifications;

import com.example.securitytask.models.User;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
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



                case ENDS_WITH:
                    predicateList.add( builder.like(builder.lower(root.get(searchCriteria.getKey())), "%" +value.toString().toLowerCase()));


                case CONTAINS:
                    predicateList.add( builder.like(builder.lower(root.get(searchCriteria.getKey())), "%" + value.toString().toLowerCase() + "%"));

                case INNER_JOIN:
//                Subquery<Integer> subquery = query.subquery(Integer.class);
//                Root<User> subRoot = subquery.from(User.class);
//                subquery.select(subRoot.get("id"));
//
//
//                Predicate joinCondition = builder.equal(root.get("User").get("id"), subquery);
//
//
//                Predicate whereCondition = builder.equal(subRoot.get(searchCriteria.getKey()), searchCriteria.getValue()); // Example where condition
//
//
//                return builder.and(joinCondition, whereCondition);

//                root.fetch(searchCriteria.getTable(), JoinType.INNER);
//                System.out.println(builder.conjunction());
//                 return builder.conjunction();

                    Join<M, User> join = root.join(searchCriteria.getTable(), JoinType.INNER);
                    predicateList.add(builder.equal(join.get(searchCriteria.getKey()), value));
                    break;

                case LEFT_JOIN:
//                root.join("User", JoinType.LEFT);
//                return builder.in(root.get("User").get("id")).value(subquery);
//                root.fetch(searchCriteria.getTable(), JoinType.LEFT);
//                return builder.conjunction();
//                root.join("User", JoinType.LEFT);
                    Join<M, User> leftJoin = root.join(searchCriteria.getTable(), JoinType.LEFT);
                    Predicate leftJoinCondition = builder.equal(leftJoin.get(searchCriteria.getKey()), value);
                    return leftJoinCondition;
                case RIGHT_JOIN:
//                root.join("User", JoinType.RIGHT);
//                return builder.in(root.get("User").get("id")).value(subquery);
//                root.fetch(searchCriteria.getTable(), JoinType.RIGHT);
//                return builder.conjunction();
                    Join<M, User> rightJoin = root.join(searchCriteria.getTable(), JoinType.RIGHT);
                    Predicate rightJoinCondition = builder.equal(rightJoin.get(searchCriteria.getKey()), value);
                    return rightJoinCondition;

                case FULL_JOIN:
//                root.join("User", JoinType.LEFT);
//                query.distinct(true);
//                return builder.in(root.get("User").get("id")).value(subquery);
//                root.fetch(searchCriteria.getTable(), JoinType.LEFT);
//                query.distinct(true);

//                return builder.conjunction();

                    Join<M, User> fullJoin = root.join(searchCriteria.getTable(), JoinType.LEFT);
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