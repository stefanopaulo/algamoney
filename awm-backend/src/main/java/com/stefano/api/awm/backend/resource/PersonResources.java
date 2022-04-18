package com.stefano.api.awm.backend.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.api.awm.backend.event.ResourceCreatedEvent;
import com.stefano.api.awm.backend.model.Person;
import com.stefano.api.awm.backend.repository.PersonRepository;
import com.stefano.api.awm.backend.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonResources {
	
	private final PersonService personService;
	private final PersonRepository personRepository;
	private final ApplicationEventPublisher publisher;

	public PersonResources(PersonRepository personRepository, ApplicationEventPublisher publisher, PersonService personService) {
		this.personRepository = personRepository;
		this.publisher = publisher;
		this.personService = personService;
	}
	
	@GetMapping
	public List<Person> listAll() {
		return personRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person savePerson = personRepository.save(person);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savePerson.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savePerson);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return ResponseEntity.ok(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		personRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
		Person savePerson = personService.update(id, person);
		
		return ResponseEntity.ok(savePerson);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateActiveProperty(@PathVariable Long id, @RequestBody Boolean active) {
		personService.updateActiveProperty(id, active);
	}

}
