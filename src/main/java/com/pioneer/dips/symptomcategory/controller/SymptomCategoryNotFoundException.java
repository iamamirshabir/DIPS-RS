package com.pioneer.dips.symptomcategory.controller;

public class SymptomCategoryNotFoundException extends RuntimeException {

	public SymptomCategoryNotFoundException(Long id){
		super("Could not find symptom category" + id);
	}
}
