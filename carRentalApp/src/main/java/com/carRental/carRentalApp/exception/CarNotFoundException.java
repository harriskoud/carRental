package com.carRental.carRentalApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * This exception is thrown when the specific user is not found at the request, and throws a Runtime Exception
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException {
	
	/**
	 * q
	 */
	private static final long serialVersionUID = 1L;

	public CarNotFoundException (String message) {
		super(message);
	}
	
}
