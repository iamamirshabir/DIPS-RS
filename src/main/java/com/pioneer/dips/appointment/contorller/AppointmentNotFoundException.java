package com.pioneer.dips.appointment.contorller;

public class AppointmentNotFoundException extends RuntimeException {

	public AppointmentNotFoundException(Long id){
		super("Could not find appointment" + id);
	}
}
