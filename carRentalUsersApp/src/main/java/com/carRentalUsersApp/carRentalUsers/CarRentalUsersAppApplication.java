package com.carRentalUsersApp.carRentalUsers;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.carRentalUsersApp.carRentalUsers.utils.UserContextInterceptor;

@SpringBootApplication
@EnableFeignClients("com.carRentalUsersApp.carRentalUsers")
//@EnableResourceServer
public class CarRentalUsersAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalUsersAppApplication.class, args);
	}
	
	//@LoadBalanced
	@Bean
	 public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		//if(interceptors == null) {
			restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		//}
		return restTemplate;
		
	}
}
