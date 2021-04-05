package com.pioneer.dips.prescription.model;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pioneer.dips.appointment.model.Appointment;
import com.pioneer.dips.config.AuditModel;
import com.pioneer.dips.medicine.model.Medicine;
import com.pioneer.dips.physician.model.Physician;
import com.pioneer.dips.symptoms.model.Symptom;
import com.pioneer.dips.user.model.User;

@Entity
@Table(name="prescription")
public class Prescription extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595048583772454461L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "prescription_id")
	private long prescription_id;
	
	@Column(name = "prescription_notes")
	private String prescription_notes;	
	
	@Column(name = "prescription_diagnosis")
	private String prescription_diagnosis;	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userac_id")
	private User userac;
	
	@ManyToMany(mappedBy = "prescription")
    private List<Symptom> symptom;
		
	@ManyToMany(mappedBy = "prescription")
    private List<Medicine> medicine;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "physician_id")
    private Physician physician;
	
		@Override
	  public int hashCode() {
	    return Objects.hash(this.prescription_id, this.prescription_notes, this.prescription_diagnosis);
	  }

	  @Override
	  public String toString() {
	    return "PRESCRIPTION{" + "id=" + this.prescription_id + ", notes='" + this.prescription_notes + ", notes='" + this.prescription_notes + ", diagnosis='" + this.prescription_diagnosis + '\''  + '}';
	  }

	  
	public Prescription(long prescription_id, String prescription_notes, String prescription_diagnosis,
			Appointment appointment, User user, List<Symptom> symptom, List<Medicine> medicine, Physician physician) {
		super();
		this.prescription_id = prescription_id;
		this.prescription_notes = prescription_notes;
		this.prescription_diagnosis = prescription_diagnosis;
		this.appointment = appointment;
		this.userac	 = user;
		this.symptom = symptom;
		this.medicine = medicine;
		this.physician = physician;
	}

	
	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public User getUser() {
		return userac;
	}

	public void setUser(User user) {
		this.userac = user;
	}

	public List<Symptom> getSymptom() {
		return symptom;
	}

	public void setSymptom(List<Symptom> symptom) {
		this.symptom = symptom;
	}

	public List<Medicine> getMedicine() {
		return medicine;
	}

	public void setMedicine(List<Medicine> medicine) {
		this.medicine = medicine;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public long getPrescription_id() {
		return prescription_id;
	}

	public void setPrescription_id(long prescription_id) {
		this.prescription_id = prescription_id;
	}

	public String getPrescription_notes() {
		return prescription_notes;
	}

	public void setPrescription_notes(String prescription_notes) {
		this.prescription_notes = prescription_notes;
	}

	public String getPrescription_diagnosis() {
		return prescription_diagnosis;
	}

	public void setPrescription_diagnosis(String prescription_diagnosis) {
		this.prescription_diagnosis = prescription_diagnosis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
