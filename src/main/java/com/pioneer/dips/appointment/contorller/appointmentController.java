package com.pioneer.dips.appointment.contorller;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

import com.pioneer.dips.appointment.model.Appointment;
import com.pioneer.dips.appointment.model.AppointmentModelAssembler;
import com.pioneer.dips.appointment.repository.appointmentRepository;
import com.pioneer.dips.physician.model.Physician;
import com.pioneer.dips.physician.repository.physicianRepository;
import com.pioneer.dips.user.model.User;
import com.pioneer.dips.user.repository.userRepository;



@RestController
@RequestMapping(value = "/api/appointments")
public class appointmentController {
	
	@Autowired
	private final appointmentRepository repository;	
	
	@Autowired
	private final userRepository urepository;
	
	@Autowired
	private final physicianRepository prepository;
	
	private final AppointmentModelAssembler assembler; 
	
	  
	  public appointmentController(appointmentRepository repository, userRepository urepository,
			physicianRepository prepository, AppointmentModelAssembler assembler) {
		super();
		this.repository = repository;
		this.urepository = urepository;
		this.prepository = prepository;
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
	
	@CrossOrigin(origins = "http://localhost:8089") 
	  @PostMapping("physician/{pId}/getByDate/")
	public
	  CollectionModel<EntityModel<Appointment>> getByDate(@PathVariable Long pId, @RequestBody String date){
		Optional<Physician> optionalPhysician = prepository.findById(pId);
		 if (!optionalPhysician.isPresent()) {
	            return CollectionModel.empty();
	        }  
		
		List<EntityModel<Appointment>> appointments = repository.findByDate(Date.from(Instant.parse(date)), pId).stream()
				  .map(assembler :: toModel)
				  .collect(Collectors.toList());
		  return 	CollectionModel.of(appointments,
				  linkTo(methodOn(appointmentController.class).all()).withSelfRel());
	  }
	
	@CrossOrigin(origins = "http://localhost:8089") 
	  @PostMapping("/user/{uId}/physician/{pId}")
	  ResponseEntity<?> newAppointment(@RequestBody Appointment newAppointment, @PathVariable Long uId, @PathVariable Long pId ) {
		  Optional<User> optionalUser = urepository.findById(uId);
			 if (!optionalUser.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }			 
			 Optional<Physician> optionalPhysician = prepository.findById(pId);
			 if (!optionalPhysician.isPresent()) {
		            return ResponseEntity.unprocessableEntity().build();
		        }
			 List<Appointment> appointments = repository.findByDate(newAppointment.getAppointment_on(), pId);
			 if(appointments.size() == optionalPhysician.get().getPhysician_max_daily()) {
				 int days[] = new int[7] ;
				 int day = 0;
				 days=Stream.of(optionalPhysician.get().getPhysician_availability().split(","))
				 	.mapToInt(Integer::parseInt)
	                .toArray();
				 System.out.println("Before "+days);
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(newAppointment.getAppointment_on());
				 day= cal.get(Calendar.DAY_OF_WEEK);
				 System.out.println("Day "+ day);
				 days[day-1] = 0;
				 System.out.println("After "+IntStream.of(days)
                 .mapToObj(Integer::toString)
                 .collect(Collectors.joining(",")));
				 optionalPhysician.get().setPhysician_availability(""+IntStream.of(days)
                         .mapToObj(Integer::toString)
                         .collect(Collectors.joining(",")));
				 prepository.save(optionalPhysician.get());
				 
				 return ResponseEntity.badRequest().body("Now, Physician has max daily appointments reached");
			 }
			 List<Appointment> userappointments = repository.findByUser(uId);
			 if(userappointments.size() >= 1) {
				 return ResponseEntity.badRequest().body("User Cannot have more than one appointment at once");					
			 }
			 
			 newAppointment.setUser(optionalUser.get());
			 newAppointment.setPhysician(optionalPhysician.get());
			 
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
