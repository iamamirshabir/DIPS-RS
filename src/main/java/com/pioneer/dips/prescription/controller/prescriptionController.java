package com.pioneer.dips.prescription.controller;

import java.util.List;
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

import com.pioneer.dips.prescription.model.Prescription;
import com.pioneer.dips.prescription.model.PrescriptionModelAssembler;
import com.pioneer.dips.prescription.repository.prescriptionRepository;

@RestController
@RequestMapping(value = "/api/prescriptions")
public class prescriptionController {
	
	@Autowired
	private final prescriptionRepository repository;	
	private final PrescriptionModelAssembler assembler; 
	
	  prescriptionController(prescriptionRepository repository, PrescriptionModelAssembler assembler) {
		    this.repository = repository;
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
	  
	  @PostMapping("/")
	  ResponseEntity<?> newPrescription(@RequestBody Prescription newPrescription ) {
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
	  
	  @DeleteMapping("/{id}")
	  void deletePrescription(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
