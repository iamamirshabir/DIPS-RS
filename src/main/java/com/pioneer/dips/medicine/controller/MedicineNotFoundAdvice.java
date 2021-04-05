package com.pioneer.dips.medicine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MedicineNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(MedicineNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String medicineNotFoundHandler(MedicineNotFoundException ex) {
		return ex.getMessage();
	}
}
