package com.awe.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awe.backend.persistence.domain.backend.Category;
import com.awe.backend.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> getAllCategories() {
		List<Category> categoryList = categoryService.findAllCategories();
		
		return !categoryList.isEmpty() ? categoryList : new ArrayList<>();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Category getCategoryById(@PathVariable("id") Long id) {
		Optional<Category> category = categoryService.findCategoryById(id);
		
		return category.isPresent() ? category.get() : null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Category create(@Valid @RequestBody Category categoryRequest) {
		Category category = categoryService.create(categoryRequest);
		return category;
	}
}
