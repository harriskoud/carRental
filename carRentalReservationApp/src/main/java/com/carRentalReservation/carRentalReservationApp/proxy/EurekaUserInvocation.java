package com.carRentalReservation.carRentalReservationApp.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.carRentalReservation.carRentalReservationApp.domains.ApiDomains.User;

@FeignClient(name = "user-microservice")
public interface EurekaUserInvocation {
	@GetMapping("ui/user/{username}")
	ResponseEntity<User> getUserInfo(@RequestHeader("Authorization") String authToken, @PathVariable(name = "username") String username);

}