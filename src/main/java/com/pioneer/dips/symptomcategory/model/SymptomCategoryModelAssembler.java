package com.pioneer.dips.symptomcategory.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.symptomcategory.controller.symptomCategoryController;
import com.pioneer.dips.symptoms.controller.symptomController;

@Component
public class SymptomCategoryModelAssembler implements RepresentationModelAssembler<SymptomCategory, EntityModel<SymptomCategory>> {

	@Override
	public EntityModel<SymptomCategory> toModel (SymptomCategory sc){
	
		return EntityModel.of(sc, 
				linkTo(methodOn(symptomCategoryController.class).one(sc.getSc_id())).withSelfRel(),
				linkTo(methodOn(symptomCategoryController.class).all()).withRel("symptomcategory"));
	}
}
