package com.pioneer.dips.medicine.controller;

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

import com.pioneer.dips.medicine.model.Medicine;
import com.pioneer.dips.medicine.model.MedicineModelAssembler;
import com.pioneer.dips.medicine.repository.medicineRepository;
import com.pioneer.dips.prescription.model.Prescription;
import com.pioneer.dips.prescription.repository.prescriptionRepository;

@RestController
@RequestMapping(value = "/api/medicines")
public class medicineController {
	
	@Autowired
	private final medicineRepository repository;	
	
	@Autowired
	private final prescriptionRepository prepository ;	
	
	private final MedicineModelAssembler assembler; 
	
	
	  medicineController(medicineRepository repository, MedicineModelAssembler assembler, prescriptionRepository prepository) {
		    this.repository = repository;
		    this.assembler = assembler;
		    this.prepository = prepository;
		  }
	  @CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/")
	public
	  CollectionModel<EntityModel<Medicine>> all(){
		  List<EntityModel<Medicine>> medicines = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(medicines,
				  linkTo(methodOn(medicineController.class).all()).withSelfRel());
	  }
	  
	  @PostMapping("/prescription/{pId}")
	  ResponseEntity<?> newMedicine(@PathVariable Long pId, @RequestBody Medicine newMedicine ) {
			Optional<Prescription> optionalPrescription = prepository.findById(pId);
			 if (!optionalPrescription.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }
			 newMedicine.addPrescription(optionalPrescription.get());
			 optionalPrescription.get().addMedicine(newMedicine);
			 
		  EntityModel<Medicine> medicine = assembler.toModel(repository.save(newMedicine));
		  return ResponseEntity
				  .created(medicine.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(medicine);
	   
	  }
	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<Medicine> one(@PathVariable Long id) {
		  Medicine medicine = repository.findById(id)
				  .orElseThrow(() -> new MedicineNotFoundException(id));
		  return assembler.toModel(medicine);
	  }
	  
	  @PutMapping("/{id}")
	  ResponseEntity<?> replaceMedicine(@RequestBody Medicine newMedicine, @PathVariable Long id) {
		  Medicine updatedMedicine = repository.findById(id)
				  .map(medicine ->{
					  medicine.setMedicine_brand(newMedicine.getMedicine_brand());
					  medicine.setMedicine_composition(newMedicine.getMedicine_composition());
					  medicine.setMedicine_frequency(newMedicine.getMedicine_frequency());
					  medicine.setMedicine_dosage(newMedicine.getMedicine_dosage());
					  return repository.save(medicine);
				  })
				  .orElseGet(() ->{
					  newMedicine.setMedicine_id(id);
					  return repository.save(newMedicine);
				  });
		  EntityModel<Medicine> medicines = assembler.toModel(updatedMedicine);
		  
		  return ResponseEntity
				  .created(medicines.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(medicines);
					 
	  }
	  
	  @DeleteMapping("/{id}")
	  void deleteMedicine(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
