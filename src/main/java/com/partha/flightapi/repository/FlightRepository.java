package com.partha.flightapi.repository;

import com.partha.flightapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * User: Partha Pratim Baral
 * Date: 9/7/2023
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String>,
        JpaSpecificationExecutor<Flight> {
}
