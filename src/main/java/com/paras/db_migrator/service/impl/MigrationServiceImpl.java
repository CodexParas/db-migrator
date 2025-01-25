package com.paras.db_migrator.service.impl;

import com.paras.db_migrator.dto.MigrateToMySqlDTO;
import com.paras.db_migrator.dto.MigrateToOracleDTO;
import com.paras.db_migrator.dto.MigrateToPostgresDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import com.paras.db_migrator.service.MigrationService;
import com.paras.db_migrator.supplier.BeanSupplier;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.paras.db_migrator.constants.BeanName.*;
import static com.paras.db_migrator.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class MigrationServiceImpl implements MigrationService {

    private final BeanSupplier beanSupplier;

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataToMySql(MigrateToMySqlDTO request) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(source, request.source().getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(MYSQL_JOB_LAUNCHER).run(beanSupplier.getJobBean(MIGRATE_TO_MYSQL_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_MIGRATED, null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataToPostgres(MigrateToPostgresDTO request) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(source, request.source().getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(POSTGRES_JOB_LAUNCHER)
                    .run(beanSupplier.getJobBean(MIGRATE_TO_POSTGRES_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_MIGRATED, null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataToOracle(MigrateToOracleDTO request) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(source, request.source().getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(ORACLE_JOB_LAUNCHER)
                    .run(beanSupplier.getJobBean(MIGRATE_TO_ORACLE_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_MIGRATED, null));
    }
}
