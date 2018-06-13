package com.carRental.carRentalApp.resources.dto;

import org.springframework.hateoas.ResourceSupport;

import com.carRental.carRentalApp.domains.Car;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto extends ResourceSupport{

	@JsonUnwrapped
	private Car car;
	
	
	
}
