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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ReleaseRepositoryImpl implements ReleaseRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Release> filter(ReleaseFilter releaseFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Release> criteria = builder.createQuery(Release.class);

		Root<Release> root = criteria.from(Release.class);

		Predicate[] predicates = createRestrictions(releaseFilter, builder, root);

		criteria.where(predicates);

		TypedQuery<Release> query = manager.createQuery(criteria);

		addPaginationRestrictionsInTheQuery(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(releaseFilter));
	}

	private Predicate[] createRestrictions(ReleaseFilter releaseFilter, CriteriaBuilder builder, Root<Release> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (releaseFilter.getDescription() != null) {
			predicates.add(builder.like(
					builder.lower(root.get(Release_.description)),
					"%" + releaseFilter.getDescription().toLowerCase() + "%"));
		}

		if (releaseFilter.getDueDateOf() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Release_.dueDate), releaseFilter.getDueDateOf()));
		}

		if (releaseFilter.getPaymentDateUntil() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Release_.paymentDate), releaseFilter.getPaymentDateUntil()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPaginationRestrictionsInTheQuery(TypedQuery<Release> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalPecordsPerPage = pageable.getPageSize();
		int firstRecordOfThePage = currentPage * totalPecordsPerPage;

		query.setFirstResult(firstRecordOfThePage);
		query.setMaxResults(totalPecordsPerPage);
	}

	private Long total(ReleaseFilter releaseFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Release> root = criteria.from(Release.class);

		Predicate[] predicates = createRestrictions(releaseFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

}
