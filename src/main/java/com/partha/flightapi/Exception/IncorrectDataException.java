package com.partha.flightapi.Exception;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/10/2023
 */
public class IncorrectDataException extends RuntimeException{
    public IncorrectDataException(String message) {
        super(message);
    }
}
