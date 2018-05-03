package com.awe.backend.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.awe.backend.persistence.domain.backend.Website;

@Repository
public interface WebsiteRepository extends PagingAndSortingRepository<Website, Long> {

	long countByUrl(String name);

	Page<Website> findByVisibleTrue(Pageable pageable);
}
