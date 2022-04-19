package com.stefano.api.awm.backend.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.stefano.api.awm.backend.event.ResourceCreatedEvent;
import com.stefano.api.awm.backend.exceptionhandler.AlgamoneyExceptionHandler.Error;
import com.stefano.api.awm.backend.model.Release;
import com.stefano.api.awm.backend.repository.ReleaseRepository;
import com.stefano.api.awm.backend.repository.filter.ReleaseFilter;
import com.stefano.api.awm.backend.service.ReleaseService;
import com.stefano.api.awm.backend.service.exception.NonexistentOrInactivePersonException;

@RestController
@RequestMapping("/releases")
public class ReleaseResource {
	
	private final ApplicationEventPublisher publisher;
	private final ReleaseRepository releaseRepository;
	private final ReleaseService releaseService;
	private final MessageSource messageSource;

	public ReleaseResource(ReleaseRepository releaseRepository, ApplicationEventPublisher publisher, ReleaseService releaseService, MessageSource messageSource) {
		this.releaseRepository = releaseRepository;
		this.publisher = publisher;
		this.releaseService = releaseService;
		this.messageSource = messageSource;
	}
	
	@GetMapping
	public List<Release> search(ReleaseFilter releaseFilter) {
		return releaseRepository.filter(releaseFilter);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Release> findById(@PathVariable Long id) {
		Release release = releaseRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return ResponseEntity.ok(release);
	}
	
	@PostMapping
	public ResponseEntity<Release> create(@Valid @RequestBody Release release, HttpServletResponse response) {
		Release saveRelease = releaseService.save(release);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, saveRelease.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveRelease);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		releaseService.delete(id);
	}
	
	@ExceptionHandler({ NonexistentOrInactivePersonException.class })
	public ResponseEntity<Object> handleNonexistentOrInactivePersonException(NonexistentOrInactivePersonException ex, WebRequest request) {
		String msgUser = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String msgDeveloper = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDeveloper));
		
		return ResponseEntity.badRequest().body(errors);
	}

}
