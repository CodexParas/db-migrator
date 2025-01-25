package com.paras.db_migrator.config.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BatchRepositoryConfig {

    private final PlatformTransactionManager postgreSqlTransactionManager;
    private final PlatformTransactionManager oracleTransactionManager;
    private final PlatformTransactionManager mySqlTransactionManager;
    private final DataSource mySqlDataSource;
    private final DataSource postgreSqlDataSource;
    private final DataSource oracleDataSource;

    @Bean
    @Primary
    public JobRepository mySqlJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(mySqlDataSource);
        factory.setTransactionManager(mySqlTransactionManager);
        factory.setDatabaseType("MYSQL");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JobRepository postgreSqlJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(postgreSqlDataSource);
        factory.setTransactionManager(postgreSqlTransactionManager);
        factory.setDatabaseType("POSTGRES");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JobRepository oracleJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(oracleDataSource);
        factory.setTransactionManager(oracleTransactionManager);
        factory.setDatabaseType("ORACLE");
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
