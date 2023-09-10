package com.partha.flightapi.Exception;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/10/2023
 */
public class FlightAlreadyExistsException extends RuntimeException {
    public FlightAlreadyExistsException(String message) {
        super(message);
    }
}
