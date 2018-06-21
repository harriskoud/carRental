package com.carRental.carRentalApp.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carRental.carRentalApp.domains.Car;
import com.carRental.carRentalApp.repositories.CarRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UniqueColumnValidator implements
  ConstraintValidator<UniqueColumn, String> {
	
	private final CarRepository carRep;
 
    @Override
    public void initialize(UniqueColumn column) {
    }
 
    @Override
    public boolean isValid(String contactField,
      ConstraintValidatorContext cxt) {
        
    	Optional<Car> oCar = carRep.findOneByBrandName(contactField);
    	return !oCar.isPresent();
    	
    }{

}
}
