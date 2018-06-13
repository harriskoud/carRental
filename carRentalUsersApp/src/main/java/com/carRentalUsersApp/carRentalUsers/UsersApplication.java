package com.carRentalUsersApp.carRentalUsers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;
import com.carRentalUsersApp.carRentalUsers.domains.User;
import com.carRentalUsersApp.carRentalUsers.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableDiscoveryClient
@RequiredArgsConstructor
@EnableFeignClients
public class UsersApplication implements CommandLineRunner {

	
	private final UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		log.debug("Inserting to DB");
		userRepository
				.save(User.builder().id(1).username("Harris").firstname("Charidimos").lastname("Koudoumas").build());
		userRepository
				.save(User.builder().id(2).username("Sifis").firstname("Iosif").lastname("Koudoumas").build());

	}

}
