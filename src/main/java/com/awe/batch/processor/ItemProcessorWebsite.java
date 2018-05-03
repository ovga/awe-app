package com.awe.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.awe.backend.persistence.domain.backend.Website;
import com.awe.utils.AWEHelper;

public class ItemProcessorWebsite implements ItemProcessor<Website, Website>{

	@Override
	public Website process(Website item) throws Exception {
		item.setPrettyname(AWEHelper.convertToPrettyName(item.getTitle()));
		return item;
	}
	
}
