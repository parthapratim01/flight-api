package com.partha.flightapi.startUp;

import com.partha.flightapi.controller.FlightController;
import com.partha.flightapi.entity.Flight;
import com.partha.flightapi.repository.FlightRepository;
import com.partha.flightapi.utility.DateUtil;
import com.partha.flightapi.utility.HelperUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;


/**
 * User: Partha Pratim Baral
 * Topic :
 * Date: 9/7/2023
 */

/*@Component
@Transactional*/
public class DataLoad {

    /*private static final Logger logger = LoggerFactory.getLogger(DataLoad.class);

    private static final String flightsFile = "/data/flights-data.csv";

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    @Order(1)
    CommandLineRunner loadFlights(FlightRepository repo) {
        return (args) -> {
            loadData(resourceLoader, flightsFile,
                    v -> new Flight(v[0], v[1], v[2], DateUtil.toLocalDateTime(v[3]),
                            DateUtil.toLocalDateTime(v[4]),
                            HelperUtility.stringToDouble(v[5]), null), repo);
        };
    }

    public static void loadData(ResourceLoader resourceLoader, String dataFile,
                                   Function<String[], Object> objectMapper, JpaRepository repo) {

        logger.info("Flight data Loading running " + dataFile + "...");
        Resource resource = resourceLoader.getResource("classpath:" + dataFile);
        try (Stream<String> stream = Files.lines(Paths.get(resource.getFile().getAbsolutePath())).skip(1)) {
            stream.forEach(line -> {
                logger.info("Incoming line " + line);
                try {
                    String[] values = line.split(",");
                    Object entity = objectMapper.apply(values);
                    repo.save(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Flight Data Loading " + dataFile + " Complete ! ");
    }

    @Bean
    @Order(2)
    CommandLineRunner updateDuration(FlightRepository repo) {

        return (args) -> {
            List<Flight> flights = repo.findAll();
            for (Flight flight : flights) {
                LocalDateTime from = flight.getArrivalTime();
                LocalDateTime to = flight.getDepartureTime();
                LocalTime time = HelperUtility.calculateDuration(from, to);
                flight.setDuration(time);
                repo.save(flight);
            }
            logger.info("Flight duration update Complete ! ");
        };
    }*/
}
