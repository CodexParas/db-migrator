package com.paras.db_migrator.config.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.paras.db_migrator.repository.postgres",
                       entityManagerFactoryRef = "postgreSqlEntityManagerFactory",
                       transactionManagerRef = "postgreSqlTransactionManager")
@Configuration
public class PostgreSqlRepositoryConfig {
}
