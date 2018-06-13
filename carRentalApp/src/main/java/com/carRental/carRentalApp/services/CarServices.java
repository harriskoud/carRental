package com.carRental.carRentalApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carRental.carRentalApp.domains.Car;
import com.carRental.carRentalApp.repositories.CarRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarServices {
	
	private final CarRepository carRepository;
	
	@HystrixCommand//(commandProperties= {@HystrixProperty (name = "execution.isolation.thread.timeoutInMilliseconds", value= "12000")})
	public List<Car> getListOfCars(){
		testHystrixWithDelay();
		return carRepository.findAll();
	}
	
	private void testHystrixWithDelay() {
		
		try {
		Thread.sleep(11000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
