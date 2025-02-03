package com.paras.db_migrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DbMigratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbMigratorApplication.class, args);
	}

}
