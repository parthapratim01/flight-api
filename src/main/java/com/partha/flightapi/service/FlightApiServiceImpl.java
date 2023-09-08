package com.partha.flightapi.service;

import com.partha.flightapi.controller.FlightController;
import com.partha.flightapi.dao.queryOptimizer.FlightSpecificationBuilder;
import com.partha.flightapi.dto.DomainDtoMapper;
import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.utility.HelperUtility;
import com.partha.flightapi.dao.FlightApiDao;
import com.partha.flightapi.dto.FlightDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
@Service
public class FlightApiServiceImpl implements FlightApiService{

    private static final Logger logger = LoggerFactory.getLogger(FlightApiServiceImpl.class);

    @Autowired
    private FlightApiDao flightApiDao;

    @Override
    public FlightDTO createFlight(FlightDTO request) {

        logger.info("createFlight method call");
        flightApiDao.saveFlightDetails(HelperUtility.getEntity(request));
        return request;
    }

    /*@Override
    public List<FlightDTO> fetchAllFlights() {

        logger.info("fetchAllFlights method call");
        return HelperUtility.convertEntityToDTOList(flightApiDao.getAllFlight());
    }*/

    @Override
    public FlightDTO fetchFlightById(String flightId) {

        logger.info("fetchFlightById method call");
        return HelperUtility.getFlightDTO(flightApiDao.getFlightById(flightId));
    }

    @Override
    public List<FlightDTO> findBySearchCriteria(FlightSpecificationBuilder builder, Pageable page) {

        logger.info("findBySearchCriteria method call");
        Page<Flight> flights = null;
        if(null != builder){
            Specification<Flight> spec = builder.build();
            flights = flightApiDao.findBySearchCriteria(spec, page);
            return DomainDtoMapper.getFlightDTOs(flights);
        }else {
            flights = flightApiDao.getAllFlight(page);
            return DomainDtoMapper.getFlightDTOs(flights);
        }
    }
}
