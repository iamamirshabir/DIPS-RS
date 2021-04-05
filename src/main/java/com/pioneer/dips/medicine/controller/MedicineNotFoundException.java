package com.pioneer.dips.medicine.controller;

public class MedicineNotFoundException extends RuntimeException {

	public MedicineNotFoundException(Long id){
		super("Could not find medicine" + id);
	}
}
