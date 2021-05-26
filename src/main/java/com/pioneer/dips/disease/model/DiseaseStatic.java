package com.pioneer.dips.disease.model;

import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
public class DiseaseStatic {

	private final String disease_id;
	private final String disease_name;
	private final String disease_details;
	
	public DiseaseStatic(String disease_id, String disease_name, String disease_details) {
		super();
		this.disease_id = disease_id;
		this.disease_name = disease_name;
		this.disease_details = disease_details;
	}

	public String getDisease_id() {
		return disease_id;
	}

	public String getDisease_name() {
		return disease_name;
	}

	public String getDisease_details() {
		return disease_details;
	}
	
}

