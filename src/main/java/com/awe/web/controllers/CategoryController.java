package com.awe.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awe.backend.persistence.domain.backend.Category;
import com.awe.backend.persistence.domain.dto.CategoryDTO;
import com.awe.backend.service.CategoryService;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
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
		Category category = categoryService.findCategoryById(id);
		
		return category;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Category create(@Valid @RequestBody CategoryDTO categoryRequest) {
		Category category = new Category();
		category.setName(categoryRequest.getName());
		return categoryService.create(category);
	}
}
