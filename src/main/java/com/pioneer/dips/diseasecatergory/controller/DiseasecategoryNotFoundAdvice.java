package com.pioneer.dips.diseasecatergory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DiseasecategoryNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(DiseasecategoryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String diseasecategoryNotFoundHandler(DiseasecategoryNotFoundException ex) {
		return ex.getMessage();
	}
}
