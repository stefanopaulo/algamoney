package com.stefano.api.awm.backend.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.api.awm.backend.event.ResourceCreatedEvent;
import com.stefano.api.awm.backend.model.Release;
import com.stefano.api.awm.backend.repository.ReleaseRepository;

@RestController
@RequestMapping("/releases")
public class ReleaseResource {
	
	private final ApplicationEventPublisher publisher;
	private final ReleaseRepository releaseRepository;

	public ReleaseResource(ReleaseRepository releaseRepository, ApplicationEventPublisher publisher) {
		this.releaseRepository = releaseRepository;
		this.publisher = publisher;
	}
	
	@GetMapping
	public List<Release> listAll() {
		return releaseRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Release> findById(@PathVariable Long id) {
		Release release = releaseRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return ResponseEntity.ok(release);
	}
	
	@PostMapping
	public ResponseEntity<Release> create(@Valid @RequestBody Release release, HttpServletResponse response) {
		Release saveRelease = releaseRepository.save(release);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, saveRelease.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveRelease);
	}

}
