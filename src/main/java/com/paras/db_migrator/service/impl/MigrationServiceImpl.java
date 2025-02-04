package com.paras.db_migrator.service.impl;

import com.paras.db_migrator.dto.MigrateToMySqlDTO;
import com.paras.db_migrator.dto.MigrateToOracleDTO;
import com.paras.db_migrator.dto.MigrateToPostgresDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import com.paras.db_migrator.service.MigrationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.paras.db_migrator.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class MigrationServiceImpl implements MigrationService {

    private final JobLauncher mySqlJobLauncher;
    private final JobLauncher postgresJobLauncher;
    private final JobLauncher oracleJobLauncher;
    private final Job migrateToMysqlJob;
    private final Job migrateToPostgresJob;
    private final Job migrateToOracleJob;

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataToMySql(MigrateToMySqlDTO request) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(source, request.source().getValue())
                                                         .toJobParameters();
        mySqlJobLauncher.run(migrateToMysqlJob, params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_MIGRATED, null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataToPostgres(MigrateToPostgresDTO request) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(source, request.source().getValue())
                                                         .toJobParameters();
        postgresJobLauncher
                    .run(migrateToPostgresJob, params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_MIGRATED, null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataToOracle(MigrateToOracleDTO request) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(source, request.source().getValue())
                                                         .toJobParameters();
        oracleJobLauncher
                    .run(migrateToOracleJob, params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_MIGRATED, null));
    }
}
