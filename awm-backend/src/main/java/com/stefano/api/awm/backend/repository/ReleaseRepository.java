package com.stefano.api.awm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefano.api.awm.backend.model.Release;
import com.stefano.api.awm.backend.repository.release.ReleaseRepositoryQuery;

public interface ReleaseRepository extends JpaRepository<Release, Long>, ReleaseRepositoryQuery {

}
