package com.carRentalReservation.carRentalReservationApp.Dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.carRentalReservation.carRentalReservationApp.domains.CarReservation;
import com.carRentalReservation.carRentalReservationApp.resources.CarReservationResource;

@Component
public class CarReservationAssembler extends ResourceAssemblerSupport<CarReservation, CarReservationDto> {

	public CarReservationAssembler() {
		super(CarReservationResource.class, CarReservationDto.class);

	}
	@Override
	public CarReservationDto toResource(CarReservation carReservation) {

		CarReservationDto carReservationDto = createResourceWithId(carReservation.getReservationId(), carReservation);
		carReservationDto.setCarReservation(carReservation);
		Link invoiceLink = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CarReservationResource.class)
				.getReservation(carReservation.getReservationId())).withRel("reservation");

		carReservationDto.add(invoiceLink);
		return carReservationDto;
	}

}
