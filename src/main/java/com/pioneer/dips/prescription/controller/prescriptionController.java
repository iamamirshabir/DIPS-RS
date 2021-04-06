package com.pioneer.dips.prescription.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pioneer.dips.appointment.model.Appointment;
import com.pioneer.dips.appointment.repository.appointmentRepository;
import com.pioneer.dips.physician.model.Physician;
import com.pioneer.dips.physician.repository.physicianRepository;
import com.pioneer.dips.prescription.model.Prescription;
import com.pioneer.dips.prescription.model.PrescriptionModelAssembler;
import com.pioneer.dips.prescription.repository.prescriptionRepository;
import com.pioneer.dips.symptomcategory.model.SymptomCategory;
import com.pioneer.dips.symptoms.model.Symptom;
import com.pioneer.dips.symptoms.repository.symptomRepository;
import com.pioneer.dips.user.model.User;
import com.pioneer.dips.user.repository.userRepository;

@RestController
@RequestMapping(value = "/api/prescriptions")
public class prescriptionController {
	
	@Autowired
	private final prescriptionRepository repository;
	
	@Autowired
	private final appointmentRepository arepository;
	
	@Autowired
	private final userRepository urepository;
	
	@Autowired
	private final physicianRepository prepository;
	
	@Autowired
	private final symptomRepository srepository;
	
	private final PrescriptionModelAssembler assembler; 
	
	
	  
	public prescriptionController(prescriptionRepository repository, appointmentRepository arepository,
			userRepository urepository, physicianRepository prepository, symptomRepository srepository,
			PrescriptionModelAssembler assembler) {
		super();
		this.repository = repository;
		this.arepository = arepository;
		this.urepository = urepository;
		this.prepository = prepository;
		this.srepository = srepository;
		this.assembler = assembler;
	}

	@CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/")
	public
	  CollectionModel<EntityModel<Prescription>> all(){
		  List<EntityModel<Prescription>> prescriptions = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(prescriptions,
				  linkTo(methodOn(prescriptionController.class).all()).withSelfRel());
	  }
	  
	  @PostMapping("/user/{uId}/physician/{pId}/appointment/{aId}")
	  ResponseEntity<?> newPrescription(@RequestBody Prescription newPrescription, @PathVariable Long uId, @PathVariable Long pId, @PathVariable Long aId  ) {
		  Optional<User> optionalUser = urepository.findById(uId);
			 if (!optionalUser.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }
			 Optional<Physician> optionalPhysician = prepository.findById(pId);
			 if (!optionalPhysician.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }
			 Optional<Appointment> optionalAppointment = arepository.findById(aId);
			 if (!optionalAppointment.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }
			 newPrescription.setUser(optionalUser.get());
			 newPrescription.setPhysician(optionalPhysician.get());
			 newPrescription.setAppointment(optionalAppointment.get());
		  EntityModel<Prescription> prescription = assembler.toModel(repository.save(newPrescription));
		  return ResponseEntity
				  .created(prescription.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(prescription);
	   
	  }
	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<Prescription> one(@PathVariable Long id) {
		  Prescription prescription = repository.findById(id)
				  .orElseThrow(() -> new PrescriptionNotFoundException(id));
		  return assembler.toModel(prescription);
	  }
	  
	  @PutMapping("/{id}")
	  ResponseEntity<?> replacePrescription(@RequestBody Prescription newPrescription, @PathVariable Long id) {
		  Prescription updatedPrescription = repository.findById(id)
				  .map(prescription ->{
					  prescription.setPrescription_notes(newPrescription.getPrescription_notes());
					  prescription.setPrescription_diagnosis(newPrescription.getPrescription_diagnosis());
					  return repository.save(prescription);
				  })
				  .orElseGet(() ->{
					  newPrescription.setPrescription_id(id);
					  return repository.save(newPrescription);
				  });
		  EntityModel<Prescription> prescriptions = assembler.toModel(updatedPrescription);
		  
		  return ResponseEntity
				  .created(prescriptions.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(prescriptions);
					 
	  }
	  
	  @PutMapping("/{id}/symptom/{sId}")
	  ResponseEntity<?> updatePrescription(@RequestBody Prescription newPrescription, @PathVariable Long id, @PathVariable Long sId) {
		  Optional<Symptom> optionalSymptom = srepository.findById(sId);
			 if (!optionalSymptom.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }
		  Prescription updatedPrescription = repository.findById(id)
				  .map(prescription ->{
					  prescription.addSymptom(optionalSymptom.get());
					  optionalSymptom.get().addPrescription(prescription);
					  return repository.save(prescription);
				  })
				  .orElseGet(() ->{
					  newPrescription.setPrescription_id(id);
					  return repository.save(newPrescription);
				  });
		  EntityModel<Prescription> prescriptions = assembler.toModel(updatedPrescription);
		  
		  return ResponseEntity
				  .created(prescriptions.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(prescriptions);
					 
	  }
	  
	  @DeleteMapping("/{id}")
	  void deletePrescription(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
