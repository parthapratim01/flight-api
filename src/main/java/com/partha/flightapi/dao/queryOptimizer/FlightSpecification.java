package com.partha.flightapi.dao.queryOptimizer;

import com.partha.flightapi.controller.FlightController;
import com.partha.flightapi.entity.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
public class FlightSpecification implements Specification<Flight> {

    private static final Logger logger = LoggerFactory.getLogger(FlightSpecification.class);

    private final SearchCriteria searchCriteria;

    public FlightSpecification(SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Specification<Flight> and(Specification<Flight> other) {

        return Specification.super.and(other);
    }

    @Override
    public Specification<Flight> or(Specification<Flight> other) {

        return Specification.super.or(other);
    }

    public Predicate toPredicate(Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        logger.info(" toPredicate in method call");
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();
        switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
            case EQUAL:
                if(searchCriteria.getFilterKey().equals("flightId")){
                    return criteriaBuilder.equal(root.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }
                if(searchCriteria.getFilterKey().equals("origin")){
                    return criteriaBuilder.equal(root.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }
                if(searchCriteria.getFilterKey().equals("destination")){
                    return criteriaBuilder.equal(root.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }
            case BEGINS_WITH:
                if(searchCriteria.getFilterKey().startsWith("origin")){
                    return criteriaBuilder.like(root.get(searchCriteria.getFilterKey()),
                            searchCriteria.getValue()+"%");
                }
        }
        return null;
    }
}
