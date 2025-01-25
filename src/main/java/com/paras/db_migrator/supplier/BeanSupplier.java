package com.paras.db_migrator.supplier;

import com.paras.db_migrator.constants.BeanName;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class BeanSupplier {

    private final ApplicationContext applicationContext;

    public Job getJobBean(BeanName name) {
        return applicationContext.getBean(name.getValue(), Job.class);
    }

    public JobLauncher getJobLauncherBean(BeanName name) {
        return applicationContext.getBean(name.getValue(), JobLauncher.class);
    }

    public PlatformTransactionManager getTransactionManagerBean(BeanName name) {
        return applicationContext.getBean(name.getValue(), PlatformTransactionManager.class);
    }

    public DataSource getDataSourceBean(BeanName name) {
        return applicationContext.getBean(name.getValue(), DataSource.class);
    }

    public JobRepository getJobRepositoryBean(BeanName name) {
        return applicationContext.getBean(name.getValue(), JobRepository.class);
    }
}
