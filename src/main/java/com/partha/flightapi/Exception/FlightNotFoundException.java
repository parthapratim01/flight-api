package com.partha.flightapi.Exception;

/**
 * User: Partha Pratim Baral
 * Topic :
 */
public class FlightNotFoundException extends RuntimeException{

    public FlightNotFoundException (String message) {
        super(message);
    }
}
