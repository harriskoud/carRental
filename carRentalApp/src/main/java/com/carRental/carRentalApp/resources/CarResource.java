package com.carRental.carRentalApp.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
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

import com.carRental.carRentalApp.domains.Car;
import com.carRental.carRentalApp.repositories.CarRepository;
import com.carRental.carRentalApp.resources.dto.CarDto;
import com.carRental.carRentalApp.resources.dto.CarResourceAssembler;
import com.carRental.carRentalApp.services.CarServices;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/ui/car", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class CarResource {

	private final CarRepository carRep;
	private final CarResourceAssembler carResourceAssembler;
	private final CarServices carService;
/*	@Value("${info.fname}")
	private String fname;*/

	@GetMapping("{brandName}")
	public @ResponseBody ResponseEntity<?> getCar(@PathVariable(name = "brandName") String brandname) {

		Optional<Car> oCar = carRep.findOneByBrandName(brandname);
		// return oCar.map(c->
		// ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
		/*
		 * if(oCar.isPresent()){ return oCar.get(); }else { return null; }
		 */
		return oCar.map(c -> ResponseEntity.ok(carResourceAssembler.toResource(c)))
				.orElse(ResponseEntity.notFound().build());
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	PagedResources<CarDto> get(@PageableDefault Pageable p, PagedResourcesAssembler pagedAssembler) {
		Page<Car> u = carRep.findAll(p);
		return pagedAssembler.toResource(u, carResourceAssembler);
	}

	@DeleteMapping("{brandName}")
	public @ResponseBody ResponseEntity<?> deleteCar(@PathVariable(name = "brandName") String brandname) {

		carRep.deleteByBrandName(brandname);
		return ResponseEntity.noContent().build();
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<?> createCar(@RequestBody Car car) {
		return ResponseEntity.ok(carResourceAssembler.toResource(carRep.save(car)));
	}
	
	@PutMapping()
	public @ResponseBody ResponseEntity<?> updateCar(@RequestBody Car car) {
		return ResponseEntity.ok(carResourceAssembler.toResource(carRep.save(car)));
	}
	
/*	@GetMapping("/info")
	@Timed(value = "removeService")
	public String getActiveProfile() {
		return String.format("Hello!  You're %s ", fname);
	}*/
	
	@RequestMapping("/list")
	public ResponseEntity<?>  getListOfCars(){
		return ResponseEntity.ok(carService.getListOfCars());
	}
	
	@RequestMapping("/list1")
	public ResponseEntity<?> getListOfCarsWithHystrixThreadPool(){
		return ResponseEntity.ok(carService.getListOfCarsWithHystrixThreadPool());
	}
	

}
