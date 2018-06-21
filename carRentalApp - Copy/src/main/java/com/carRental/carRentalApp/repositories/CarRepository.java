package com.carRental.carRentalApp.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.carRental.carRentalApp.domains.Car;

public interface CarRepository  extends MongoRepository<Car, Integer>{

	Optional<Car> findOneByBrandName(String string);
	
	void deleteByBrandName(String brandname);
	
	
	

}
