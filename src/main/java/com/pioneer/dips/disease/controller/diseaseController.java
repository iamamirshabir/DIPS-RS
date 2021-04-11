package com.pioneer.dips.disease.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pioneer.dips.disease.model.Disease;
import com.pioneer.dips.disease.model.DiseaseModelAssembler;
import com.pioneer.dips.disease.repository.diseaseRepository;
import com.pioneer.dips.diseasecategory.model.Diseasecategory;
import com.pioneer.dips.diseasecategory.repository.diseasecategoryRepository;

@RestController
@RequestMapping(value = "/api/diseases")
public class diseaseController {
	
	@Autowired
	private final diseaseRepository repository;	
	
	@Autowired
	private final diseasecategoryRepository dcrepository;	
	private final DiseaseModelAssembler assembler; 
	

	  public diseaseController(diseaseRepository repository, diseasecategoryRepository dcrepository,
			DiseaseModelAssembler assembler) {
		super();
		this.repository = repository;
		this.dcrepository = dcrepository;
		this.assembler = assembler;
	}

	@CrossOrigin(origins = "http://localhost:8089") 
	@GetMapping("/")
	public
	  CollectionModel<EntityModel<Disease>> all(){
		  List<EntityModel<Disease>> diseases = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(diseases,
				  linkTo(methodOn(diseaseController.class).all()).withSelfRel());
	  }
	
	@CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/filterByName/")
	  public
	  ResponseEntity<?> getByEmail(@RequestParam(name = "name") String name){
		  Optional<Disease> optionalDisease = repository.findByName(name);
		  if (!optionalDisease.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }
		  return ResponseEntity.ok().body(assembler.toModel(optionalDisease.get()));
	   }
	
	  @CrossOrigin(origins = "http://localhost:8089")
	  @PostMapping("/diseasecategory/{dcId}")
	  ResponseEntity<?> newDisease(@PathVariable Long dcId, @RequestBody Disease newDisease ) {
		 Optional<Diseasecategory> optionalDiseaseCategory = dcrepository.findById(dcId);
			 if (!optionalDiseaseCategory.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }  
				newDisease.setDiseasecategory(optionalDiseaseCategory.get());	
		EntityModel<Disease> disease = assembler.toModel(repository.save(newDisease));
		  return ResponseEntity
				  .created(disease.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(disease);
	   
	  }
	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<Disease> one(@PathVariable Long id) {
		  Disease disease = repository.findById(id)
				  .orElseThrow(() -> new DiseaseNotFoundException(id));
		  return assembler.toModel(disease);
	  }
	  
	  @PutMapping("/{id}")
	  ResponseEntity<?> replaceDisease(@RequestBody Disease newDisease, @PathVariable Long id) {
		  Disease updatedDisease = repository.findById(id)
				  .map(disease ->{
					  disease.setDisease_name(newDisease.getDisease_name());
					  disease.setDisease_code(newDisease.getDisease_code());
					  disease.setDisease_details(newDisease.getDisease_details());
					  return repository.save(disease);
				  })
				  .orElseGet(() ->{
					  newDisease.setDisease_id(id);
					  return repository.save(newDisease);
				  });
		  EntityModel<Disease> diseases = assembler.toModel(updatedDisease);
		  
		  return ResponseEntity
				  .created(diseases.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(diseases);
					 
	  }
	  
	  @DeleteMapping("/{id}")
	  void deleteDisease(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
