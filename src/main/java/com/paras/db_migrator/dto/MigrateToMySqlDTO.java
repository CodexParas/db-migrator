package com.paras.db_migrator.dto;

import com.paras.db_migrator.constants.DbType;

public record MigrateToMySqlDTO(
        DbType source
) {
    public MigrateToMySqlDTO {
        if(source != DbType.POSTGRES && source != DbType.ORACLE) {
            throw new IllegalArgumentException(
                    "Only POSTGRES and ORACLE are allowed as source types for migration to MySQL.");
        }
    }
}
