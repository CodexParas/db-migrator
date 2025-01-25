package com.paras.db_migrator.constants;

import lombok.Getter;

@Getter
public enum DbType {
    MYSQL("mysql"), POSTGRES("postgres"), ORACLE("oracle");
    private final String value;

    DbType(String value) {
        this.value = value;
    }

}
