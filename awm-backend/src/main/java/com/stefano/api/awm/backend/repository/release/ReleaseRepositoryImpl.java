package com.stefano.api.awm.backend.repository.release;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.stefano.api.awm.backend.model.Release;
import com.stefano.api.awm.backend.model.Release_;
import com.stefano.api.awm.backend.repository.filter.ReleaseFilter;

public class ReleaseRepositoryImpl implements ReleaseRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Release> filter(ReleaseFilter releaseFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Release> criteria = builder.createQuery(Release.class);
		
		Root<Release> root = criteria.from(Release.class);
		
		Predicate[] predicates = createRestrictions(releaseFilter, builder, root);
		
		criteria.where(predicates);
		
		TypedQuery<Release> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] createRestrictions(ReleaseFilter releaseFilter, CriteriaBuilder builder, Root<Release> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(releaseFilter.getDescription() != null) {
			predicates.add(builder.like(
					builder.lower(root.get(Release_.description)), "%" + releaseFilter.getDescription().toLowerCase() +"%"));
		}
		
		if(releaseFilter.getDueDateOf() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Release_.dueDate), releaseFilter.getDueDateOf()));
		}
		
		if(releaseFilter.getPaymentDateUntil() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Release_.paymentDate), releaseFilter.getPaymentDateUntil()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
