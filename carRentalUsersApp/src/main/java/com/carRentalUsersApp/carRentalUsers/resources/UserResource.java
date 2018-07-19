package com.carRentalUsersApp.carRentalUsers.resources;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
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
import org.springframework.web.client.RestTemplate;

import com.carRentalUsersApp.carRentalUsers.Dto.UserDto;
import com.carRentalUsersApp.carRentalUsers.Dto.UserResourceAssembler;
import com.carRentalUsersApp.carRentalUsers.domains.User;
import com.carRentalUsersApp.carRentalUsers.domains.ApiDomains.Car;
import com.carRentalUsersApp.carRentalUsers.repositories.UserRepository;
import com.carRentalUsersApp.carRentalUsers.services.UserServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/ui/user", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class UserResource {
	
	private final UserRepository userRepository;
	private final UserResourceAssembler userResourceAssembler;
	private final UserServices userServices;
	@Autowired
	RestTemplate rest;
	
	 private static Logger log = LoggerFactory.getLogger(UserResource.class);

	
	@GetMapping("{username}")
	public @ResponseBody ResponseEntity<?> getUser(@PathVariable(name="username") String username) {
		
		log.info("Get User Info");
		Optional<User> oUser = userRepository.findByUsername(username);
		Car car = rest.getForObject("http://localhost:8080/ui/car/AUDI", Car.class);
		return oUser.map(u -> ResponseEntity.ok(userResourceAssembler.toResource(u))).orElse(ResponseEntity.notFound().build());
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	PagedResources<UserDto> get(@PageableDefault Pageable p, PagedResourcesAssembler pagedAssembler) {
		Page<User> u = userRepository.findAll(p);
		return pagedAssembler.toResource(u, userResourceAssembler);
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<?> saveUser(@RequestBody User user){
		return ResponseEntity.ok(userResourceAssembler.toResource(userRepository.save(user)));
		
	}
	
	@DeleteMapping("{userame}")
	public @ResponseBody ResponseEntity<?> deleteCar(@PathVariable(name = "userame") String userame) {

		userRepository.deleteByUsername(userame);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping()
	public @ResponseBody ResponseEntity<?> createCar(@RequestBody User user) {
		return ResponseEntity.ok(userResourceAssembler.toResource(userRepository.save(user)));	}
	

	
	

}
