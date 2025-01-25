package com.paras.db_migrator.config.job;

import com.paras.db_migrator.supplier.BeanSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.paras.db_migrator.constants.BeanName.*;

@Configuration
@RequiredArgsConstructor
public class BatchRepositoryConfig {

    private final BeanSupplier beanSupplier;

    @Bean
    @Primary
    public JobRepository mySqlJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(beanSupplier.getDataSourceBean(MYSQL_DATA_SOURCE));
        factory.setTransactionManager(beanSupplier.getTransactionManagerBean(MYSQL_TRANSACTION_MANAGER));
        factory.setDatabaseType("MYSQL");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JobRepository postgreSqlJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(beanSupplier.getDataSourceBean(POSTGRES_DATA_SOURCE));
        factory.setTransactionManager(beanSupplier.getTransactionManagerBean(POSTGRES_TRANSACTION_MANAGER));
        factory.setDatabaseType("POSTGRES");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JobRepository oracleJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(beanSupplier.getDataSourceBean(ORACLE_DATA_SOURCE));
        factory.setTransactionManager(beanSupplier.getTransactionManagerBean(ORACLE_TRANSACTION_MANAGER));
        factory.setDatabaseType("ORACLE");
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
