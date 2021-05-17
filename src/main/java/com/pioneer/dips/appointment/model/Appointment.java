package com.pioneer.dips.appointment.model;

import java.util.Date;
import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pioneer.dips.config.AuditModel;
import com.pioneer.dips.physician.model.Physician;
import com.pioneer.dips.prescription.model.Prescription;
import com.pioneer.dips.user.model.User;

@Entity
@Table(name="appointment")
public class Appointment extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595048583772454461L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "appointment_id")
	private long appointment_id;
	
	@Column(name = "appointment_on")
	private Date appointment_on;	
	
    @OneToOne(mappedBy = "appointment")
    private Prescription prescription;
    
    @ManyToOne
	@JoinColumn(name = "userac_id")
	private User userac;
    
    @ManyToOne
    @JoinColumn(name = "physician_id")
    private Physician physician;
	
		@Override
	  public int hashCode() {
	    return Objects.hash(this.appointment_id, this.appointment_on);
	  }

	  @Override
	  public String toString() {
	    return "SYMPTOM{" + "id=" + this.appointment_id + ", on='" + this.appointment_on + '\''  + '}';
	  }

	public Appointment(long appointment_id, Date appointment_on, Prescription prescription, User user,
			Physician physician) {
		super();
		this.appointment_id = appointment_id;
		this.appointment_on = appointment_on;
		this.prescription = prescription;
		this.userac = user;
		this.physician = physician;
	}
	

	public Appointment() {
		super();
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public User getUser() {
		return userac;
	}

	public void setUser(User user) {
		this.userac = user;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(long appointment_id) {
		this.appointment_id = appointment_id;
	}

	public Date getAppointment_on() {
		return appointment_on;
	}

	public void setAppointment_on(Date appointment_on) {
		this.appointment_on = appointment_on;
	}
	
	
}
