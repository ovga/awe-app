package com.awe.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.awe.backend.persistence.domain.backend.Website;
import com.awe.backend.service.WebsiteService;
import com.awe.utils.AWEHelper;

public class ItemProcessorWebsite implements ItemProcessor<Website, Website>{
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemProcessorWebsite.class);

	private WebsiteService service;
	
	@Override
	public Website process(Website item) throws Exception {
		LOG.info("Item " + item + "is being processed");
		//item.setPrettyname(AWEHelper.convertToPrettyName(item.getTitle()));
		return item;
	}
	
	public void setService(WebsiteService service) {
		this.service = service;
	}
	
}
