package com.paras.db_migrator.service;

import com.paras.db_migrator.dto.MigrateToMySqlDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface MigrationService {
    ResponseEntity<ResponseDTO> migrateDataFromMysqlToPostgres();

    ResponseEntity<ResponseDTO> migrateDataFromPostgresToMysql();

    ResponseEntity<ResponseDTO> migrateDataFromMysqlToOracle();

    ResponseEntity<ResponseDTO> migrateDataFromOracleToMysql();

    ResponseEntity<ResponseDTO> migrateDataFromPostgresToOracle();

    ResponseEntity<ResponseDTO> migrateDataFromOracleToPostgres();

    ResponseEntity<ResponseDTO> migrateDataToMySql(MigrateToMySqlDTO request);
}
