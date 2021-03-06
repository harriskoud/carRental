package com.carRentalReservation.carRentalReservationApp.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.carRentalReservation.carRentalReservationApp.domains.ApiDomains.Car;

@FeignClient(name = "car-microservice")
public interface EurekaCarInvocation {
	@GetMapping("ui/car/{brandName}")
	ResponseEntity<Car> getCarInfo(@RequestHeader("Authorization") String authToken,@PathVariable(name = "brandName") String brandName);

}
