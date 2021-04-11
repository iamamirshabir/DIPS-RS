package com.pioneer.dips.medicine.controller;

public class MedicineNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MedicineNotFoundException(Long id){
		super("Could not find medicine" + id);
	}
}
