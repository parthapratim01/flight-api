package com.partha.flightapi.service;

import com.partha.flightapi.dao.FLightApiDaoImpl;
import com.partha.flightapi.dao.FlightApiDao;
import com.partha.flightapi.dto.FlightDTO;
import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.repository.FlightRepository;
import com.partha.flightapi.testUtility.TestMockDataPrep;
import com.partha.flightapi.utility.DateUtil;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Author: Partha Pratim Baral
 */

@ExtendWith(MockitoExtension.class)
public class FlightApiServiceTest {

    @Mock
    private FlightApiDao flightApiDao;

    @Mock
    private FlightRepository repository;
    @InjectMocks
    private FlightApiServiceImpl flightApiService;

    private Flight flight, flight1;

    @BeforeEach
    public void setUp() {
        flight = TestMockDataPrep.getMockedFlightData();
    }

    @Test
    public void whenFlightToAdd_thenReturnAddedFLight(){
        doNothing().when(flightApiDao).saveFlightDetails(any());
        FlightDTO dto = TestMockDataPrep.getMockedCreateFlightData();
        flightApiService.createFlight(dto);
        verify(flightApiDao, times(1)).saveFlightDetails(any());
    }

    @Test
    public void whenGetAllFlights_thenReturnAllFlightList(){
        Page<Flight> flights = TestMockDataPrep.getData();
        when(flightApiDao.getAllFlight(any())).thenReturn(flights);
        List<FlightDTO> testData = flightApiService.findBySearchCriteria(null, null);
        assertEquals(testData.size(), flights.toList().size());
    }

    @Test
    public void givenIdThen_ReturnFlightById() {
        Mockito.when(flightApiDao.getFlightById("B101")).thenReturn(flight);
        FlightDTO testData = flightApiService.fetchFlightById("B101");
        assertEquals(testData.getFlightId(), flight.getFlightId());
        assertEquals(testData.getDestination(), flight.getDestination());
    }

    @Test
    public void givenFlight_Then_ReturnUpdatedFlight() {
        Mockito.when(flightApiDao.updateFLight(any())).thenReturn(flight);
        Flight tobe = new Flight("B101", "AMS", "BOM", DateUtil.toLocalDateTime("2023-08-31 12:00:00"),
                DateUtil.toLocalDateTime("2023-08-31 19:30:00"), 750.0, DateUtil.toLocalTime("7:30:00"));
        Mockito.when(flightApiDao.getFlightById("B101")).thenReturn(tobe);

        FlightDTO dto = new FlightDTO("B101", null, null,
                DateUtil.toLocalDateTime("2023-08-31 11:00:00"),
                DateUtil.toLocalDateTime("2023-08-31 16:00:00"), null,
                DateUtil.toLocalTime("05:00:00"));
        FlightDTO testData = flightApiService.updateFlight(dto);
        assertEquals(testData.getFlightId(), flight.getFlightId());
        assertEquals(testData.getDestination(), flight.getDestination());
    }

    @Test
    public void deleteFlightData() throws Exception{
        String flightId = "D201";
        doNothing().when(flightApiDao).deleteFlight(flightId);
        flightApiService.deleteFlight(flightId);
        verify(flightApiDao, times(1)).deleteFlight(any());
    }
}
