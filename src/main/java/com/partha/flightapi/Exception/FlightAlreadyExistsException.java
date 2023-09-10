package com.partha.flightapi.Exception;

/**
 * User: Partha Pratim Baral
 * Topic :
 */
public class FlightAlreadyExistsException extends RuntimeException {
    public FlightAlreadyExistsException(String message) {
        super(message);
    }
}
