package com.example.demo;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserSpecificationBuilder {
    private  final List<SearchCriteria> params;

    public UserSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public UserSpecificationBuilder with(String key, String operation, String value, String prefix, String suffix){
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
            System.out.println("Operation: "+operation.charAt(0)+"; SearchOperation"+searchOperation);
            params.add(new SearchCriteria(key, searchOperation, value));
        }
        System.out.println(this.getParams());
        return this;

    }

    public Specification<User> build(){
        if(params.isEmpty()){
            return null;
        }
        System.out.println("Hiiiiiiii");
        System.out.println(params.size());
        Specification<User> result = new UserSpecification(params.get(0));
        for (int i = 1; i <params.size() ; i++) {
            result = Specification.where(result).and(new UserSpecification(params.get(i)));

        }
        return result;
    }


}
