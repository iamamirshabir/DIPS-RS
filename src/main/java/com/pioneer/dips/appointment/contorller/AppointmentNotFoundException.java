package com.pioneer.dips.appointment.contorller;

public class AppointmentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppointmentNotFoundException(Long id){
		super("Could not find appointment" + id);
	}
}
