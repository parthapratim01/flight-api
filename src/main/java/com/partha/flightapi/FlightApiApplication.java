package com.partha.flightapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Flight API", version = "1.0", description = "Flight Information System")
)
public class FlightApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(FlightApiApplication.class, args);
	}

}
