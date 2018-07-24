package com.carRentalReservation.carRentalReservationApp.exception;

import java.util.Date;

import javax.annotation.security.RolesAllowed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String details;

}
