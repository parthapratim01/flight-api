package com.partha.flightapi.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.partha.flightapi.dao.queryOptimizer.FlightSpecificationBuilder;
import com.partha.flightapi.dao.queryOptimizer.SearchCriteria;
import com.partha.flightapi.dto.FlightDTO;
import com.partha.flightapi.dto.SearchDTO;
import com.partha.flightapi.entity.Flight;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
public class HelperUtility {

    private static final Logger logger = LoggerFactory.getLogger(HelperUtility.class);

    public static Flight getEntity(FlightDTO dto){

        logger.info("getEntity method call");
        Flight flight = new Flight();
        flight.setFlightId(dto.getFlightId());
        flight.setOrigin(dto.getOrigin());
        flight.setDestination(dto.getDestination());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setArrivalTime(dto.getArrivalTime());
        flight.setPrice(dto.getPrice());
        flight.setDuration(dto.getDuration());

        return flight;
    }

    public static FlightDTO getFlightDTO(Flight entity){

        logger.info("getFlightDTO method call");
        FlightDTO dto = new FlightDTO();
        dto.setFlightId(entity.getFlightId());
        dto.setOrigin(entity.getOrigin());
        dto.setDestination(entity.getDestination());
        dto.setDepartureTime(entity.getDepartureTime());
        dto.setArrivalTime(entity.getArrivalTime());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        return dto;
    }

    public static List<FlightDTO> convertEntityToDTOList(List<Flight> entityList ){

        logger.info("convertEntityToDTOList method call");
        List<FlightDTO> aList = new ArrayList<>();
        for (Flight entity : entityList) {
            FlightDTO dto = new FlightDTO();
            dto.setFlightId(entity.getFlightId());
            dto.setOrigin(entity.getOrigin());
            dto.setDestination(entity.getDestination());
            dto.setDepartureTime(entity.getDepartureTime());
            dto.setArrivalTime(entity.getArrivalTime());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            aList.add(dto);
        }
        return aList;
    }

    public static Double stringToDouble(String price ){

        return Double.parseDouble(price);
    }

    public static FlightSpecificationBuilder getSearchDTO(ObjectMapper jacksonObjectMapper,
                                                          String searchString, Class valuetype)
            throws JsonProcessingException {

        if(null != searchString && !searchString.isEmpty()) {
            FlightSpecificationBuilder builder = new FlightSpecificationBuilder();
            SearchDTO searchDTO = jacksonObjectMapper.readValue(searchString, SearchDTO.class);
            List<SearchCriteria> criteriaList = searchDTO.getSearchCriteriaList();
            if (criteriaList != null) {
                criteriaList.forEach(x -> {
                    x.setDataOption(searchDTO.getDataOption());
                    builder.with(x);
                });
            }
            return builder;
        }else {
            return null;
        }
    }

    public static LocalTime calculateDuration(LocalDateTime arrivalTime, LocalDateTime departureTime){

        Duration duration = Duration.between(departureTime, arrivalTime);
        String durationString = DurationFormatUtils.formatDuration(duration.toMillis(), "HH:mm", true);
        return LocalTime.parse(durationString);
    }
}
