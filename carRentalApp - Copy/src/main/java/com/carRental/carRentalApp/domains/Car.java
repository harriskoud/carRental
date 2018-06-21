package com.carRental.carRentalApp.domains;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.carRental.carRentalApp.validation.UniqueColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Car   implements Serializable {

	@Id
	private int carId;
	@NotEmpty
	@NotNull
	@UniqueColumn(message = "BrandName value Exists")
	private String brandName;
	
	


}
