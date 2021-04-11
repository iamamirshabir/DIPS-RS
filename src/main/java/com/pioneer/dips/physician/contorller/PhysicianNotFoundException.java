package com.pioneer.dips.physician.contorller;

public class PhysicianNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhysicianNotFoundException(Long id){
		super("Could not find physician" + id);
	}
}
