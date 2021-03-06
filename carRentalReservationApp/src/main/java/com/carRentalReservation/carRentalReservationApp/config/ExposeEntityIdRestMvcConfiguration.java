package com.carRentalReservation.carRentalReservationApp.config;


import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.carRentalReservation.carRentalReservationApp.domains.CarReservation;


@Component
public class ExposeEntityIdRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(CarReservation.class);
  }
}