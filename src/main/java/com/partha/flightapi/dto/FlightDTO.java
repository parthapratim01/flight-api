package com.partha.flightapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    @NotBlank(message = "FlightId cannot be blank or null")
    @Size(min = 3, max = 5)
    private String flightId;

    @NotBlank(message = "Origin cannot be blank or null")
    @Size(min = 3, max = 30)
    private String origin;

    @NotBlank(message = "Destination cannot be blank or null")
    @Size(min = 3, max = 30)
    private String destination;

    @NotNull(message = "Departure time cannot be blank or null")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time cannot be blank or null")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Price time cannot be blank or null")
    private Double price;

    private LocalTime duration;
}
