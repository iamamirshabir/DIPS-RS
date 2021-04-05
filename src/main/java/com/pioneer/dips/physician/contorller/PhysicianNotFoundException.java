package com.pioneer.dips.physician.contorller;

public class PhysicianNotFoundException extends RuntimeException {

	public PhysicianNotFoundException(Long id){
		super("Could not find physician" + id);
	}
}
