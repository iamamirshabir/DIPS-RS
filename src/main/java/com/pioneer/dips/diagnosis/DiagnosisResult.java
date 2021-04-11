package com.pioneer.dips.diagnosis;

import java.util.Arrays;

public class DiagnosisResult {
	
	private int classIndex;
	
	private String instanceString;
	
	private String[] potentialDiseases = new String[3] ;
	
	private double[] diseaseProb = new double[3];
	
	public DiagnosisResult() {
		super();
	}

	public DiagnosisResult(int classIndex, String instanceString, String[] potentialDiseases, double[] diseaseProb) {
		super();
		this.classIndex = classIndex;
		this.instanceString = instanceString;
		this.potentialDiseases = potentialDiseases;
		this.diseaseProb = diseaseProb;
	}
	
	

	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}

	public String getInstanceString() {
		return instanceString;
	}

	public void setInstanceString(String instanceString) {
		this.instanceString = instanceString;
	}

	public String[] getPotentialDiseases() {
		return potentialDiseases;
	}

	public void setPotentialDiseases(String[] potentialDiseases) {
		this.potentialDiseases = potentialDiseases;
	}

	public double[] getDiseaseProb() {
		return diseaseProb;
	}

	public void setDiseaseProb(double[] diseaseProb) {
		this.diseaseProb = diseaseProb;
	}

	@Override
	public String toString() {
		return "DiagnosisResult [classIndex=" + classIndex + ", instanceString=" + instanceString
				+ ", potentialDiseases=" + Arrays.toString(potentialDiseases) + ", diseaseProb="
				+ Arrays.toString(diseaseProb) + "]";
	}
	
	
	
	
	
}
