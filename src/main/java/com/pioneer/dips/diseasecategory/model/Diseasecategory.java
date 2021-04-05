package com.pioneer.dips.diseasecategory.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pioneer.dips.config.AuditModel;
import com.pioneer.dips.disease.model.Disease;
import com.pioneer.dips.symptomcategory.model.SymptomCategory;

@Entity
@Table(name="diseasecategory")
public class Diseasecategory extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595048583772454461L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "diseasecategory_id")
	private long diseasecategory_id;
	
	@Column(name = "diseasecategory_title")
	private String diseasecategory_title;	
	
	@OneToMany(mappedBy = "diseasecategory", cascade = {
	        CascadeType.ALL
	    })
	    private List <Disease> disease;
	
		@Override
	  public int hashCode() {
	    return Objects.hash(this.diseasecategory_id, this.diseasecategory_title);
	  }

	  @Override
	  public String toString() {
	    return "DISEASECATEGORY{" + "id=" + this.diseasecategory_id + ", on='" + this.diseasecategory_title + '\''  + '}';
	  }
	  
	public Diseasecategory(long diseasecategory_id, String diseasecategory_title, List<Disease> disease) {
			super();
			this.diseasecategory_id = diseasecategory_id;
			this.diseasecategory_title = diseasecategory_title;
			this.disease = disease;
	}

	public long getDiseasecategory_id() {
		return diseasecategory_id;
	}

	public void setDiseasecategory_id(long diseasecategory_id) {
		this.diseasecategory_id = diseasecategory_id;
	}

	public String getDiseasecategory_title() {
		return diseasecategory_title;
	}

	public List<Disease> getDisease() {
		return disease;
	}

	public void setDisease(List<Disease> disease) {
		this.disease = disease;
	}

	public void setDiseasecategory_title(String diseasecategory_title) {
		this.diseasecategory_title = diseasecategory_title;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
