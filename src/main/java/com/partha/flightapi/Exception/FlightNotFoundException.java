package com.partha.flightapi.Exception;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */
public class FlightNotFoundException extends RuntimeException{

    public FlightNotFoundException (String message) {
        super(message);
    }
}
