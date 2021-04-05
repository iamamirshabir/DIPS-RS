package com.pioneer.dips.medicine.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.medicine.controller.medicineController;


@Component
public class MedicineModelAssembler implements RepresentationModelAssembler<Medicine, EntityModel<Medicine>> {

	@Override
	public EntityModel<Medicine> toModel (Medicine medicine){
	
		return EntityModel.of(medicine, 
				linkTo(methodOn(medicineController.class).one(medicine.getMedicine_id())).withSelfRel(),
				linkTo(methodOn(medicineController.class).all()).withRel("medicine"));
	}
}
