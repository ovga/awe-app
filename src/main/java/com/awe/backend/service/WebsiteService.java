package com.awe.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.awe.backend.persistence.domain.backend.Website;
import com.awe.backend.persistence.repositories.WebsiteRepository;

@Service
public class WebsiteService {

	@Autowired
	private WebsiteRepository repository;

	public Page<Website> findAll(Pageable pageable) {
		Page<Website> websiteList = repository.findAll(pageable);
		return websiteList;
	}
	
	public Website findById(Long id) {
		Optional<Website> website = repository.findById(id);
		return website.isPresent() ? website.get() : null;
	}

	public long countByUrl(String url) {
		return repository.countByUrl(url);
	}

	public Website create(Website item) {
		Website entity = repository.save(item);
		return entity;
	}
	
	public Page<Website> findByVisibleTrue(Pageable pageable) {
		return repository.findByVisibleTrue(pageable);
	}
	
}
