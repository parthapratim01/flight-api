package com.partha.flightapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<String> flightExceptionHandler(FlightNotFoundException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException m){
        return new ResponseEntity<List<String>>(m.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
    }
}
