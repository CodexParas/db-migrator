package com.paras.db_migrator.dto;

import com.paras.db_migrator.constants.DbType;

public record MigrateToOracleDTO(
        DbType source
) {
    public MigrateToOracleDTO {
        if(source != DbType.MYSQL && source != DbType.POSTGRES) {
            throw new IllegalArgumentException(
                    "Only POSTGRES and ORACLE are allowed as source types for migration to MySQL.");
        }
    }
}
