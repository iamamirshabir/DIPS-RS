package com.pioneer.dips.physician.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.physician.contorller.physicianController;

@Component
public class PhysicianModelAssembler implements RepresentationModelAssembler<Physician, EntityModel<Physician>> {

	@Override
	public EntityModel<Physician> toModel (Physician physician){
	
		return EntityModel.of(physician, 
				linkTo(methodOn(physicianController.class).one(physician.getPhysician_id())).withSelfRel(),
				linkTo(methodOn(physicianController.class).all()).withRel("physician"));
	}
}
