package com.example.securitytask.specifications;


import org.springframework.data.jpa.domain.Specification;

import java.util.List;



public abstract class BasicSpecificationBuilder {
    private  final List<SearchCriteria> params;

    public BasicSpecificationBuilder(List<SearchCriteria> params) {
        this.params = params;
    }

    public List<SearchCriteria> getParams() {
        return params;
    }


    public BasicSpecificationBuilder with(String key, String operation, String value, String prefix, String suffix, String table){
        SearchOperation searchOperation = SearchOperation.getSimpleOperation(operation.charAt(0));
        System.out.println("Operation: "+operation.charAt(0)+"; SearchOperation"+searchOperation);
        if(searchOperation!=null){
            if(searchOperation == SearchOperation.EQUALITY) {
                boolean endsWithAsterisk = prefix.contains("*");
                boolean startsWithAsterisk = suffix.contains("*");
                if (endsWithAsterisk && startsWithAsterisk) {
                    searchOperation = SearchOperation.CONTAINS;
                }
                else if(startsWithAsterisk){
                    searchOperation = SearchOperation.STARTS_WITH;
                }
                else if (endsWithAsterisk) {
                    searchOperation = SearchOperation.ENDS_WITH;
                }
            }
            else if(searchOperation == SearchOperation.CONTAINS){
                searchOperation = SearchOperation.CONTAINS;
            }
            else if(searchOperation == SearchOperation.NOT_IN){
                searchOperation = SearchOperation.NOT_IN;
            }
            else if(searchOperation == SearchOperation.INNER_JOIN){
                searchOperation = SearchOperation.INNER_JOIN;
            }
            else if(searchOperation == SearchOperation.LEFT_JOIN){
                searchOperation = SearchOperation.LEFT_JOIN;
            }
            else if(searchOperation == SearchOperation.RIGHT_JOIN){
                searchOperation = SearchOperation.RIGHT_JOIN;
            }
            else if(searchOperation == SearchOperation.FULL_JOIN){
                searchOperation = SearchOperation.FULL_JOIN;
            }

            System.out.println("Operation: "+operation.charAt(0)+"; SearchOperation"+searchOperation);
            params.add(new SearchCriteria(key, searchOperation, value, table));
        }
        System.out.println(this.getParams());
        return this;

    }

     public abstract Specification<?> build();
//     {
//        if(params.isEmpty()){
//            return null;
//        }
//        System.out.println("Hiiiiiiii");
//        System.out.println(params.size());
//        Specification<M> result = new BasicSpecification(params.get(0));
//        for (int i = 1; i <params.size() ; i++) {
//            result = Specification.where(result).and(new BasicSpecification(params.get(i)));
//
//        }
//        return result;
//    }


}