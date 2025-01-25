package com.paras.db_migrator.dto;

import com.paras.db_migrator.constants.DbType;

public record MigrateToPostgresDTO(
        DbType source
) {
    public MigrateToPostgresDTO {
        if(source != DbType.MYSQL && source != DbType.ORACLE) {
            throw new IllegalArgumentException(
                    "Only POSTGRES and ORACLE are allowed as source types for migration to MySQL.");
        }
    }
}
