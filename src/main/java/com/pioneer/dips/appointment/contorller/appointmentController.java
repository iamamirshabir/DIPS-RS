package com.pioneer.dips.appointment.contorller;

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

import com.pioneer.dips.appointment.model.Appointment;
import com.pioneer.dips.appointment.model.AppointmentModelAssembler;
import com.pioneer.dips.appointment.repository.appointmentRepository;



@RestController
@RequestMapping(value = "/api/appointments")
public class appointmentController {
	
	@Autowired
	private final appointmentRepository repository;	
	private final AppointmentModelAssembler assembler; 
	
	  appointmentController(appointmentRepository repository, AppointmentModelAssembler assembler) {
		    this.repository = repository;
		    this.assembler = assembler;
		  }
	  @CrossOrigin(origins = "http://localhost:8089") 
	  @GetMapping("/")
	public
	  CollectionModel<EntityModel<Appointment>> all(){
		  List<EntityModel<Appointment>> appointments = repository.findAll().stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return CollectionModel.of(appointments,
				  linkTo(methodOn(appointmentController.class).all()).withSelfRel());
	  }
	  
	  @PostMapping("/")
	  ResponseEntity<?> newAppointment(@RequestBody Appointment newAppointment ) {
		EntityModel<Appointment> appointment = assembler.toModel(repository.save(newAppointment));
		  return ResponseEntity
				  .created(appointment.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(appointment);
	   
	  }
	  @CrossOrigin(origins = "http://localhost:8089")
	  @GetMapping("/{id}")
	public
	  EntityModel<Appointment> one(@PathVariable Long id) {
		  Appointment appointment = repository.findById(id)
				  .orElseThrow(() -> new AppointmentNotFoundException(id));
		  return assembler.toModel(appointment);
	  }
	  
	  @PutMapping("/{id}")
	  ResponseEntity<?> replaceAppointment(@RequestBody Appointment newAppointment, @PathVariable Long id) {
		  Appointment updatedAppointment = repository.findById(id)
				  .map(appointment ->{
					  appointment.setAppointment_on(newAppointment.getAppointment_on());
					  return repository.save(appointment);
				  })
				  .orElseGet(() ->{
					  newAppointment.setAppointment_id(id);
					  return repository.save(newAppointment);
				  });
		  EntityModel<Appointment> appointments = assembler.toModel(updatedAppointment);
		  
		  return ResponseEntity
				  .created(appointments.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(appointments);
					 
	  }
	  
	  @DeleteMapping("/{id}")
	  void deleteAppointment(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	  

}
