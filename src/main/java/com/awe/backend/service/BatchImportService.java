package com.awe.backend.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BatchImportService {

	private static final Logger LOG = LoggerFactory.getLogger(BatchImportService.class);

	public void startJob() {
		String springConfig = "spring/jobs.xml";

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		LOG.info("PREPGARING BATCH IMPORT");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		Job job = (Job) context.getBean("importJob");
		executeJob(jobLauncher, job);

		LOG.info("DONE!");
		
	}
	
	private void executeJob(JobLauncher jobLauncher, Job job) {
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("datetime", new Date().getTime())
					.toJobParameters();
			LOG.info("---> START " + job.getName() + "<---");
			
			JobExecution execution = jobLauncher.run(job, jobParameters);
			
			LOG.info("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		
		LOG.info("---> END " + job.getName() + "<---");
	}
}
