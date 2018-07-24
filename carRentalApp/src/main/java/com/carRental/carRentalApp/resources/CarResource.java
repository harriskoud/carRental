package com.carRental.carRentalApp.resources;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
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
import com.carRental.carRentalApp.exception.CarNotFoundException;
import com.carRental.carRentalApp.proxy.EurekaUserInvocation;
import com.carRental.carRentalApp.repositories.CarRepository;
import com.carRental.carRentalApp.resources.dto.CarDto;
import com.carRental.carRentalApp.resources.dto.CarResourceAssembler;
import com.carRental.carRentalApp.services.CarServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/ui/car", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class CarResource {

	private final CarRepository carRep;
	private final CarResourceAssembler carResourceAssembler;
	private final CarServices carService;
	private final EurekaUserInvocation eurekaUserInvocation;
	// private final CarElasticRepository carElasticRepository;
	private static Logger log = LoggerFactory.getLogger(CarResource.class);
	// private final ElasticsearchTemplate elasticsearchTemplate;

	@GetMapping("{brandName}")
	public @ResponseBody ResponseEntity<?> getCar(@PathVariable(name = "brandName") String brandname) {

		log.info("Get Car Info");
		Optional<Car> oCar = carRep.findOneByBrandName(brandname);
		if (!oCar.isPresent()) {
			throw new CarNotFoundException("BrandName-" + brandname);
		}
		
		return oCar.map(c -> ResponseEntity.ok(carResourceAssembler.toResource(c)))
				.orElse(ResponseEntity.notFound().build());
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	PagedResources<CarDto> get(@PageableDefault Pageable p, PagedResourcesAssembler pagedAssembler) {
		Page<Car> u = carRep.findAll(p);
		return pagedAssembler.toResource(u, carResourceAssembler);
	}

	@DeleteMapping("{brandName}")
	public String deleteCar(@PathVariable(name = "brandName") String brandname) {

		carRep.deleteByBrandName(brandname);
		return "Deletion Completed";
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<?> createCar(@RequestBody Car car) {
		return ResponseEntity.ok(carResourceAssembler.toResource(carRep.save(car)));
	}

	@GetMapping("/updateDate/car/{brandName}/date/{date}")
	public @ResponseBody ResponseEntity<?> updateBookedDateGet(@PathVariable(name = "brandName") String brandname,
			@PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
		log.info("Update Car Booked Date");

		Instant instant = fromDate.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		Optional<Car> oCar = carRep.findOneByBrandName(brandname);
		oCar.get().setBookedDate(zdt.toLocalDate());
		carRep.delete(oCar.get());
		carRep.save(oCar.get());
		return oCar.map(c -> ResponseEntity.ok(carResourceAssembler.toResource(c)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping()
	public @ResponseBody ResponseEntity<?> updateBookedDatePut(@RequestBody Car car) {
		Optional<Car> oCar = carRep.findOneByBrandName(car.getBrandName());
		carRep.delete(oCar.get());
		return ResponseEntity.ok(carResourceAssembler.toResource(carRep.save(car)));
	}

	@RequestMapping("/list")
	public ResponseEntity<?> getListOfCars() {
		return ResponseEntity.ok(carService.getListOfCars());
	}

	@RequestMapping("/list1")
	public ResponseEntity<?> getListOfCarsWithHystrixThreadPool() {
		return ResponseEntity.ok(carService.getListOfCarsWithHystrixThreadPool());
	}

	@GetMapping("/test/{brandName}")
	public @ResponseBody ResponseEntity<?> getCarFromUser(@PathVariable(name = "brandName") String brandname) {

		Optional<Car> oCar = carRep.findOneByBrandName(brandname);
		return oCar.map(c -> ResponseEntity.ok(carResourceAssembler.toResource(c)))
				.orElse(ResponseEntity.notFound().build());
	}

	/*
	 * @GetMapping("/elastic/{brandName}") public @ResponseBody ResponseEntity<?>
	 * getCarWithElastic(@PathVariable(name = "brandName") String brandname) {
	 * 
	 * log.info("Get Car Info"); Car car =
	 * carElasticRepository.findByBrandName(brandname); return
	 * ResponseEntity.ok(ResponseEntity.ok(carResourceAssembler.toResource(car))); }
	 * 
	 * @PutMapping("/elastic/{brandName}/id/{carid}") public String
	 * newCarWithElastic(@PathVariable(name = "brandName") String brandname,
	 * 
	 * @PathVariable(name = "carid") Integer carid) {
	 * 
	 * log.info("Create New Car"); Car car =
	 * carElasticRepository.save(Car.builder().brandName(brandname).build()); return
	 * car.toString(); }
	 * 
	 * @GetMapping("/elasticCustom/{brandName}") public @ResponseBody
	 * ResponseEntity<?> newCarWithElasticCustom(@PathVariable(name = "brandName")
	 * String brandname) {
	 * 
	 * log.info("Create New Car"); // Page<Car> car =
	 * carElasticRepository.findByBrandNameCustomQuery(brandname, // new
	 * PageRequest(1, 10)); List<Car> cars = findByBrandNameCustomQuery(brandname);
	 * cars.stream().map(c -> c.getBrandName()).forEach(System.out::println); return
	 * cars.size() == 0 ? ResponseEntity.notFound().build() :
	 * ResponseEntity.ok(cars); }
	 * 
	 * private List<Car> findByBrandNameCustomQuery(String brandName) {
	 * 
	 * SearchResponse response =
	 * elasticsearchTemplate.getClient().prepareSearch().setTypes()
	 * .setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setPostFilter(QueryBuilders.
	 * matchAllQuery()) // .setPostFilter(QueryBuilders.simpleQueryStringQuery("A"))
	 * .execute().actionGet(); List<SearchHit> searchHits =
	 * Arrays.asList(response.getHits().getHits()); List<Car> cars = new
	 * ArrayList<Car>();
	 * 
	 * searchHits.forEach(hit -> cars.add(JSON.parseObject(hit.getSourceAsString(),
	 * Car.class))); return cars; }
	 */

}
