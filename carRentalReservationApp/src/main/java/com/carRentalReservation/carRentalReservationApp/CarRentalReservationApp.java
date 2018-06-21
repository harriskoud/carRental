package com.carRentalReservation.carRentalReservationApp;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import com.carRentalReservation.carRentalReservationApp.utils.UserContextInterceptor;


@SpringBootApplication
@EnableResourceServer
public class CarRentalReservationApp {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalReservationApp.class, args);
	}
	
	@Bean
	 public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List interceptors = restTemplate.getInterceptors();
		//if(interceptors == null) {
			restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		//}
		return restTemplate;
	}

}
