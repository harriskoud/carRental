package com.carRentalReservation.carRentalReservationApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRentalReservation.carRentalReservationApp.domains.CarReservation;

public interface CarReservationRepository extends JpaRepository<CarReservation, Integer>{

}
