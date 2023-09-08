package com.partha.flightapi.dao;

import com.partha.flightapi.Exception.FlightNotFoundException;
import com.partha.flightapi.controller.FlightController;
import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
@Component
public class FLightApiDaoImpl implements FlightApiDao{

    private static final Logger logger = LoggerFactory.getLogger(FLightApiDaoImpl.class);

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void saveFlightDetails(Flight flight) {

        logger.info(" saveFlightDetails method call");
        flightRepository.save(flight);
    }

    @Override
    public Page<Flight> getAllFlight(Pageable page) {

        logger.info(" getAllFlight method call");
        return flightRepository.findAll(page);
    }

    @Override
    public Flight getFlightById(String flightId) {

        logger.info(" getFlightById method call");
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("No Flight found for id : " + flightId));
        return flight;
    }

    @Override
    public Page<Flight> findBySearchCriteria(Specification<Flight> spec, Pageable page) {
        return flightRepository.findAll(spec, page);
    }
}
