package com.carRentalReservation.carRentalReservationApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

import com.carRentalReservation.carRentalReservationApp.repositories.CarReservationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class CarRentalReservationAppApplication implements CommandLineRunner {

	private final CarReservationRepository carReservationRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//carReservationRepository.save(CarReservation.builder().bookedBy("Harris").carBrand("VW").reservationId(0).build());
		
	}

}
