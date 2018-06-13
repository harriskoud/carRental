package com.carRentalReservation.carRentalReservationApp.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.carRentalReservation.carRentalReservationApp.domains.ApiDomains.Car;
import com.carRentalReservation.carRentalReservationApp.domains.ApiDomains.User;

@FeignClient(name = "zuulProxy")
public interface ReservationProxy {
	@GetMapping("car-microservice/ui/car/{brandName}") 
	ResponseEntity<Car> getCarInfo(@PathVariable(name="brandName") String brandName);
	
	@GetMapping("user-microservice/ui/user/{username}") 
	ResponseEntity<User> getUserInfo(@PathVariable(name="username") String username);

}

