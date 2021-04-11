package com.pioneer.dips.physician.model;

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
@Table(name="physician")
public class Physician extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595048583772454461L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "physician_id")
	private long physician_id;
	
	@Column(name = "physician_name")
	private String physician_name;	
	
	@Column(name = "physician_email")
	private String physician_email;	
	
	@Column(name = "physician_spec")
	private String physician_spec;	

	@Column(name = "physician_address")
	private String physician_address;	

	@Column(name = "physician_max_daily")
	private int physician_max_daily;
	
	@Column(name = "physician_visit_days")
	private String physician_visit_days;	
	
	@Column(name = "physician_availability")
	private String physician_availability;

	@Column(name = "physician_time_start")
	private int physician_time_start;	

	@Column(name = "physician_time_end")
	private int physician_time_end;	

	@Column(name = "physician_keycloak_id")
	private String physician_keycloak_id;	

	@Column(name = "physician_keycloak_username")
	private String physician_keycloak_username;
	
	@OneToMany(mappedBy = "physician", cascade = {
	        CascadeType.ALL
	    })
	    private List < Prescription > prescription;
	@OneToMany(mappedBy = "physician", cascade = {
	        CascadeType.ALL
	    })
	    private List < Appointment > appointment;
	
	
		@Override
	  public int hashCode() {
	    return Objects.hash(this.physician_id, this.physician_spec, this.physician_address, this.physician_visit_days, this.physician_time_start, this.physician_time_end, this.physician_keycloak_id, this.physician_keycloak_username );
	  }

	  @Override
	  public String toString() {
	    return "PHYSICIAN{" + "id=" + this.physician_id + ", spec='" + this.physician_spec + ", address='" + this.physician_address + ", visit_days='" + this.physician_visit_days + ", time_start='" + this.physician_time_start + ", time_end='" + this.physician_time_end + ", keycloak_id='" + this.physician_keycloak_id + ", keycloak_username='" + this.physician_keycloak_username + '\''  + '}';
	  }


	
	




	public Physician(long physician_id, String physician_name, String physician_email, String physician_spec,
			String physician_address, int physician_max_daily, String physician_visit_days,
			String physician_availability, int physician_time_start, int physician_time_end,
			String physician_keycloak_id, String physician_keycloak_username, List<Prescription> prescription,
			List<Appointment> appointment) {
		super();
		this.physician_id = physician_id;
		this.physician_name = physician_name;
		this.physician_email = physician_email;
		this.physician_spec = physician_spec;
		this.physician_address = physician_address;
		this.physician_max_daily = physician_max_daily;
		this.physician_visit_days = physician_visit_days;
		this.physician_availability = physician_availability;
		this.physician_time_start = physician_time_start;
		this.physician_time_end = physician_time_end;
		this.physician_keycloak_id = physician_keycloak_id;
		this.physician_keycloak_username = physician_keycloak_username;
		this.prescription = prescription;
		this.appointment = appointment;
	}

	public Physician() {
		super();
	}	
	
	
	public int getPhysician_max_daily() {
		return physician_max_daily;
	}

	public void setPhysician_max_daily(int physician_max_daily) {
		this.physician_max_daily = physician_max_daily;
	}

	public String getPhysician_availability() {
		return physician_availability;
	}

	public void setPhysician_availability(String physician_availability) {
		this.physician_availability = physician_availability;
	}

	public String getPhysician_email() {
		return physician_email;
	}

	public void setPhysician_email(String physician_email) {
		this.physician_email = physician_email;
	}

	@JsonIgnore	
	public List<Prescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}
	
	@JsonIgnore	
	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public long getPhysician_id() {
		return physician_id;
	}

	public void setPhysician_id(long physician_id) {
		this.physician_id = physician_id;
	}

	public String getPhysician_name() {
		return physician_name;
	}

	public void setPhysician_name(String physician_name) {
		this.physician_name = physician_name;
	}

	public String getPhysician_spec() {
		return physician_spec;
	}

	public void setPhysician_spec(String physician_spec) {
		this.physician_spec = physician_spec;
	}

	public String getPhysician_address() {
		return physician_address;
	}

	public void setPhysician_address(String physician_address) {
		this.physician_address = physician_address;
	}

	public String getPhysician_visit_days() {
		return physician_visit_days;
	}

	public void setPhysician_visit_days(String physician_visit_days) {
		this.physician_visit_days = physician_visit_days;
	}

	public int getPhysician_time_start() {
		return physician_time_start;
	}

	public void setPhysician_time_start(int physician_time_start) {
		this.physician_time_start = physician_time_start;
	}

	public int getPhysician_time_end() {
		return physician_time_end;
	}

	public void setPhysician_time_end(int physician_time_end) {
		this.physician_time_end = physician_time_end;
	}

	public String getPhysician_keycloak_id() {
		return physician_keycloak_id;
	}

	public void setPhysician_keycloak_id(String physician_keycloak_id) {
		this.physician_keycloak_id = physician_keycloak_id;
	}

	public String getPhysician_keycloak_username() {
		return physician_keycloak_username;
	}

	public void setPhysician_keycloak_username(String physician_keycloak_username) {
		this.physician_keycloak_username = physician_keycloak_username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	  
}

