package com.stefano.api.awm.backend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.stefano.api.awm.backend.model.Person;
import com.stefano.api.awm.backend.repository.PersonRepository;

@Service
public class PersonService {
	
	private final PersonRepository personRepository;
	
	
	
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person update(Long id, Person person) {
		Person savePerson = findPersonById(id);
		
		BeanUtils.copyProperties(person, savePerson, "id");
		
		return personRepository.save(savePerson);
	}

	public void updateActiveProperty(Long id, Boolean active) {
		Person savePerson = findPersonById(id);
		
		savePerson.setActive(active);
		
		personRepository.save(savePerson);
	}
	
	private Person findPersonById(Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

}
