package com.pioneer.dips.user.controller;

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

import com.pioneer.dips.user.model.User;
import com.pioneer.dips.user.model.UserModelAssembler;
import com.pioneer.dips.user.repository.userRepository;

@RestController
@RequestMapping(value = "/api/users")
public class userController {

	@Autowired
	private final userRepository repository;
	private final UserModelAssembler assembler;

	  userController(userRepository repository, UserModelAssembler assembler) {
		    this.repository = repository;
		    this.assembler = assembler;
		  }
@CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/")
	  public
	  CollectionModel<EntityModel<User>> all(){
		  List<EntityModel<User>> users = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(users,
				  linkTo(methodOn(userController.class).all()).withSelfRel());
	  }

	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/keycloak/")
	  public
	  ResponseEntity<?> getByKeycloakID(@RequestParam(name="keycloak") String keycloak){
		  Optional<User> optionalUser = repository.findByKeycloakID(keycloak);
		  if (!optionalUser.isPresent()) {
	            return ResponseEntity.unprocessableEntity().build();
	        }
		  return ResponseEntity.ok().body(assembler.toModel(optionalUser.get()));
	   }

	  @CrossOrigin(origins = "http://localhost:8089")
	  @PostMapping("/")
	  ResponseEntity<?> newUser(@RequestBody User newUser ) {
		EntityModel<User> user = assembler.toModel(repository.save(newUser));
		  return ResponseEntity
				  .created(user.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(user);
	  }

	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<User> one(@PathVariable Long id) {
		  User user = repository.findById(id)
				  .orElseThrow(() -> new UserNotFoundException(id));
		  return assembler.toModel(user);
	  }

	  @CrossOrigin(origins = "http://localhost:8089")
	  @PutMapping("/{id}")
	  ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
		  User updatedUser = repository.findById(id)
				  .map(user ->{
					  user.setUserac_name(newUser.getUserac_name());
					  user.setUserac_dob(newUser.getUserac_dob());
					  user.setUserac_mobile(newUser.getUserac_mobile());
					  user.setUserac_email(newUser.getUserac_email());
					  user.setUserac_reg(newUser.isUserac_reg());
					  user.setUserac_keycloak_id(newUser.getUserac_keycloak_id());
					  user.setUserac_keycloak_username(newUser.getUserac_keycloak_username());
					  return repository.save(user);
				  })
				  .orElseGet(() ->{
					  newUser.setUserac_id(id);
					  return repository.save(newUser);
				  });
		  EntityModel<User> users = assembler.toModel(updatedUser);

		  return ResponseEntity
				  .created(users.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(users);

	  }

	  @DeleteMapping("/{id}")
	  void deleteUser(@PathVariable Long id) {
	    repository.deleteById(id);
	  }


}
