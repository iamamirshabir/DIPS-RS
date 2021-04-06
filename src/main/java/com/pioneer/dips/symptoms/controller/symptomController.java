package com.pioneer.dips.symptoms.controller;

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

import com.pioneer.dips.symptomcategory.model.SymptomCategory;
import com.pioneer.dips.symptomcategory.repository.symptomCategoryRepository;
import com.pioneer.dips.symptoms.model.Symptom;
import com.pioneer.dips.symptoms.model.SymptomModelAssembler;
import com.pioneer.dips.symptoms.repository.symptomRepository;

@RestController
@RequestMapping(value = "/api/symptoms")
public class symptomController {
	
	@Autowired
	private final symptomRepository repository;	
	
	@Autowired
	private final symptomCategoryRepository symptomcategoryrepository;	
	
	
	private final SymptomModelAssembler assembler; 
	
	  symptomController(symptomRepository repository, SymptomModelAssembler assembler,symptomCategoryRepository symptomcategoryrepository) {
		    this.repository = repository;
		    this.assembler = assembler;
		    this.symptomcategoryrepository = symptomcategoryrepository;
		  }
	  @CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/")
	public
	  CollectionModel<EntityModel<Symptom>> all(){
		  List<EntityModel<Symptom>> symptoms = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(symptoms,
				  linkTo(methodOn(symptomController.class).all()).withSelfRel());
	  }
	  
	  @PostMapping("/symptomcategory/{scId}")
	  ResponseEntity<?> newSymptom(@PathVariable Long scId,@RequestBody Symptom newSymptom ) {
		Optional<SymptomCategory> optionalSymptomCategory = symptomcategoryrepository.findById(scId);
		 if (!optionalSymptomCategory.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }
		newSymptom.setSymptom_category(optionalSymptomCategory.get());	
		EntityModel<Symptom> symptom = assembler.toModel(repository.save(newSymptom));
		  return ResponseEntity
				  .created(symptom.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(symptom);
	   
	  }
	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<Symptom> one(@PathVariable Long id) {
		  Symptom symptom = repository.findById(id)
				  .orElseThrow(() -> new SymptomNotFoundException(id));
		  return assembler.toModel(symptom);
	  }
	  
	  @PutMapping("/{id}")
	  ResponseEntity<?> replaceSymptom(@RequestBody Symptom newSymptom, @PathVariable Long id) {
		  Optional<SymptomCategory> optionalSymptomCategory = symptomcategoryrepository.findById(newSymptom.getSymptom_category().getSymptomcategory_id());
			 if (!optionalSymptomCategory.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }
			 				
		  Symptom updatedSymptom = repository.findById(id)
				  .map(symptom ->{
					  symptom.setSymptom_text(newSymptom.getSymptom_text());
					  symptom.setSymptom_category(newSymptom.getSymptom_category());
					  symptom.setSymptom_category(optionalSymptomCategory.get());
					  return repository.save(symptom);
				  })
				  .orElseGet(() ->{
					  newSymptom.setSymptom_id(id);
					  newSymptom.setSymptom_category(optionalSymptomCategory.get());					  
					  return repository.save(newSymptom);
				  });
		  EntityModel<Symptom> symptoms = assembler.toModel(updatedSymptom);
		  
		  return ResponseEntity
				  .created(symptoms.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(symptoms);
					 
	  }
	  
	  @DeleteMapping("/{id}")
	  ResponseEntity<?> deleteSymptom(@PathVariable Long id) throws SymptomNotFoundException {
	  repository.deleteById(id);
	  return ResponseEntity.noContent().build();
	  }
	  

}

