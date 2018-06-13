package com.carRental.carRentalApp.services;

import java.util.Arrays;
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
	
	
	//Known as Bulkhead
	@HystrixCommand(fallbackMethod = "buildFallBackCarList", threadPoolKey = "CarThreadPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "30"),  //max number of threads
			@HystrixProperty(name = "maxQueueSize", value = "10") // how many requests can the queue take. The othersS will be rejected
	})
	public List<Car> getListOfCarsWithHystrixThreadPool() {
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
	
	private List<Car> buildFallBackCarList(){
		String a[] = new String[]{"FIAT","BMW","AUDI","VW"};
	      List carList = Arrays.asList(a);
	      return carList;
	}
	

}
