package com.pioneer.dips.diseasecategory.controller;

public class DiseasecategoryNotFoundException extends RuntimeException {

	public DiseasecategoryNotFoundException(Long id){
		super("Could not find diseasecategory" + id);
	}
}
