package com.awe.batch.writer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JpaItemWriter;

import com.awe.backend.persistence.domain.backend.Website;
import com.awe.backend.service.WebsiteService;

public class ItemWriterWebsite extends JpaItemWriter<Website> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemWriterWebsite.class);

	private WebsiteService service;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void write(List<? extends Website> items) {
		if (items.isEmpty()) {
			LOG.info("There are no WEBSITES in the CSV file!!!");
			return;
		}
		
		for (Website item : items) {
			/*if (service.countByUrl("gsp") != 0) {
				throw new ItemAlreadyExistsInDBException("item already in DB"); 
			}*/
			em.persist(item);
			service.create(item);
			em.flush();
			em.clear();
			LOG.info(item.getPrettyname());
		}

	}

	public void setService(WebsiteService service) {
		this.service = service;
	}

/*	public void setEm(EntityManager em) {
		this.em = em;
	}*/
	
}
