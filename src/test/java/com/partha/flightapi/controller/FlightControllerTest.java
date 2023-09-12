package com.partha.flightapi.controller;

import com.partha.flightapi.dto.FlightDTO;
import com.partha.flightapi.service.FlightApiService;
import com.partha.flightapi.testUtility.TestMockDataPrep;
import com.partha.flightapi.utility.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * User: Partha Pratim Baral
 * Topic :
 */


@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {

    @Mock
    private FlightApiService flightApiService;
    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    public void initEach(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void testFetchFlights() throws Exception{

        when(flightApiService.findBySearchCriteria(any(), any())).thenReturn(TestMockDataPrep.getMockedData());
        ResponseEntity<List<FlightDTO>> response = flightController.fetchFlights(0, 20, "flightId", null);
        assertEquals(2, response.getBody().size());
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
}
