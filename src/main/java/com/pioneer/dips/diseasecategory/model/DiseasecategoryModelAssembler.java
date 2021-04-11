package com.pioneer.dips.diseasecategory.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.diseasecategory.controller.diseasecategoryController;


@Component
public class DiseasecategoryModelAssembler implements RepresentationModelAssembler<Diseasecategory, EntityModel<Diseasecategory>> {

	@Override
	public EntityModel<Diseasecategory> toModel (Diseasecategory diseasecategory){
	
		return EntityModel.of(diseasecategory, 
				linkTo(methodOn(diseasecategoryController.class).one(diseasecategory.getDiseasecategory_id())).withSelfRel(),
				linkTo(methodOn(diseasecategoryController.class).all()).withRel("diseasecategory"));
	}
}
