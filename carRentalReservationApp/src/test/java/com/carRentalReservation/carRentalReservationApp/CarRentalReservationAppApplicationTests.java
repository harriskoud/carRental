package com.carRentalReservation.carRentalReservationApp;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.carRentalReservation.carRentalReservationApp.domains.CarReservation;
import com.carRentalReservation.carRentalReservationApp.repositories.CarReservationRepository;
import com.carRentalReservation.carRentalReservationApp.resources.CarReservationResource;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarRentalReservationAppApplicationTests {

	@Mock
	private CarReservationResource carReservationResource;
	@Mock
	CarReservationRepository carReservationRepository;// not injected null
	private MockMvc mockMvc;

	private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ"
			+ "qb2huLmNhcm5lbGwiLCJzY29wZSI6WyJ3ZWJjbGllbnQiXSwiZXh"
			+ "wIjoxNTMyNDAyOTQxLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianR"
			+ "pIjoiMWExNTY5Y2MtMGU3Ni00YTEyLWFjNzgtMzllZjI1YTAyZjNjIiwiY2xpZW50X"
			+ "2lkIjoidXNlci1taWNyb3NlcnZpY2UiLCJ1c2VybmFtZSI6ImpvaG4uY2FybmVsbCJ9.8M"
			+ "GzcE7V5_jaVFWzvdzkRxDkZGvlefHlCUm03Eqo99o";

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.standaloneSetup(carReservationResource).build();
		MockMvcBuilders.standaloneSetup(carReservationRepository).build();

	}

	@Test
	public void getReservationsTest() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		List<CarReservation> reservations = carReservationRepository.findAll();
		System.out.println(reservations);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/ui/reservations").header("Authorization", "Bearer " + token))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

	}

}
