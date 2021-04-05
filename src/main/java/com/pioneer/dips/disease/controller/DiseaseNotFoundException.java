package com.pioneer.dips.disease.controller;

public class DiseaseNotFoundException extends RuntimeException {

	public DiseaseNotFoundException(Long id){
		super("Could not find disease" + id);
	}
}
