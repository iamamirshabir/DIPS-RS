package com.pioneer.dips.appointment.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pioneer.dips.appointment.contorller.appointmentController;

@Component
public class AppointmentModelAssembler implements RepresentationModelAssembler<Appointment, EntityModel<Appointment>> {

	@Override
	public EntityModel<Appointment> toModel (Appointment appointment){
	
		return EntityModel.of(appointment, 
				linkTo(methodOn(appointmentController.class).one(appointment.getAppointment_id())).withSelfRel(),
				linkTo(methodOn(appointmentController.class).all()).withRel("appointment"));
	}
}
