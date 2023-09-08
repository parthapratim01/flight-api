package com.partha.flightapi.dao.queryOptimizer;

import com.partha.flightapi.entity.Flight;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
public class FlightSpecificationBuilder {

    private final List<SearchCriteria> params;

    public FlightSpecificationBuilder() {

        this.params = new ArrayList<>();
    }

    public final FlightSpecificationBuilder with(String key, String operation, Object value){

        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final FlightSpecificationBuilder with(SearchCriteria searchCriteria){

        params.add(searchCriteria);
        return this;
    }

    public Specification<Flight> build(){

        if(params.size() == 0){
            return null;
        }

        Specification<Flight> result = new FlightSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result =  SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new FlightSpecification(criteria))
                    : Specification.where(result).or(new FlightSpecification(criteria));
        }
        return result;
    }
}
