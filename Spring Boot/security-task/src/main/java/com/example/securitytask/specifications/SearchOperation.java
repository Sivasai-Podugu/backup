package com.example.securitytask.specifications;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, INNER_JOIN,LEFT_JOIN, RIGHT_JOIN, FULL_JOIN,LIKE, STARTS_WITH, ENDS_WITH, CONTAINS, IN, NOT_IN;

    public static final String[] SIMPLE_OPERATION_SET = {":", "!", ">", ";", "<", "~", "@"};

    public static SearchOperation getSimpleOperation(char input){
        switch (input) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;

            case '@':
                return IN;
            case ';':
                return NOT_IN;
            case '$':
                return CONTAINS;
            case '~':
                return INNER_JOIN;
            case '[':
                return LEFT_JOIN;
            case ']':
                return RIGHT_JOIN;
            case '|':
                return FULL_JOIN;


            default:
                return null;
        }
    }
}

