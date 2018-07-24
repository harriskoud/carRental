package com.carRentalReservation.carRentalReservationApp.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CarRentalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage() , request.getDescription(false) );
		return new ResponseEntity(response, HttpStatus.NOT_FOUND);
	}
	
	

}
