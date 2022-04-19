package com.stefano.api.awm.backend.repository.release;

import java.util.List;

import com.stefano.api.awm.backend.model.Release;
import com.stefano.api.awm.backend.repository.filter.ReleaseFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReleaseRepositoryQuery {
	
	public Page<Release> filter(ReleaseFilter releaseFilter, Pageable pageable);

}
