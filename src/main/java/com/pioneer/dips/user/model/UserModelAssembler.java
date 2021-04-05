package com.pioneer.dips.user.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.user.controller.userController;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

	@Override
	public EntityModel<User> toModel (User user){
	
		return EntityModel.of(user, 
				linkTo(methodOn(userController.class).one(user.getUserac_id())).withSelfRel(),
				linkTo(methodOn(userController.class).all()).withRel("userac"));
	}
}
