package com.pioneer.dips.disease.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.disease.controller.diseaseController;



@Component
public class DiseaseModelAssembler implements RepresentationModelAssembler<Disease, EntityModel<Disease>> {

	@Override
	public EntityModel<Disease> toModel (Disease disease){
	
		return EntityModel.of(disease, 
				linkTo(methodOn(diseaseController.class).one(disease.getDisease_id())).withSelfRel(),
				linkTo(methodOn(diseaseController.class).all()).withRel("disease"));
	}
}
