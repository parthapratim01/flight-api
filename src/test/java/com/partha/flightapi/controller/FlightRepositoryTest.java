package com.partha.flightapi.controller;

import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: Partha Pratim Baral
 * Topic :
 */

@DataJpaTest
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void findAll_should_return_flight_list() {
        List<Flight> flights = this.flightRepository.findAll();
        assertNotNull(flights);
    }

    @Test
    void findById_should_return_flight() {
        Optional<Flight> employee = this.flightRepository.findById("A101");
        assertTrue(employee.isPresent());
    }
}
