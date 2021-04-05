package com.pioneer.dips.diseasecatergory.controller;

public class DiseasecategoryNotFoundException extends RuntimeException {

	public DiseasecategoryNotFoundException(Long id){
		super("Could not find diseasecategory" + id);
	}
}
