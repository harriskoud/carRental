package com.carRentalReservation.carRentalReservationApp.domains.ApiDomains;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;
	private int carId;
	private String brandName;
}
