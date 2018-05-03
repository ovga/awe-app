package com.awe.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.awe.backend.persistence.domain.backend.Website;
import com.awe.backend.persistence.repositories.WebsiteRepository;
import com.awe.exceptions.ItemAlreadyExistsInDBException;

public class ItemWriterWebsite extends JpaItemWriter<Website> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemWriterWebsite.class);

	@Autowired
	private WebsiteRepository wr;
	
	@Override
	public void write(List<? extends Website> items) {
		if (items.isEmpty()) {
			LOG.info("There are no WEBSITES in the CSV file!!!");
			return;
		}
		
		for (Website item : items) {
			if (wr.countByUrl("gsp") != 0) {
				throw new ItemAlreadyExistsInDBException("item already in DB"); 
			}
			LOG.info(item.getPrettyname());
		}

	}

}
