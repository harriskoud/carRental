package com.carRental.carRentalApp.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.carRental.carRentalApp.domains.ApiDomains.User;


@FeignClient(name = "user-microservice")
public interface EurekaUserInvocation {
	@GetMapping("user-microservice/ui/user/{username}") 
	ResponseEntity<User> getUserInfo(@PathVariable(name="username") String username);
	
}