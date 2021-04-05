package com.pioneer.dips.symptomcategory.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.pioneer.dips.symptomcategory.model.SymptomCategoryModelAssembler;
import com.pioneer.dips.symptomcategory.repository.symptomCategoryRepository;
import com.pioneer.dips.symptoms.repository.symptomRepository;

@RestController
@RequestMapping(value = "/api/symptomcategory")
public class symptomCategoryController {
	
	private final symptomCategoryRepository repository;
	private final SymptomCategoryModelAssembler assembler; 
	
	  symptomCategoryController(symptomCategoryRepository repository, SymptomCategoryModelAssembler assembler) {
		    this.repository = repository;
		    this.assembler = assembler;
		  }
	  @CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/")
	public
	  CollectionModel<EntityModel<SymptomCategory>> all(){
		  List<EntityModel<SymptomCategory>> sc = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(sc,
				  linkTo(methodOn(symptomCategoryController.class).all()).withSelfRel());
	  }
	  
	  @PostMapping("/")
	  ResponseEntity<?> newSymptomCategory(@RequestBody SymptomCategory newSc ) {
		EntityModel<SymptomCategory> sc = assembler.toModel(repository.save(newSc));
		  return ResponseEntity
				  .created(sc.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(sc);
	   
	  }
	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<SymptomCategory> one(@PathVariable Long id) {
		  SymptomCategory sc = repository.findById(id)
				  .orElseThrow(() -> new SymptomCategoryNotFoundException(id));
		  return assembler.toModel(sc);
	  }
	  
	  @PutMapping("/{id}")
	  ResponseEntity<?> replaceSymptom(@RequestBody SymptomCategory newSc, @PathVariable Long id) {
		  SymptomCategory updatedSc = repository.findById(id)
				  .map(sc ->{
					  sc.setSymptomcategory_id(newSc.getSymptomcategory_id());
					  sc.setSymptomcategory_title(newSc.getSymptomcategory_title());
					  return repository.save(sc);
				  })
				  .orElseGet(() ->{
					  newSc.setSymptomcategory_id(id);
					  return repository.save(newSc);
				  });
		  EntityModel<SymptomCategory> sc = assembler.toModel(updatedSc);
		  
		  return ResponseEntity
				  .created(sc.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(sc);
					 
	  }
	  
	  @DeleteMapping("/{id}")
	  void deleteSymptomCategory(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
