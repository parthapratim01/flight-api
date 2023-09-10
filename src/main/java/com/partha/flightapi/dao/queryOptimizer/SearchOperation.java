package com.partha.flightapi.dao.queryOptimizer;

/**
 * User: Partha Pratim Baral
 */

public enum SearchOperation {

    EQUAL,
    BEGINS_WITH,
    ANY,
    ALL;

    public static final String[] SIMPLE_OPERATION_SET = {
            "eq",
            "bw",
    };

    public static SearchOperation getDataOption(final String
                                                        dataOption){
        switch(dataOption){
            case "all": return ALL;
            case "any": return ANY;
            default: return null;
        }
    }

    public static SearchOperation getSimpleOperation(final String input) {
        switch (input){
            case "eq": return EQUAL;
            case "bw": return BEGINS_WITH;
            default: return null;
        }
    }
}
