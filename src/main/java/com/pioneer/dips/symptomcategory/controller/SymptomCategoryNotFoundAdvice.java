package com.pioneer.dips.symptomcategory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SymptomCategoryNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(SymptomCategoryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String symptomNotFoundHandler(SymptomCategoryNotFoundException ex) {
		return ex.getMessage();
	}
}
