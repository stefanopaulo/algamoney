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
import com.stefano.api.awm.backend.model.Category;
import com.stefano.api.awm.backend.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	private final CategoryRepository categoryRepository;
	private final ApplicationEventPublisher publisher;

	public CategoryResource(CategoryRepository categoryRepository, ApplicationEventPublisher publisher) {
		this.categoryRepository = categoryRepository;
		this.publisher = publisher;
	}

	@GetMapping
	public List<Category> listAll() {
		return categoryRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category saveCategory = categoryRepository.save(category);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, saveCategory.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Category>> findById(@PathVariable Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return !category.isEmpty() ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
	}

}
