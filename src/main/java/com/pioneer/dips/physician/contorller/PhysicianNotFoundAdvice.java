package com.pioneer.dips.physician.contorller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PhysicianNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(PhysicianNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String prescriprionNotFoundHandler(PhysicianNotFoundException ex) {
		return ex.getMessage();
	}
}
