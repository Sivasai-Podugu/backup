package com.example.securitytask.specifications;


public class SearchCriteria {
    private String key;
    private SearchOperation op;
    private String value;
    private  String table;

    public SearchCriteria(String key, SearchOperation op, String value, String table) {
        this.key = key;
        this.op = op;
        this.value = value;
        this.table = table;
    }

    public SearchOperation getOp() {
        return op;
    }

    public void setOp(SearchOperation op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SearchCriteria(String key, SearchOperation searchOperation, String value) {
        this.key = key;
        this.op = op;
        this.value = value;

    }
}
