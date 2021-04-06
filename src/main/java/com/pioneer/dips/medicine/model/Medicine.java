package com.pioneer.dips.medicine.model;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name="medicine")
public class Medicine extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595048583772454461L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "medicine_id")
	private long medicine_id;
	
	@Column(name = "medicine_brand")
	private String medicine_brand;	
	
	@Column(name = "medicine_composition")
	private String medicine_composition;	
	
	@Column(name = "medicine_frequency")
	private int medicine_frequency;	
	
	@Column(name = "medicine_dosage")
	private int medicine_dosage;	
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medicine_prescription", 
      joinColumns = @JoinColumn(name = "medicine_id"), 
      inverseJoinColumns = @JoinColumn(name = "prescription_id"))
    private List<Prescription> prescription= new ArrayList <Prescription>();
	
	
		@Override
	  public int hashCode() {
	    return Objects.hash(this.medicine_id, this.medicine_brand,this.medicine_composition,this.medicine_frequency,this.medicine_dosage);
	  }

	  @Override
	  public String toString() {
	    return "Medicine{" + "id=" + this.medicine_id + ", brand='" + this.medicine_brand +", composition='" + this.medicine_composition +", frequency='" + this.medicine_frequency + ", dosage='" + this.medicine_dosage + '\''  + '}';
	  }

	public Medicine(long medicine_id, String medicine_brand, String medicine_composition, int medicine_frequency,
			int medicine_dosage, List<Prescription> prescription) {
		super();
		this.medicine_id = medicine_id;
		this.medicine_brand = medicine_brand;
		this.medicine_composition = medicine_composition;
		this.medicine_frequency = medicine_frequency;
		this.medicine_dosage = medicine_dosage;
		this.prescription = prescription;
	}
	
	
	public Medicine() {
		super();
	}

	public void addPrescription(Prescription p) {
		if(prescription == null) {
			prescription = new ArrayList<Prescription>();
		}
		this.prescription.add(p);
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

	public long getMedicine_id() {
		return medicine_id;
	}

	public void setMedicine_id(long medicine_id) {
		this.medicine_id = medicine_id;
	}

	public String getMedicine_brand() {
		return medicine_brand;
	}

	public void setMedicine_brand(String medicine_brand) {
		this.medicine_brand = medicine_brand;
	}

	public String getMedicine_composition() {
		return medicine_composition;
	}

	public void setMedicine_composition(String medicine_composition) {
		this.medicine_composition = medicine_composition;
	}

	public int getMedicine_frequency() {
		return medicine_frequency;
	}

	public void setMedicine_frequency(int medicine_frequency) {
		this.medicine_frequency = medicine_frequency;
	}

	public int getMedicine_dosage() {
		return medicine_dosage;
	}

	public void setMedicine_dosage(int medicine_dosage) {
		this.medicine_dosage = medicine_dosage;
	}
}