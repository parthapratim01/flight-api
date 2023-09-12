package com.partha.flightapi.repository;

import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.repository.FlightRepository;
import com.partha.flightapi.testUtility.TestMockDataPrep;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * User: Partha Pratim Baral
 * Topic :
 */

@DataJpaTest
public class FlightRepositoryTest {


    @Test
    void findAll_should_return_flight_list() {

        Flight flight = TestMockDataPrep.getMockedFlightData();
        List<Flight> list = new ArrayList<>();
        list.add(flight);
        Pageable pageable = PageRequest.of(0, 1, Sort.by("flightId"));
        Page<Flight> page = new PageImpl<>(list, pageable, list.size());
        FlightRepository repo = mock(FlightRepository.class);
        given(repo.findAll(pageable)).willReturn(page);
        assertEquals(1, page.toList().size());
    }

    @Test
    void findById_should_return_flight() {

        Flight flight = TestMockDataPrep.getMockedFlightData();
        FlightRepository repo = mock(FlightRepository.class);
        Mockito.when(repo.findById("B101")).thenReturn(Optional.ofNullable(flight));
        assertNotNull(flight);
    }

    @Test
    void save_should_insert_new_Flight() {

        Flight flight = TestMockDataPrep.getMockedFlightData();
        FlightRepository repo = mock(FlightRepository.class);
        Mockito.when(repo.save(flight)).thenReturn(flight);
        assertNotNull(flight);
        assertEquals("B101", flight.getFlightId());
    }

    @Test
    void save_should_update_existing_Flight() {

        Flight flight = TestMockDataPrep.getMockedFlightData();
        FlightRepository repo = mock(FlightRepository.class);
        Mockito.when(repo.save(flight)).thenReturn(flight);
        assertNotNull(flight);
        assertEquals("AMS", flight.getOrigin());
        assertEquals("BOM", flight.getDestination());
    }

    @Test
    void deleteById_should_delete_Flight() {
        Flight flight = TestMockDataPrep.getMockedFlightData();
        FlightRepository repo = mock(FlightRepository.class);
        Mockito.when(repo.findById("B101")).thenReturn(Optional.ofNullable(flight));
        repo.deleteById("B101");
        verify(repo, times(1)).deleteById("B101");
    }
}
