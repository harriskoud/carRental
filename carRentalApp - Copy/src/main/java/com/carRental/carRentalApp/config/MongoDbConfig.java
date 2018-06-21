package com.carRental.carRentalApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.carRental.carRentalApp.repositories")
public class MongoDbConfig {

}
