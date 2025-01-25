package com.paras.db_migrator.config.db;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfigs {

    @Primary
    @Bean(name = "mySqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mySqlDataSource")
            DataSource dataSource) {
        return builder.dataSource(dataSource)
                      .packages("com.paras.db_migrator.entity.mysql")
                      .persistenceUnit("mysql")
                      .properties(jpaProperties())
                      .build();
    }

    @Bean(name = "postgreSqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgreSqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgreSqlDataSource")
            DataSource dataSource) {
        return builder.dataSource(dataSource)
                      .packages("com.paras.db_migrator.entity.postgres")
                      .properties(jpaProperties())
                      .persistenceUnit("postgres")
                      .build();
    }

    @Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("oracleDataSource")
            DataSource dataSource) {
        Map<String, Object> properties = jpaProperties();
        properties.put("hibernate.default_schema", "DBMIGRATOR");
        return builder.dataSource(dataSource)
                      .packages("com.paras.db_migrator.entity.oracle")
                      .properties(jpaProperties())
                      .persistenceUnit("oracle")
                      .build();
    }

    @Bean(name = "mySqlTransactionManager")
    @Primary
    public PlatformTransactionManager mySqlTransactionManager(
            @Qualifier("mySqlEntityManagerFactory")
            EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    @Bean(name = "postgreSqlTransactionManager")
    public PlatformTransactionManager postgreSqlTransactionManager(
            @Qualifier("postgreSqlEntityManagerFactory")
            EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager oracleTransactionManager(
            @Qualifier("oracleEntityManagerFactory")
            EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.ddl-auto", "update");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        return properties;
    }
}
