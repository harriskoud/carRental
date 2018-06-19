package com.carRental.carRentalApp;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.carRental.carRentalApp.utils.UserContextInterceptor;

@SpringBootApplication
@RefreshScope
//if i want to refresh configuration files from the config server, i can hit blabla/management/refresh from actuator dependency
@EnableResourceServer
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
	
	@Bean
	 public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List interceptors = restTemplate.getInterceptors();
		//if(interceptors == null) {
			restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		//}
		return restTemplate;
	}
		
}
