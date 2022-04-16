package com.stefano.api.awm.backend.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.api.awm.backend.event.ResourceCreatedEvent;
import com.stefano.api.awm.backend.model.Person;
import com.stefano.api.awm.backend.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonResources {
	
	private final PersonRepository personRepository;
	private final ApplicationEventPublisher publisher;

	public PersonResources(PersonRepository personRepository, ApplicationEventPublisher publisher) {
		this.personRepository = personRepository;
		this.publisher = publisher;
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
	public ResponseEntity<Optional<Person>> findById(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		return !person.isEmpty() ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
	}

}
