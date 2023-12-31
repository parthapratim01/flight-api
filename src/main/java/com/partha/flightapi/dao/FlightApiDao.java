package com.partha.flightapi.dao;

import com.partha.flightapi.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * User: Partha Pratim Baral
 */

public interface FlightApiDao {

    public void saveFlightDetails(Flight flight);
    public Page<Flight> getAllFlight(Pageable page);
    public Flight getFlightById(String flightId);
    public Page<Flight> findBySearchCriteria(Specification<Flight> spec, Pageable page);
    public void deleteFlight(String flightId);
    public Flight updateFLight(Flight flight);
}
