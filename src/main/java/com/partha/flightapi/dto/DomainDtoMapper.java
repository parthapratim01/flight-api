package com.partha.flightapi.dto;

import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.service.FlightApiServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
public class DomainDtoMapper {

    private static final Logger logger = LoggerFactory.getLogger(DomainDtoMapper.class);

    public static List<FlightDTO> getFlightDTOs(Page<Flight> flights){

        logger.info("getFlightDTOs method call");
        if(flights == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        List<Flight> fts = flights.toList();
        List<FlightDTO> aDtoList = new ArrayList<>();
        for (Flight f : fts){
            FlightDTO d = modelMapper.map(f, FlightDTO.class);
            aDtoList.add(d);
        }
        return aDtoList;
    }
}
