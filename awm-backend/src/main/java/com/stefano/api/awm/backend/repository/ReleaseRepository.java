package com.stefano.api.awm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefano.api.awm.backend.model.Release;

public interface ReleaseRepository extends JpaRepository<Release, Long> {

}
