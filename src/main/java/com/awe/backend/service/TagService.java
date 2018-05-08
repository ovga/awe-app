package com.awe.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awe.backend.persistence.domain.backend.Tag;
import com.awe.backend.persistence.repositories.TagRepository;

@Service
public class TagService {
	
	@Autowired
	private TagRepository repository;
	
	public List<Tag> findAllTags() {
		List<Tag> entityList = (List<Tag>) repository.findAll();
		return entityList; 
	}
	
	public Tag findTagById(long id) {
		Optional<Tag> entity = repository.findById(id);
		return entity.isPresent() ? entity.get() : null;
	}
	
	public Tag create(Tag tag) {
		Tag entity = repository.save(tag);
		return entity;
	}
	
	public long count() {
		return repository.count();
	}

	public Tag findTagByName(String name) {
		Optional<Tag> entity = repository.findByName(name); 
		return entity.isPresent() ? entity.get() : null;
	}
}
