package com.awe.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awe.backend.persistence.domain.backend.Website;
import com.awe.backend.service.WebsiteService;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping(value = "/websites")
public class WebsiteController {

	@Autowired
	private WebsiteService websiteService;

	@RequestMapping(method = RequestMethod.GET)
	public Page<Website> getAllWebsites(@PageableDefault(page = 0, size = 9) Pageable pageable) {
		return websiteService.getAllWebsites(pageable);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Website> getWebsiteById(@PathVariable("id") Long id) {
		return websiteService.getWebsiteById(id);
	}
}
