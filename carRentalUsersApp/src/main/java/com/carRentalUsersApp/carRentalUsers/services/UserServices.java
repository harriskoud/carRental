package com.carRentalUsersApp.carRentalUsers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carRentalUsersApp.carRentalUsers.domains.User;
import com.carRentalUsersApp.carRentalUsers.repositories.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServices {
	
	private final UserRepository userRepository;
	
	@HystrixCommand
	public List<User> getListOfCars(){
		//testHystrixWithDelay();
		return userRepository.findAll();
	}
	
	private void testHystrixWithDelay() {
		
		try {
		Thread.sleep(11000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
