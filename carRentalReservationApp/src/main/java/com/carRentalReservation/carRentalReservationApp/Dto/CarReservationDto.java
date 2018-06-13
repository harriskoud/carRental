package com.carRentalReservation.carRentalReservationApp.Dto;

import org.springframework.hateoas.ResourceSupport;
import com.carRentalReservation.carRentalReservationApp.domains.CarReservation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarReservationDto extends ResourceSupport{
	
	private CarReservation carReservation;

}
