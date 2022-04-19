package com.stefano.api.awm.backend.repository.release;

import java.util.List;

import com.stefano.api.awm.backend.model.Release;
import com.stefano.api.awm.backend.repository.filter.ReleaseFilter;

public interface ReleaseRepositoryQuery {
	
	public List<Release> filter(ReleaseFilter releaseFilter);

}
