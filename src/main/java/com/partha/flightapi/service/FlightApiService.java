package com.partha.flightapi.service;

import com.partha.flightapi.dao.queryOptimizer.FlightSpecificationBuilder;
import com.partha.flightapi.dto.FlightDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 */

public interface FlightApiService {

    public FlightDTO createFlight(FlightDTO request);
    public FlightDTO fetchFlightById(String flightId);
    public List<FlightDTO> findBySearchCriteria(FlightSpecificationBuilder builder, Pageable page);
    public void deleteFlight(String flightId) ;
    public FlightDTO updateFlight(FlightDTO request) ;
}
