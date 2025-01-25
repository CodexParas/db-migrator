package com.paras.db_migrator.config.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job Started with ID:- {}", jobExecution.getJobId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus().isUnsuccessful()) {
            log.error("Job Failed with ID:- {}", jobExecution.getJobId());
        }
        else {
            log.info("Job Completed with ID:- {}", jobExecution.getJobId());
        }
    }
}
