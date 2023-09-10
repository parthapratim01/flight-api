package com.partha.flightapi.repository;

import com.partha.flightapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * User: Partha Pratim Baral
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, String>,
        JpaSpecificationExecutor<Flight> {
}
