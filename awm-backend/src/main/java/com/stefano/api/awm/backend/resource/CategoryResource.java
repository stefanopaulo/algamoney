package com.stefano.api.awm.backend.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefano.api.awm.backend.model.Category;
import com.stefano.api.awm.backend.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	private final CategoryRepository categoryRepository;

	public CategoryResource(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@GetMapping
	public List<Category> listAll() {
		return categoryRepository.findAll();
	}

}
