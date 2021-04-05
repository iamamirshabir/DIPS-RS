package com.pioneer.dips.diseasecatergory.controller;

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

import com.pioneer.dips.diseasecategory.model.Diseasecategory;
import com.pioneer.dips.diseasecategory.model.DiseasecategoryModelAssembler;
import com.pioneer.dips.diseasecatergory.repository.diseasecategoryRepository;


@RestController
@RequestMapping(value = "/api/diseasecategorys")
public class diseasecategoryController {
	
	@Autowired
	private final diseasecategoryRepository repository;	
	private final DiseasecategoryModelAssembler assembler; 
	
	  diseasecategoryController(diseasecategoryRepository repository, DiseasecategoryModelAssembler assembler) {
		    this.repository = repository;
		    this.assembler = assembler;
		  }
	  @CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/")
	public
	  CollectionModel<EntityModel<Diseasecategory>> all(){
		  List<EntityModel<Diseasecategory>> diseasecategorys = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(diseasecategorys,
				  linkTo(methodOn(diseasecategoryController.class).all()).withSelfRel());
	  }
	  
	  @PostMapping("/")
	  ResponseEntity<?> newDiseasecategory(@RequestBody Diseasecategory newDiseasecategory ) {
		EntityModel<Diseasecategory> diseasecategory = assembler.toModel(repository.save(newDiseasecategory));
		  return ResponseEntity
				  .created(diseasecategory.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(diseasecategory);
	   
	  }
	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<Diseasecategory> one(@PathVariable Long id) {
		  Diseasecategory diseasecategory = repository.findById(id)
				  .orElseThrow(() -> new DiseasecategoryNotFoundException(id));
		  return assembler.toModel(diseasecategory);
	  }
	  
	  @PutMapping("/{id}")
	  ResponseEntity<?> replaceDiseasecategory(@RequestBody Diseasecategory newDiseasecategory, @PathVariable Long id) {
		  Diseasecategory updatedDiseasecategory = repository.findById(id)
				  .map(diseasecategory ->{
					  diseasecategory.setDiseasecategory_title(newDiseasecategory.getDiseasecategory_title());
					  return repository.save(diseasecategory);
				  })
				  .orElseGet(() ->{
					  newDiseasecategory.setDiseasecategory_id(id);
					  return repository.save(newDiseasecategory);
				  });
		  EntityModel<Diseasecategory> diseasecategorys = assembler.toModel(updatedDiseasecategory);
		  
		  return ResponseEntity
				  .created(diseasecategorys.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(diseasecategorys);
					 
	  }
	  
	  @DeleteMapping("/{id}")
	  void deleteDiseasecategory(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
