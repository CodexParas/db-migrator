package com.paras.db_migrator.service;

import com.paras.db_migrator.dto.MigrateToMySqlDTO;
import com.paras.db_migrator.dto.MigrateToOracleDTO;
import com.paras.db_migrator.dto.MigrateToPostgresDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface MigrationService {

    ResponseEntity<ResponseDTO> migrateDataToMySql(MigrateToMySqlDTO request);

    ResponseEntity<ResponseDTO> migrateDataToPostgres(MigrateToPostgresDTO request);

    ResponseEntity<ResponseDTO> migrateDataToOracle(MigrateToOracleDTO request);
}
