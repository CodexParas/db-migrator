package com.paras.db_migrator.config.job;

import com.paras.db_migrator.supplier.BeanSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import static com.paras.db_migrator.constants.BeanName.*;

@Configuration
@RequiredArgsConstructor
public class JobLauncherConfigs {

    private final BeanSupplier beanSupplier;

    @Bean
    @Primary
    public JobLauncher mySqlJobLauncher() {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(beanSupplier.getJobRepositoryBean(MYSQL_JOB_REPOSITORY));
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }

    @Bean
    public JobLauncher postgresJobLauncher() {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(beanSupplier.getJobRepositoryBean(POSTGRES_JOB_REPOSITORY));
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }

    @Bean
    public JobLauncher oracleJobLauncher() {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(beanSupplier.getJobRepositoryBean(ORACLE_JOB_REPOSITORY));
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }

}
