package com.partha.flightapi.dao.queryOptimizer;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
public class SearchCriteria {

    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;

    public SearchCriteria() {

    }

    public SearchCriteria(String filterKey, String operation, Object value){
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }

    public String getFilterKey() {

        return filterKey;
    }

    public void setFilterKey(String filterKey) {

        this.filterKey = filterKey;
    }

    public Object getValue() {

        return value;
    }

    public void setValue(Object value) {

        this.value = value;
    }

    public String getOperation() {

        return operation;
    }

    public void setOperation(String operation) {

        this.operation = operation;
    }

    public String getDataOption() {

        return dataOption;
    }

    public void setDataOption(String dataOption) {

        this.dataOption = dataOption;
    }
}
