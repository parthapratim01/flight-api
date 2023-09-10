package com.partha.flightapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.partha.flightapi.dao.queryOptimizer.FlightSpecificationBuilder;
import com.partha.flightapi.dto.FlightDTO;
import com.partha.flightapi.dto.SearchDTO;
import com.partha.flightapi.service.FlightApiService;
import com.partha.flightapi.utility.HelperUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */

@RestController
@RequestMapping("/v1")
public class FlightController {

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightApiService flightApiService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @GetMapping("/ping")
    public String pingService() {

        logger.info(" pingService in method call");
        return "Flight Service is Up and running";
    }

    @PostMapping("/flights")
    public ResponseEntity createFlight(@Valid @RequestBody FlightDTO request) {

        logger.info(" createFlight method call ");
        return new ResponseEntity<FlightDTO>(flightApiService.createFlight(request), HttpStatus.CREATED);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity findFlightById(@PathVariable String flightId){
        logger.info(" findFlightById method call ");
        return new ResponseEntity<FlightDTO>(flightApiService.fetchFlightById(flightId), HttpStatus.OK);
    }

    @GetMapping("/flights")
    public ResponseEntity fetchFlights(
            @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(name = "sortedBy", defaultValue = "flightId") String sortedBy,
            @RequestParam(value = "searchString", required = false) String searchString
    ) throws JsonMappingException, JsonProcessingException
    {
        logger.info(" fetchFlights flights method call ");
        FlightSpecificationBuilder builder = HelperUtility.getSearchDTO(jacksonObjectMapper,
                searchString, SearchDTO.class);
        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by(sortedBy).ascending());
        List<FlightDTO> dtoList = flightApiService.findBySearchCriteria(builder, page);
        return new ResponseEntity<List<FlightDTO>>(dtoList, HttpStatus.OK);
    }
    @PutMapping("/flight")
    public ResponseEntity updateFLightData( @RequestBody FlightDTO request) {
        logger.info(" updateFLightData flights method call ");
        return new ResponseEntity<FlightDTO>(flightApiService.updateFlight(request), HttpStatus.OK);
    }

    @DeleteMapping("/flight/{flightId}")
    public ResponseEntity deleteFlightData(@PathVariable String flightId){

        logger.info(" deleteFlightData flights method call ");
        flightApiService.deleteFlight(flightId);
        return new ResponseEntity<String>("Flight data deleted successfully!.", HttpStatus.OK);
    }
}
