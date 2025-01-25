package com.paras.db_migrator.controller;

import com.paras.db_migrator.dto.MigrateToMySqlDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import com.paras.db_migrator.service.MigrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/migrate")
public class MigrationController {

    private final MigrationService migrationService;

    @PostMapping("/toMySql")
    public ResponseEntity<ResponseDTO> migrateDataToMySql(
            @Valid
            MigrateToMySqlDTO request) {
        return migrationService.migrateDataToMySql(request);
    }

    @PostMapping("/postgresToMysql")
    public ResponseEntity<ResponseDTO> migrateDataFromPostgresToMysql() {
        return migrationService.migrateDataFromPostgresToMysql();
    }

    @PostMapping("/mysqlToOracle")
    public ResponseEntity<ResponseDTO> migrateDataFromMysqlToOracle() {
        return migrationService.migrateDataFromMysqlToOracle();
    }

    @PostMapping("/oracleToMysql")
    public ResponseEntity<ResponseDTO> migrateDataFromOracleToMysql() {
        return migrationService.migrateDataFromOracleToMysql();
    }

    @PostMapping("/postgresToOracle")
    public ResponseEntity<ResponseDTO> migrateDataFromPostgresToOracle() {
        return migrationService.migrateDataFromPostgresToOracle();
    }

    @PostMapping("/oracleToPostgres")
    public ResponseEntity<ResponseDTO> migrateDataFromOracleToPostgres() {
        return migrationService.migrateDataFromOracleToPostgres();
    }
}
