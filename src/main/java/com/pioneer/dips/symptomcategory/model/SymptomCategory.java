package com.pioneer.dips.symptomcategory.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblsymptomcategory")
public class SymptomCategory {

	@Column(name = "sc_id")
	private @Id @GeneratedValue(strategy = GenerationType.AUTO)	
	long sc_id;
	@Column(name = "sc_title")
	private String sc_title;
	
	
	public SymptomCategory() {
		
	}
	
		  public long getSc_id() {
		return sc_id;
	}

	public void setSc_id(long sc_id) {
		this.sc_id = sc_id;
	}

	public SymptomCategory(long sc_id, String sc_title) {
		super();
		this.sc_id = sc_id;
		this.sc_title = sc_title;
	}

	public String getSc_title() {
		return sc_title;
	}

	public void setSc_title(String sc_title) {
		this.sc_title = sc_title;
	}

		@Override
	  public int hashCode() {
	    return Objects.hash(this.sc_id, this.sc_title);
	  }

	  @Override
	  public String toString() {
	    return "SYMPTOM{" + "id=" + this.sc_id + ", name='" + this.sc_title + '\'' + '}';
	  }
	
	
}
