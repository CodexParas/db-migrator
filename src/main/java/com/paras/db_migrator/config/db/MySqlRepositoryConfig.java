package com.paras.db_migrator.config.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.paras.db_migrator.repository.mysql",
                       entityManagerFactoryRef = "mySqlEntityManagerFactory",
                       transactionManagerRef = "mySqlTransactionManager")
@Configuration
public class MySqlRepositoryConfig {
}
