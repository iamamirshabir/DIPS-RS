package com.pioneer.dips.prescription.controller;

public class PrescriptionNotFoundException extends RuntimeException {

	public PrescriptionNotFoundException(Long id){
		super("Could not find prescription" + id);
	}
}
