package com.carRentalUsersApp.carRentalUsers.Dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.carRentalUsersApp.carRentalUsers.domains.User;
import com.carRentalUsersApp.carRentalUsers.resources.UserResource;

import lombok.Data;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserDto> {

	public UserResourceAssembler() {
		super(UserResource.class, UserDto.class);
	}

	@Override
	public UserDto toResource(User user) {
		UserDto userDto = createResourceWithId(user.getUsername(), user);
		 
		Link invoiceLink = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(UserResource.class).getUser(user.getUsername())).withRel("user");
		userDto.setUser(user);
		userDto.add(invoiceLink);
		return userDto;
	}

}
