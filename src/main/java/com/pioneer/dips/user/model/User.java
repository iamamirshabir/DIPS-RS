package com.pioneer.dips.user.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pioneer.dips.appointment.model.Appointment;
import com.pioneer.dips.config.AuditModel;
import com.pioneer.dips.prescription.model.Prescription;

@Entity
@Table(name="userac")
public class User extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "userac_id")
	private long userac_id;
	
	@Column(name = "userac_name")
	private String userac_name;
	
	@Column(name = "userac_dob")
	private Date userac_dob;	
	
	@Column(name = "userac_mobile")
	private String userac_mobile;
	
	@Column(name = "userac_email")
	private String userac_email;
	
	@Column(name = "userac_reg")
	private boolean userac_reg;
	
	@Column(name = "userac_keycloak_id")
	private String userac_keycloak_id;	
	
	@Column(name = "userac_keycloak_username")
	private String userac_keycloak_username;	
	
	@OneToMany(mappedBy = "userac", cascade = {
	        CascadeType.ALL
	    })
	    private List < Prescription > prescription;
	
	@OneToMany(mappedBy = "userac", cascade = {
	        CascadeType.ALL
	    })
	    private List < Appointment > appointment;
	
		@Override
	  public int hashCode() {
	    return Objects.hash(this.userac_id, this.userac_name, this.userac_dob, this.userac_mobile, this.userac_email, this.userac_keycloak_id, this.userac_keycloak_username);
	  }

	  @Override
	  public String toString() {
	    return "USER{" + "id=" + this.userac_id + ", name='" + this.userac_name + ", dob='" + this.userac_dob+ ", mobile='" + this.userac_mobile + ", email='" + this.userac_email+ ", keycloak_id='" + this.userac_keycloak_id+ ", keycloak_username='" + this.userac_keycloak_username + '\''  + '}';
	  }

	  
	
	

	public User(long userac_id, String userac_name, Date userac_dob, String userac_mobile, String userac_email,
			boolean userac_reg, String userac_keycloak_id, String userac_keycloak_username,
			List<Prescription> prescription, List<Appointment> appointment) {
		super();
		this.userac_id = userac_id;
		this.userac_name = userac_name;
		this.userac_dob = userac_dob;
		this.userac_mobile = userac_mobile;
		this.userac_email = userac_email;
		this.userac_reg = userac_reg;
		this.userac_keycloak_id = userac_keycloak_id;
		this.userac_keycloak_username = userac_keycloak_username;
		this.prescription = prescription;
		this.appointment = appointment;
	}

	public User() {
		super();
	}

	@JsonIgnore	
	public List<Prescription> getPrescription() {
		return prescription;
	}
	
	
	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}
	
	
	public boolean isUserac_reg() {
		return userac_reg;
	}

	public void setUserac_reg(boolean userac_reg) {
		this.userac_reg = userac_reg;
	}

	@JsonIgnore	
	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public long getUserac_id() {
		return userac_id;
	}

	public void setUserac_id(long userac_id) {
		this.userac_id = userac_id;
	}

	public String getUserac_name() {
		return userac_name;
	}

	public void setUserac_name(String userac_name) {
		this.userac_name = userac_name;
	}

	public Date getUserac_dob() {
		return userac_dob;
	}

	public void setUserac_dob(Date userac_dob) {
		this.userac_dob = userac_dob;
	}

	public String getUserac_mobile() {
		return userac_mobile;
	}

	public void setUserac_mobile(String userac_mobile) {
		this.userac_mobile = userac_mobile;
	}

	public String getUserac_email() {
		return userac_email;
	}

	public void setUserac_email(String userac_email) {
		this.userac_email = userac_email;
	}

	public String getUserac_keycloak_id() {
		return userac_keycloak_id;
	}

	public void setUserac_keycloak_id(String userac_keycloak_id) {
		this.userac_keycloak_id = userac_keycloak_id;
	}

	public String getUserac_keycloak_username() {
		return userac_keycloak_username;
	}

	public void setUserac_keycloak_username(String userac_keycloak_useracname) {
		this.userac_keycloak_username = userac_keycloak_useracname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
