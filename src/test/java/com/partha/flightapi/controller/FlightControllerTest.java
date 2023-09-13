package com.partha.flightapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partha.flightapi.dao.FLightApiDaoImpl;
import com.partha.flightapi.dao.FlightApiDao;
import com.partha.flightapi.dto.FlightDTO;
import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.repository.FlightRepository;
import com.partha.flightapi.service.FlightApiService;
import com.partha.flightapi.service.FlightApiServiceImpl;
import com.partha.flightapi.testUtility.TestMockDataPrep;
import com.partha.flightapi.utility.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * User: Partha Pratim Baral
 * Topic :
 */


@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {

    @Mock
    private FlightApiServiceImpl flightApiService;
    @Mock
    private FLightApiDaoImpl flightApiDao;

    @Mock
    private ObjectMapper jacksonObjectMapper;
    @Mock
    private FlightRepository flightRepository;
    @InjectMocks
    private FlightController flightController;
    FlightDTO dto;

    @BeforeEach
    public void initEach(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void testFetchFlights() throws Exception{

        when(flightApiService.findBySearchCriteria(any(), any())).thenReturn(TestMockDataPrep.getMockedData());
        ResponseEntity<List<FlightDTO>> response = flightController.fetchFlights(0, 20, "flightId", "ASC", null);
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testFetchFlights_OrderByPriceDesc() throws Exception{

        when(flightApiService.findBySearchCriteria(any(), any())).thenReturn(TestMockDataPrep.getOrderData());
        ResponseEntity<List<FlightDTO>> response = flightController.fetchFlights(0, 20, "flightId", "DESC", null);
        assertEquals(3, response.getBody().size());
        assertEquals(850.0, response.getBody().get(0).getPrice());
        assertEquals(700.0, response.getBody().get(1).getPrice());
        assertEquals(600.0, response.getBody().get(2).getPrice());
    }
    @Test
    public void testFetchFlights_OrderByDurationDesc() throws Exception{

        when(flightApiService.findBySearchCriteria(any(), any())).thenReturn(TestMockDataPrep.getOrderDataDuration());
        ResponseEntity<List<FlightDTO>> response = flightController.fetchFlights(0, 20, "flightId", "DESC", null);
        assertEquals(3, response.getBody().size());
        assertEquals(DateUtil.toLocalTime("06:00:00"), response.getBody().get(0).getDuration());
        assertEquals(DateUtil.toLocalTime("05:00:00"), response.getBody().get(1).getDuration());
        assertEquals(DateUtil.toLocalTime("04:30:00"), response.getBody().get(2).getDuration());
    }

    @Test
    public void testCreateFlight() throws Exception{

        when(flightApiService.createFlight(any())).thenReturn(TestMockDataPrep.getMockedCreateFlightData());

        FlightDTO dto = new FlightDTO("A201", "AMS", "DEL", DateUtil.toLocalDateTime("2023-08-31 18:45:00"),
                DateUtil.toLocalDateTime("2023-08-31 17:00:00"), 850.0, null);
        ResponseEntity<FlightDTO> response = flightController.createFlight(dto);
        assertEquals("A201", response.getBody().getFlightId());
    }

    @Test
    public void findFlightById() throws Exception{
        when(flightApiService.fetchFlightById(any())).thenReturn(TestMockDataPrep.getMockedFlightDataById());
        ResponseEntity<FlightDTO> response = flightController.findFlightById("B101");
        assertEquals("B101", response.getBody().getFlightId());
        assertEquals("AMS", response.getBody().getOrigin());
    }

    @Test
    public void findFlightById_NotFound() throws Exception{
        String flightId = "C201";
        when(flightApiService.fetchFlightById(any())).thenReturn(dto);
        ResponseEntity<FlightDTO> response = flightController.findFlightById("C201");
        assertEquals(null, response.getBody());
    }

    @Test
    public void updateFlightData() throws Exception{
        when(flightApiService.updateFlight(any())).thenReturn(TestMockDataPrep.getUpdatedFlightData());
        FlightDTO dto = new FlightDTO("C101", null, null,
                DateUtil.toLocalDateTime("2023-08-31 11:00:00"),
                DateUtil.toLocalDateTime("2023-08-31 16:00:00"), 750.0,
                DateUtil.toLocalTime("05:00:00"));
        ResponseEntity<FlightDTO> response = flightController.updateFLightData(dto);
        assertEquals("C101", response.getBody().getFlightId());
        assertEquals("AMS", response.getBody().getOrigin());
    }

    @Test
    public void deleteFlightData() throws Exception{
        String flightId = "D201";
        flightApiService.deleteFlight(flightId);
        FlightDTO flight = this.flightApiService.fetchFlightById(flightId);
        assertTrue(flight == null);
    }

    @Test
    public void findFlightById_notFound() throws Exception{
        when(flightApiService.fetchFlightById("B101")).thenReturn(null);
        ResponseEntity<FlightDTO> response = flightController.findFlightById("B101");
        assertEquals(null, response.getBody());
    }
}
