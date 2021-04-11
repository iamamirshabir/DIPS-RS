package com.pioneer.dips.diseasecategory.controller;

public class DiseasecategoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DiseasecategoryNotFoundException(Long id){
		super("Could not find diseasecategory" + id);
	}
}
