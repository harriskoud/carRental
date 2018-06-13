package com.carRentalReservation.carRentalReservationApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carRentalReservation.carRentalReservationApp.domains.CarReservation;
import com.carRentalReservation.carRentalReservationApp.repositories.CarReservationRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarReservationServices {

	private final CarReservationRepository carReservationRepository;

	@HystrixCommand
	public List<CarReservation> getListOfCars() {
		// testHystrixWithDelay();
		return carReservationRepository.findAll();
	}



	private void testHystrixWithDelay() {
		try {
			Thread.sleep(11000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
