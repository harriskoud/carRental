package com.carRental.carRentalApp;



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.RequiredArgsConstructor;


@Component
//@Slf4j
@RequiredArgsConstructor
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class ApplicationRepository implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		/*try {
			log.debug("Valisation Starts");
			Optional<Car> oCar = carRep.findOneByBrandName("AUDI");
			if (!oCar.isPresent()) {
				carRep.save(Car.builder().brandName("AUDI").carId(12).build());
			}
		} catch (Exception e) {
			log.error("Fail to initialaze DB",e);
		}
		 carRep.save(new Car(2,"FIAT"));
		 carRep.save(new Car(3,"VW"));
		//carElasticRepository.save(new Car(1,"FORD"));
		//carElasticRepository.save(Car.builder().brandName("CITROEN").build());
		//carElasticRepository.save(Car.builder().brandName("FORD").build());
*/	}
}
