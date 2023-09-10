package com.partha.flightapi.dao;

import com.partha.flightapi.Exception.FlightAlreadyExistsException;
import com.partha.flightapi.Exception.FlightNotFoundException;
import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * User: Partha Pratim Baral
 * Topic :
 */
@Component
public class FLightApiDaoImpl implements FlightApiDao{

    private static final Logger logger = LoggerFactory.getLogger(FLightApiDaoImpl.class);

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void saveFlightDetails(Flight flight) {

        logger.info(" saveFlightDetails method call");
        Flight existingFlight = flightRepository.findById(flight.getFlightId()).orElse(null);
        if(existingFlight == null){
            flightRepository.save(flight);
        }else {
            throw new FlightAlreadyExistsException("Flight already exists !");
        }

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
        logger.info(" findBySearchCriteria method call");
        return flightRepository.findAll(spec, page);
    }

    @Override
    public void deleteFlight(String flightId) {

        logger.info(" deleteFlight method call");
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("No Flight found for id : " + flightId));
        flightRepository.delete(flight);
    }

    @Override
    public Flight updateFLight(Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }
}
