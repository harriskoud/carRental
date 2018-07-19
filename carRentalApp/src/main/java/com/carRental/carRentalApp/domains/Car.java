package com.carRental.carRentalApp.domains;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.carRental.carRentalApp.validation.UniqueColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
//@Document(indexName = "cars",type="car" , shards = 1)
public class Car implements Serializable {
	
	@Id
	private int carId;
	@NotEmpty
	@NotNull
	@UniqueColumn(message = "BrandName value Exists")
	//@Field(index =true, type = FieldType.Text )
	private String brandName;
	//@Field(index =true, type = FieldType.Keyword )
	public LocalDate bookedDate;

}
