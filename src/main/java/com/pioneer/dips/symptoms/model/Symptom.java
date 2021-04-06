package com.pioneer.dips.symptoms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pioneer.dips.config.AuditModel;
import com.pioneer.dips.prescription.model.Prescription;
import com.pioneer.dips.symptomcategory.model.SymptomCategory;

@Entity
@Table(name="symptom")
public class Symptom extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3180661069629760219L;
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "symptom_id")
	private long symptom_id;
	
	@Column(name = "symptom_text")
	private String symptom_text;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="symptomcategory_id")
    private SymptomCategory symptomcategory;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "symptom_prescription", 
      joinColumns = @JoinColumn(name = "symptom_id"), 
      inverseJoinColumns = @JoinColumn(name = "prescription_id"))
    private List<Prescription> prescription;
	
	public Symptom() {
		
	}
	
	public Symptom(long symptom_id, String symptom_text, SymptomCategory symptomcategory) {
		super();
		this.symptom_id = symptom_id;
		this.symptom_text = symptom_text;
		this.symptomcategory = symptomcategory;
	}
	
	public void addPrescription(Prescription p) {
		if(prescription == null) {
			prescription = new  ArrayList<Prescription>();
		}
		prescription.add(p);
	}

	public SymptomCategory getSymptomcategory() {
		return symptomcategory;
	}

	public void setSymptomcategory(SymptomCategory symptomcategory) {
		this.symptomcategory = symptomcategory;
	}

	@JsonIgnore
	public List<Prescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getSymptom_id() {
		return symptom_id;
	}

	public void setSymptom_id(long symptom_id) {
		this.symptom_id = symptom_id;
	}

	public String getSymptom_text() {
		return symptom_text;
	}

	public void setSymptom_text(String symptom_text) {
		this.symptom_text = symptom_text;
	}

	public SymptomCategory getSymptom_category() {
		return symptomcategory;
	}

	public void setSymptom_category(SymptomCategory symptomcategory) {
		this.symptomcategory = symptomcategory;
	}

	@Override
	  public int hashCode() {
	    return Objects.hash(this.symptom_id, this.symptom_text, this.symptomcategory);
	  }

	  @Override
	  public String toString() {
	    return "SYMPTOM{" + "id=" + this.symptom_id + ", name='" + this.symptom_text + '\'' + ", category='" + this.symptomcategory + '\'' + '}';
	  }
	
	
}
