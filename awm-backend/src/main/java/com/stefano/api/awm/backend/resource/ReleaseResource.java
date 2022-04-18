package com.stefano.api.awm.backend.resource;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.api.awm.backend.model.Release;
import com.stefano.api.awm.backend.repository.ReleaseRepository;

@RestController
@RequestMapping("/releases")
public class ReleaseResource {
	
	private final ReleaseRepository releaseRepository;

	public ReleaseResource(ReleaseRepository releaseRepository) {
		this.releaseRepository = releaseRepository;
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

}
