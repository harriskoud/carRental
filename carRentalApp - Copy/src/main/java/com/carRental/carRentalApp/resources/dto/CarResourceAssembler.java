package com.carRental.carRentalApp.resources.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.carRental.carRentalApp.domains.Car;
import com.carRental.carRentalApp.resources.CarResource;

@Component
public class CarResourceAssembler extends ResourceAssemblerSupport<Car, CarDto>{

	public CarResourceAssembler() {
		super(CarResource.class, CarDto.class);
	}

	@Override
	public CarDto toResource(Car car) {
		CarDto carDto = createResourceWithId(car.getBrandName(), car);
		 
		Link invoiceLink = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CarResource.class).getCar(car.getBrandName())).withRel("car");
		carDto.setCar(car);
		carDto.add(invoiceLink);
		 
		return carDto;
	}

}
