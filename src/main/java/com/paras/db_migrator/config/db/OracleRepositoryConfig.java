package com.paras.db_migrator.config.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.paras.db_migrator.oracle.repository",
                       entityManagerFactoryRef = "oracleEntityManagerFactory",
                       transactionManagerRef = "oracleTransactionManager")
@Configuration
public class OracleRepositoryConfig {
}
