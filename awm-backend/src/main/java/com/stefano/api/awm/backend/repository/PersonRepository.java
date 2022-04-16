package com.stefano.api.awm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefano.api.awm.backend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
