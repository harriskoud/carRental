package com.carRentalReservation.carRentalReservationApp.proxy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.carRentalReservation.carRentalReservationApp.domains.ApiDomains.Car;

	
	@FeignClient(name = "car-microservice")
	public interface EurekaCarInvocation {
		//@GetMapping("car-microservice/ui/car/{brandName}") 
		@GetMapping("ui/car/{brandName}") 
		ResponseEntity<Car> getCarInfo(@PathVariable(name="brandName") String brandName);
		
	}



