package com.partha.flightapi.testUtility;

import com.partha.flightapi.dto.FlightDTO;
import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.utility.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Partha Pratim Baral
 * Topic :
 */
public class TestMockDataPrep {

    public static List<FlightDTO> getMockedData(){
        FlightDTO dto1 = new FlightDTO("A101", "AMS", "DEL", DateUtil.toLocalDateTime("2023-08-31 18:45:00"),
                DateUtil.toLocalDateTime("2023-08-31 17:00:00"), 850.0, null);
        FlightDTO dto2 = new FlightDTO("A201", "LHR", "BOM", DateUtil.toLocalDateTime("2023-08-31 11:30:00"),
                DateUtil.toLocalDateTime("2023-08-31 17:00:00"), 600.0, null);
        List<FlightDTO> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);
        return list;
    }

    public static List<Flight> getMockedEntity(){
        Flight entitty1 = new Flight("A101", "AMS", "DEL", DateUtil.toLocalDateTime("2023-08-31 18:45:00"),
                DateUtil.toLocalDateTime("2023-08-31 17:00:00"), 850.0, null);
        Flight entitty2 = new Flight("A201", "LHR", "BOM", DateUtil.toLocalDateTime("2023-08-31 11:30:00"),
                DateUtil.toLocalDateTime("2023-08-31 17:00:00"), 600.0, null);
        List<Flight> list = new ArrayList<>();
        list.add(entitty1);
        list.add(entitty2);
        return list;
    }

    public static FlightDTO getMockedCreateFlightData(){
        FlightDTO dto = new FlightDTO("A201", "AMS", "DEL", DateUtil.toLocalDateTime("2023-08-31 18:45:00"),
                DateUtil.toLocalDateTime("2023-08-31 17:00:00"), 850.0, null);
        return dto;
    }

    public static FlightDTO getMockedFlightDataById(){
        FlightDTO dto = new FlightDTO("B101", "AMS", "BOM", DateUtil.toLocalDateTime("2023-08-31 12:00:00"),
                DateUtil.toLocalDateTime("2023-08-31 19:30:00"), 750.0, null);
        return dto;
    }

    public static FlightDTO getUpdatedFlightData(){
        FlightDTO dto = new FlightDTO("C101", "AMS", "AMS", DateUtil.toLocalDateTime("2023-08-31 14:00:00"),
                DateUtil.toLocalDateTime("2023-08-31 21:30:00"), 750.0, DateUtil.toLocalTime("7:30:00"));
        return dto;
    }

    public static Flight getMockedFlightData(){
        Flight flight = new Flight("B101", "AMS", "BOM", DateUtil.toLocalDateTime("2023-08-31 12:00:00"),
                DateUtil.toLocalDateTime("2023-08-31 19:30:00"), 750.0, DateUtil.toLocalTime("7:30:00"));
        return flight;
    }
}
