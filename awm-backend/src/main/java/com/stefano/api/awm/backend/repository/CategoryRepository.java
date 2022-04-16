package com.stefano.api.awm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefano.api.awm.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
