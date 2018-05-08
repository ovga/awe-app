package com.awe.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awe.backend.persistence.domain.backend.Category;
import com.awe.backend.persistence.domain.backend.Website;
import com.awe.backend.service.CategoryService;
import com.awe.backend.service.WebsiteService;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping(value = "/websites")
public class WebsiteController {

	@Autowired
	private WebsiteService websiteService;

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<Website> getAllVisibleWebsites(@PageableDefault(page = 0, size = 9) Pageable pageable) {
		return websiteService.findByVisibleTrue(pageable);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Website getWebsiteById(@PathVariable("id") Long id) {
		return websiteService.findById(id);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addWebsite() {
		Website website = new Website();
		/*Category category1 = new Category();
		category1.setName("test many 1");
		
		Category category2 = new Category();
		category2.setName("test many 2");*/

		Category category1 = categoryService.findCategoryById(90);
		website.addCategory(category1);
		Category category2 = categoryService.findCategoryById(91);
		website.addCategory(category2);

		website.setPrettyname("testmany222");
		website.setVisible(Boolean.TRUE);
		websiteService.create(website);
		
	}
}
