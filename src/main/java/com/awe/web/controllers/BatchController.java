package com.awe.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awe.backend.service.BatchImportService;

@RestController
@RequestMapping("/batch")
public class BatchController {

	@Autowired
	public BatchImportService batchImportService;
	
	@RequestMapping(method = RequestMethod.GET)
	public void startBatchImport() {
		batchImportService.startJob();
	}
}
