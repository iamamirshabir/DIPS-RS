package com.pioneer.dips.symptomcategory.controller;

public class SymptomCategoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SymptomCategoryNotFoundException(Long id){
		super("Could not find symptom category" + id);
	}
}
