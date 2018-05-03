package com.awe.batch;

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

public class StarterBatch {

	private static final Logger logger = LoggerFactory.getLogger(StarterBatch.class);
	
	private StarterBatch() {}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String springConfig = "spring/jobs.xml";

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		logger.info("PREPGARING BATCH IMPORT");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		Job job = (Job) context.getBean("importJob");
		executeJob(jobLauncher, job);

		logger.info("DONE!");

	}

	private static void executeJob(JobLauncher jobLauncher, Job job) {
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("datetime", new Date().getTime())
					.toJobParameters();
			logger.info("---> START " + job.getName() + "<---");
			
			JobExecution execution = jobLauncher.run(job, jobParameters);
			
			logger.info("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		logger.info("---> END " + job.getName() + "<---");
	}

}
