package com.carRental.carRentalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.carRental.carRentalApp.services.CarServices;

@SpringBootApplication
@RefreshScope
//if i want to refresh configuration files from the config server, i can hit blabla/management/refresh from actuator dependency
public class CarRentalAppApplication  {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalAppApplication.class, args);
	}
	
	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
	    return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
	    return new LocalValidatorFactoryBean();
	}		
}
