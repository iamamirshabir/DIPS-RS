package com.pioneer.dips.disease.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pioneer.dips.config.AuditModel;
import com.pioneer.dips.diseasecategory.model.Diseasecategory;
import com.pioneer.dips.symptomcategory.model.SymptomCategory;

@Entity
@Table(name="disease")
public class Disease extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595048583772454461L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "disease_id")
	private long disease_id;
	
	@Column(name = "disease_name")
	private String disease_name;	

	@Column(name = "disease_code")
	private String disease_code;
	
	@Column(name = "disease_details")
	private String disease_details;	
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diseasecategory_id")
    private Diseasecategory diseasecategory;
    
		@Override
	  public int hashCode() {
	    return Objects.hash(this.disease_id, this.disease_name, this.disease_code, this.disease_details);
	  }

	  @Override
	  public String toString() {
	    return "DISEASE{" + "id=" + this.disease_id + ", name='" + this.disease_name + ", code='" + this.disease_code + ", details='" + this.disease_details + '\''  + '}';
	  }

	public Disease(long disease_id, String disease_name, String disease_code, String disease_details,
			Diseasecategory diseasecategory) {
		super();
		this.disease_id = disease_id;
		this.disease_name = disease_name;
		this.disease_code = disease_code;
		this.disease_details = disease_details;
		this.diseasecategory = diseasecategory;
	}

	public long getDisease_id() {
		return disease_id;
	}

	public void setDisease_id(long disease_id) {
		this.disease_id = disease_id;
	}

	public String getDisease_name() {
		return disease_name;
	}

	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDisease_code() {
		return disease_code;
	}

	public void setDisease_code(String disease_code) {
		this.disease_code = disease_code;
	}

	public String getDisease_details() {
		return disease_details;
	}

	public void setDisease_details(String disease_details) {
		this.disease_details = disease_details;
	}

	public Diseasecategory getDiseasecategory() {
		return diseasecategory;
	}

	public void setDiseasecategory(Diseasecategory diseasecategory) {
		this.diseasecategory = diseasecategory;
	}
	

}