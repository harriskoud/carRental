package com.carRentalReservation.carRentalReservationApp.resources;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.carRentalReservation.carRentalReservationApp.Dto.CarReservationAssembler;
import com.carRentalReservation.carRentalReservationApp.Dto.CarReservationDto;
import com.carRentalReservation.carRentalReservationApp.domains.CarReservation;
import com.carRentalReservation.carRentalReservationApp.domains.ApiDomains.Car;
import com.carRentalReservation.carRentalReservationApp.domains.ApiDomains.User;
import com.carRentalReservation.carRentalReservationApp.proxy.EurekaCarInvocation;
import com.carRentalReservation.carRentalReservationApp.proxy.EurekaUserInvocation;
import com.carRentalReservation.carRentalReservationApp.proxy.ReservationProxy;
import com.carRentalReservation.carRentalReservationApp.repositories.CarReservationRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/ui/reservations", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@RequiredArgsConstructor

public class CarReservationResource {

	private final CarReservationRepository carReservationRepository;
	private final CarReservationAssembler carReservationAssembler;
	private final ReservationProxy reservationProxy;
	private final EurekaCarInvocation eurekaCarInvocation;
	private final EurekaUserInvocation eurekaUserInvocation;

	@GetMapping("{reservationId}")
	public @ResponseBody ResponseEntity<?> getReservation(@PathVariable(name = "reservationId") int reservationId) {

		Optional<CarReservation> oCarReservation = carReservationRepository.findById(reservationId);
		return oCarReservation.map(c -> ResponseEntity.ok(carReservationAssembler.toResource(c)))
				.orElse(ResponseEntity.notFound().build());
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	PagedResources<CarReservationDto> get(@PageableDefault Pageable p, PagedResourcesAssembler pagedAssembler) {
		Page<CarReservation> u = carReservationRepository.findAll(p);
		return pagedAssembler.toResource(u, carReservationAssembler);
	}

	@DeleteMapping("{reservationId}")
	public @ResponseBody ResponseEntity<?> deleteReservation(@PathVariable(name = "reservationId") int reservationId) {

		carReservationRepository.deleteById(reservationId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<?> createCarReservation(@RequestBody CarReservation carReservation) {
		return ResponseEntity.ok(carReservationAssembler.toResource(carReservationRepository.save(carReservation)));
	}

	@PutMapping()
	public @ResponseBody ResponseEntity<?> updateCar(@RequestBody CarReservation carReservation) {
		return ResponseEntity.ok(carReservationAssembler.toResource(carReservationRepository.save(carReservation)));
	}

	@GetMapping("create-new-reservation/user/{username}/car/{brandName}")
	public @ResponseBody ResponseEntity<?> getReservation(@PathVariable(name = "username") String username,
			@PathVariable(name = "brandName") String brandName) {

		// ResponseEntity<Car> carEntity = new
		// RestTemplate().exchange("http://car-microservice/car-microservice/ui/car/{brandName}",HttpMethod.GET,
		// null,Car.class,brandName);
		ResponseEntity<Car> carEntity = reservationProxy.getCarInfo(brandName);
		ResponseEntity<User> userEntity = reservationProxy.getUserInfo(username);
		return ResponseEntity.ok(carReservationAssembler.toResource(carReservationRepository.save(CarReservation
				.builder().carBrand(brandName).bookedBy(username).bookedOn(java.time.LocalDate.now()).build())));
	}

	@GetMapping("create-new-reservation-eureka/user/{username}/car/{brandName}")
	public @ResponseBody ResponseEntity<?> getReservationEureka(@PathVariable(name = "username") String username,
			@PathVariable(name = "brandName") String brandName) {

		ResponseEntity<Car> carEntity = eurekaCarInvocation.getCarInfo(brandName);
		ResponseEntity<User> userEntity = eurekaUserInvocation.getUserInfo(username);
		return ResponseEntity.ok(carReservationAssembler.toResource(carReservationRepository.save(CarReservation
				.builder().carBrand(brandName).bookedBy(username).bookedOn(java.time.LocalDate.now()).build())));
	}

	public ResponseEntity<?> hystrixTest(String username, String brandName) {
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/new")
	public @ResponseBody ResponseEntity<?> newReservation(HttpServletRequest request) {
		String corlId = request.getHeader("CORRELATION_ID");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("CORRELATION_ID", corlId);
		
		ResponseEntity<Car> carEntity = new RestTemplate().postForEntity("http://localhost:8080/car-microservice/ui/car/test/BMW", params,Car.class);
		//ResponseEntity<User> userEntity = new RestTemplate().getForEntity("http://localhost:8082/user-microservice/ui/user/Harris", User.class);
		return ResponseEntity.ok().build();
			
	}

}
