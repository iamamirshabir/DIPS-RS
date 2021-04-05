package com.pioneer.dips.prescription.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.prescription.controller.prescriptionController;

@Component
public class PrescriptionModelAssembler implements RepresentationModelAssembler<Prescription, EntityModel<Prescription>> {

	@Override
	public EntityModel<Prescription> toModel (Prescription prescription){
	
		return EntityModel.of(prescription, 
				linkTo(methodOn(prescriptionController.class).one(prescription.getPrescription_id())).withSelfRel(),
				linkTo(methodOn(prescriptionController.class).all()).withRel("prescription"));
	}
}
