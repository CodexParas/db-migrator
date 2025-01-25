package com.paras.db_migrator.controller;

import com.paras.db_migrator.dto.MigrateToMySqlDTO;
import com.paras.db_migrator.dto.MigrateToOracleDTO;
import com.paras.db_migrator.dto.MigrateToPostgresDTO;
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

    @PostMapping("/toPostgres")
    public ResponseEntity<ResponseDTO> migrateDataToPostgres(
            @Valid
            MigrateToPostgresDTO request) {
        return migrationService.migrateDataToPostgres(request);
    }

    @PostMapping("/toOracle")
    public ResponseEntity<ResponseDTO> migrateDataToOracle(
            @Valid
            MigrateToOracleDTO request) {
        return migrationService.migrateDataToOracle(request);
    }
}
