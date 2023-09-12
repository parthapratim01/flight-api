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
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
    FLightApiDaoImpl flightApiDao;
    Flight flight;

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

    @Test
    public void givenFlightId_whenDeleteFLight_thenVerify() {
        Mockito.when(flightRepository.findById("B101")).thenReturn(Optional.ofNullable(flight));
        flightApiDao.deleteFlight("B101");
        Mockito.verify(flightRepository, times(1)).delete(flight);
    }

    @Test
    public void givenFlightList_whenGetAllFlights_thenReturnFlightList(){

        List<Flight> list = new ArrayList<>();
        list.add(flight);
        Pageable pageable = PageRequest.of(0, 1, Sort.by("flightId"));
        Page<Flight> page = new PageImpl<>(list, pageable, list.size());
        given(flightRepository.findAll(pageable)).willReturn(page);
        Page<Flight> flightList = flightApiDao.getAllFlight(pageable);
        assertEquals(1, flightList.getSize());
    }
}
