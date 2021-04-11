package com.pioneer.dips.symptoms.controller;

public class SymptomNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SymptomNotFoundException(Long id){
		super("Could not find symptom" + id);
	}
}
