package com.example.demo;


import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private SearchOperation op;
    private String value;

    public SearchCriteria(String key, SearchOperation op, String value) {
        this.key = key;
        this.op = op;
        this.value = value;
    }
}
