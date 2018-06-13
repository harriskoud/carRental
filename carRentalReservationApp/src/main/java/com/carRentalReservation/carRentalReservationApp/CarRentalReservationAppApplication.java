package com.carRentalReservation.carRentalReservationApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.carRentalReservation.carRentalReservationApp.domains.CarReservation;
import com.carRentalReservation.carRentalReservationApp.repositories.CarReservationRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarRentalReservationAppApplication implements CommandLineRunner {

	private final CarReservationRepository carReservationRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//carReservationRepository.save(CarReservation.builder().bookedBy("Harris").carBrand("VW").reservationId(0).build());
		
	}

}
