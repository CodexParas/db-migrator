package com.paras.db_migrator.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfigs {

    @Bean(name = "mySqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    @Primary
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgreSqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource postgreSqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }
}
