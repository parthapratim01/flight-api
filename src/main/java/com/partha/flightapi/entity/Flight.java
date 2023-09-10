package com.partha.flightapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * User: Partha Pratim Baral
 * Topic :
 */

@Entity
@Table(name = "FLIGHT_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flight {

    @Id
    @Column(name = "FLIGHT_ID")
    private String flightId;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "DEPARTURE_TIME")
    private LocalDateTime departureTime;

    @Column(name = "ARRIVAL_TIME")
    private LocalDateTime arrivalTime;

    @Column(name = "FLIGHT_FARE")
    private Double price;

    @Column(name = "DURATION")
    private LocalTime duration;
}
