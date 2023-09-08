package com.partha.flightapi.service;

import com.partha.flightapi.dao.queryOptimizer.FlightSpecificationBuilder;
import com.partha.flightapi.dto.FlightDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */

public interface FlightApiService {

    public FlightDTO createFlight(FlightDTO request);

    //public List<FlightDTO> fetchAllFlights();

    public FlightDTO fetchFlightById(String flightId);

    public List<FlightDTO> findBySearchCriteria(FlightSpecificationBuilder builder, Pageable page);
}
