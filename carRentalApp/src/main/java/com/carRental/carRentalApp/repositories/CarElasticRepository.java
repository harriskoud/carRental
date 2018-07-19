/*package com.carRental.carRentalApp.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.carRental.carRentalApp.domains.Car;

public interface CarElasticRepository extends  ElasticsearchRepository<Car , String>{
	
	Car findByBrandName(String brandName );
	
	@Query("{\"bool\": {\"must\": [{\"match\": {\"cars.brandName\": \"?0\"}}]}}")
	Page<Car> findByBrandNameCustomQuery(String brandName, Pageable pageable );
	
}
*/