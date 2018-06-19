package com.carRentalReservation.carRentalReservationApp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//All the requests must be authenticated
		//http.authorizeRequests().anyRequest().authenticated();
		
		//The user must have Admin-role
/*		http
		.authorizeRequests()
		.antMatchers(HttpMethod.DELETE, "/ui/car/*")
		.hasRole("ADMIN")
		.anyRequest()
		.authenticated();*/
	}
}
