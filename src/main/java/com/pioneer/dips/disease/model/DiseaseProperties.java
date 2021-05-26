package com.pioneer.dips.disease.model;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("static")
public class DiseaseProperties {
	
	private final List<DiseaseStatic> diseases;

	
	public DiseaseProperties(List<DiseaseStatic> diseases) {
		super();
		this.diseases = diseases;
	}


	public List<DiseaseStatic> getDiseases() {
		return diseases;
	}
	

}
