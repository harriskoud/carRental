package com.carRentalReservation.carRentalReservationApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when the specific reservation is not found at the request, and throws a Runtime Exception
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFound  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReservationNotFound (String message) {
		super(message);
	}

}
