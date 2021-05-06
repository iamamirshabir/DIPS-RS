package com.pioneer.dips.physician.contorller;

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

import com.pioneer.dips.physician.model.Physician;
import com.pioneer.dips.physician.model.PhysicianModelAssembler;
import com.pioneer.dips.physician.repository.physicianRepository;

@RestController
@RequestMapping(value = "/api/physicians")
public class physicianController {
	
	@Autowired
	private final physicianRepository repository;	
	private final PhysicianModelAssembler assembler; 
	
	  physicianController(physicianRepository repository, PhysicianModelAssembler assembler) {
		    this.repository = repository;
		    this.assembler = assembler;
		  }
	  
	  @CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/")
	public
	  CollectionModel<EntityModel<Physician>> all(){
		  List<EntityModel<Physician>> physicians = repository.findAllReg().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(physicians,
				  linkTo(methodOn(physicianController.class).all()).withSelfRel());
	  }
	  
	  @CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/keycloak/")
	  public
	  ResponseEntity<?> getByKeycloakID(@RequestParam(name="keycloak") String keycloak_id){
		  Optional<Physician> optionalPhysician = repository.findByKeycloakID(keycloak_id);
		  if (!optionalPhysician.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }
		  return ResponseEntity.ok().body(assembler.toModel(optionalPhysician.get()));
	   }
	  @CrossOrigin(origins = "http://localhost:8089") 
	  @PostMapping("/")
	  ResponseEntity<?> newPhysician(@RequestBody Physician newPhysician ) {
		EntityModel<Physician> physician = assembler.toModel(repository.save(newPhysician));
		  return ResponseEntity
				  .created(physician.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(physician);
	   
	  }
	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<Physician> one(@PathVariable Long id) {
		  Physician physician = repository.findById(id)
				  .orElseThrow(() -> new PhysicianNotFoundException(id));
		  return assembler.toModel(physician);
	  }
	  
	  @PutMapping("/{id}")
	  ResponseEntity<?> replacePhysician(@RequestBody Physician newPhysician, @PathVariable Long id) {
		  Physician updatedPhysician = repository.findById(id)
				  .map(physician ->{
					  physician.setPhysician_name(newPhysician.getPhysician_name());
					  physician.setPhysician_email(newPhysician.getPhysician_email());
					  physician.setPhysician_spec(newPhysician.getPhysician_spec());
					  physician.setPhysician_address(newPhysician.getPhysician_address());
					  physician.setPhysician_visit_days(newPhysician.getPhysician_visit_days());
					  physician.setPhysician_time_start(newPhysician.getPhysician_time_start());
					  physician.setPhysician_reg_status(newPhysician.isPhysician_reg_status());
					  physician.setPhysician_time_end(newPhysician.getPhysician_time_end());
					  physician.setPhysician_keycloak_id(newPhysician.getPhysician_keycloak_id());
					  physician.setPhysician_keycloak_username(newPhysician.getPhysician_keycloak_username());
					  return repository.save(physician);
				  })
				  .orElseGet(() ->{
					  newPhysician.setPhysician_id(id);
					  return repository.save(newPhysician);
				  });
		  EntityModel<Physician> physicians = assembler.toModel(updatedPhysician);
		  
		  return ResponseEntity
				  .created(physicians.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(physicians);
					 
	  }
	  
	  @DeleteMapping("/{id}")
	  void deletePhysician(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
