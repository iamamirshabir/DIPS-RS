package com.pioneer.dips.disease.controller;

public class DiseaseNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DiseaseNotFoundException(Long id){
		super("Could not find disease" + id);
	}
}
