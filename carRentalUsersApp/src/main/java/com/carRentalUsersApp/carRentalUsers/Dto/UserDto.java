package com.carRentalUsersApp.carRentalUsers.Dto;

import org.springframework.hateoas.ResourceSupport;
import com.carRentalUsersApp.carRentalUsers.domains.User;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto extends ResourceSupport{
	
		@JsonUnwrapped
		private User user;


	}

