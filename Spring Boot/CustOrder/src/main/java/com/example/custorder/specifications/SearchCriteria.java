package com.example.custorder.specifications;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private SearchOperation op;
    private String value;
    private  String table;

    public SearchCriteria(String key, SearchOperation searchOperation, String value) {
        this.key = key;
        this.op = op;
        this.value = value;

    }
}
