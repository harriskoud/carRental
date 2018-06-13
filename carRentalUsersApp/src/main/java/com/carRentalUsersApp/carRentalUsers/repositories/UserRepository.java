package com.carRentalUsersApp.carRentalUsers.repositories;


import java.util.Optional;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.carRentalUsersApp.carRentalUsers.domains.User;

//public interface UserRepository extends CrudRepository<User, Integer> {
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	void deleteByUsername(String userame);

}
