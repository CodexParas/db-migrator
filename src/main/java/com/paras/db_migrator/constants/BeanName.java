package com.paras.db_migrator.constants;

import lombok.Getter;

@Getter
public enum BeanName {

    POSTGRES_JOB_LAUNCHER("postgresJobLauncher"),
    MYSQL_JOB_LAUNCHER("mySqlJobLauncher"),
    ORACLE_JOB_LAUNCHER("oracleJobLauncher"),
    POSTGRES_DATA_INSERT_JOB("postgresJob"),
    MYSQL_DATA_INSERT_JOB("mySqlJob"),
    ORACLE_DATA_INSERT_JOB("oracleJob"),
    MIGRATE_TO_POSTGRES_JOB("migrateToPostgresJob"),
    MIGRATE_TO_MYSQL_JOB("migrateToMysqlJob"),
    MIGRATE_TO_ORACLE_JOB("migrateToOracleJob"),
    MYSQL_JOB_REPOSITORY("mySqlJobRepository"),
    POSTGRES_JOB_REPOSITORY("postgreSqlJobRepository"),
    ORACLE_JOB_REPOSITORY("oracleJobRepository"),
    MYSQL_DATA_SOURCE("mySqlDataSource"),
    POSTGRES_DATA_SOURCE("postgreSqlDataSource"),
    ORACLE_DATA_SOURCE("oracleDataSource"),
    MYSQL_TRANSACTION_MANAGER("mySqlTransactionManager"),
    POSTGRES_TRANSACTION_MANAGER("postgreSqlTransactionManager"),
    ORACLE_TRANSACTION_MANAGER("oracleTransactionManager");


    private final String value;

    BeanName(String value) {
        this.value = value;
    }
}
