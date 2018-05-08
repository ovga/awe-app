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

import com.awe.backend.persistence.domain.backend.Tag;
import com.awe.backend.persistence.domain.dto.TagDTO;
import com.awe.backend.service.TagService;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagService tagService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Tag> getAllTags() {
		List<Tag> tagList = tagService.findAllTags();
		
		return !tagList.isEmpty() ? tagList : new ArrayList<>();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Tag getTagById(@PathVariable("id") Long id) {
		Tag tag = tagService.findTagById(id);
		
		return tag;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Tag create(@Valid @RequestBody TagDTO tagRequest) {
		Tag tag = new Tag();
		tag.setName(tagRequest.getName());
		return tagService.create(tag);
	}
}
