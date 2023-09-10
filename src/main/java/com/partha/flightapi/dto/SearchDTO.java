package com.partha.flightapi.dto;

import com.partha.flightapi.dao.queryOptimizer.SearchCriteria;

import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 */
public class SearchDTO {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

    public SearchDTO() {
    }

    public SearchDTO(List<SearchCriteria> searchCriteriaList, String dataOption) {
        this.searchCriteriaList = searchCriteriaList;
        this.dataOption = dataOption;
    }

    public List<SearchCriteria> getSearchCriteriaList() {
        return searchCriteriaList;
    }

    public void setSearchCriteriaList(List<SearchCriteria> searchCriteriaList) {
        this.searchCriteriaList = searchCriteriaList;
    }

    public String getDataOption() {

        return dataOption;
    }

    public void setDataOption(String dataOption) {

        this.dataOption = dataOption;
    }
}
