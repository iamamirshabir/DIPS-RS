package com.pioneer.dips.prescription.controller;

public class PrescriptionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PrescriptionNotFoundException(Long id){
		super("Could not find prescription" + id);
	}
}
