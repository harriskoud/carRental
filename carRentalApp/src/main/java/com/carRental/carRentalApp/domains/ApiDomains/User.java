package com.carRental.carRentalApp.domains.ApiDomains;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
//@Table(name="users")
public class User implements Serializable{
	
	@Id
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;

}
