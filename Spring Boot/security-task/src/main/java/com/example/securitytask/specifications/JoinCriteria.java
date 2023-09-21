package com.example.securitytask.specifications;

public class JoinCriteria {
    private JoinTypes joinTypes;
    private String table;

    public JoinCriteria(JoinTypes joinTypes, String table) {
        this.joinTypes = joinTypes;
        this.table = table;
    }

    public JoinTypes getJoinType() {
        return joinTypes;
    }

    public String getTable() {
        return table;
    }
}

