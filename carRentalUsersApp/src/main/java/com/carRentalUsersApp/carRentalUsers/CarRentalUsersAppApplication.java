package com.carRentalUsersApp.carRentalUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.carRentalUsersApp.carRentalUsers")
public class CarRentalUsersAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalUsersAppApplication.class, args);
	}
}
