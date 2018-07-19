/*package com.carRental.carRentalApp.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.carRental.carRentalApp.domains.Car;
import com.carRental.carRentalApp.repositories.CarElasticRepository;

@Service
public class ElasticSearchService {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	@Autowired CarElasticRepository carElasticRepository;
	
	@PostConstruct
	private void postCon() {
		
		elasticsearchTemplate.createIndex(Car.class);
		elasticsearchTemplate.putMapping(Car.class);
		
		
	}
	
}
*/