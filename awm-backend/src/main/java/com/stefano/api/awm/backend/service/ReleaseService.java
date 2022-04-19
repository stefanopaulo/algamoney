package com.stefano.api.awm.backend.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.stefano.api.awm.backend.model.Person;
import com.stefano.api.awm.backend.model.Release;
import com.stefano.api.awm.backend.repository.PersonRepository;
import com.stefano.api.awm.backend.repository.ReleaseRepository;
import com.stefano.api.awm.backend.service.exception.NonexistentOrInactivePersonException;

@Service
public class ReleaseService {
	
	private final PersonRepository personRepository;
	private final ReleaseRepository releaseRepository;

	public ReleaseService(PersonRepository personRepository, ReleaseRepository releaseRepository) {
		this.personRepository = personRepository;
		this.releaseRepository = releaseRepository;
	}

	public Release save(Release release) {
		Person person = personRepository.findById(release.getPerson().getId())
				.orElseThrow(null);
		
		if(person == null || person.isInativo()) {
			throw new NonexistentOrInactivePersonException();
		}
		
		return releaseRepository.save(release);
	}

	public void delete(Long id) {
		Release release = releaseRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		releaseRepository.delete(release);
	}

}
