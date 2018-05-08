package com.awe.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awe.backend.persistence.domain.backend.Category;
import com.awe.backend.persistence.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAllCategories() {
		List<Category> entityList = (List<Category>) repository.findAll();
		return entityList; 
	}
	
	public Category findCategoryById(long id) {
		Optional<Category> entity = repository.findById(id);
		return entity.isPresent() ? entity.get() : null;
	}
	
	public Category create(Category category) {
		Category entity = repository.save(category);
		return entity;
	}
	
	public long count() {
		return repository.count();
	}

	public Category findCategoryByName(String name) {
		Optional<Category> entity = repository.findByName(name); 
		return entity.isPresent() ? entity.get() : null;
	}
}
