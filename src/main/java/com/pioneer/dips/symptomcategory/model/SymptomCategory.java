package com.pioneer.dips.symptomcategory.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pioneer.dips.config.AuditModel;
import com.pioneer.dips.symptoms.model.Symptom;

@Entity
@Table(name="symptomcategory")
public class SymptomCategory extends AuditModel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6737769080625739911L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "symptomcategory_id")
	private long symptomcategory_id;
	
	@Column(name = "symptomcategory_title")
	private String symptomcategory_title;
	
	@OneToMany(mappedBy = "symptomcategory", cascade = {
	        CascadeType.ALL
	    }, fetch = FetchType.LAZY)
    private List<Symptom> symptom;	
	
	public List<Symptom> getSymptoms() {
		return symptom;
	}

		public SymptomCategory(long symptomcategory_id, String symptomcategory_title, List<Symptom> symptoms) {
		super();
		this.symptomcategory_id = symptomcategory_id;
		this.symptomcategory_title = symptomcategory_title;
		this.symptom = symptoms;
	}

		public long getSymptomcategory_id() {
		return symptomcategory_id;
	}

	public void setSymptomcategory_id(long symptomcategory_id) {
		this.symptomcategory_id = symptomcategory_id;
	}

	public String getSymptomcategory_title() {
		return symptomcategory_title;
	}

	public void setSymptomcategory_title(String symptomcategory_title) {
		this.symptomcategory_title = symptomcategory_title;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptom = symptoms;
	}

		@Override
	  public int hashCode() {
	    return Objects.hash(this.symptomcategory_id, this.symptomcategory_title);
	  }

	  @Override
	  public String toString() {
	    return "SYMPTOM{" + "id=" + this.symptomcategory_id + ", name='" + this.symptomcategory_title + '\'' + '}';
	  }
	
	
}
