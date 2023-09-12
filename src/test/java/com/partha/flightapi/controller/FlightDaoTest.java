package com.partha.flightapi.controller;

import com.partha.flightapi.dao.FLightApiDaoImpl;
import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.repository.FlightRepository;
import com.partha.flightapi.testUtility.TestMockDataPrep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * User: Partha Pratim Baral
 * Topic :
 */

@ExtendWith(MockitoExtension.class)
public class FlightDaoTest {

    @Mock
    FlightRepository flightRepository;
    @InjectMocks
    private FLightApiDaoImpl flightApiDao;
    private Flight flight;

    @BeforeEach
    public void setUp() {
        flight = TestMockDataPrep.getMockedFlightData();
    }

    @Test
    void givenFlightToAddShouldReturnAddedFLight() throws Exception{
        when(flightRepository.save(any())).thenReturn(flight);
        flightApiDao.saveFlightDetails(flight);
        verify(flightRepository,times(1)).save(any());
    }

    @Test
    public void givenIdThenShouldReturnFlightId() {
        Mockito.when(flightRepository.findById("B101")).thenReturn(Optional.ofNullable(flight));
        assertEquals(flightApiDao.getFlightById(flight.getFlightId()), flight);
    }
}
