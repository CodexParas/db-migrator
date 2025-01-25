package com.paras.db_migrator.service.impl;

import com.paras.db_migrator.constants.DbType;
import com.paras.db_migrator.dto.MigrateToMySqlDTO;
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
import static com.paras.db_migrator.constants.Constants.source;
import static com.paras.db_migrator.constants.Constants.timestamp;

@Service
@RequiredArgsConstructor
public class MigrationServiceImpl implements MigrationService {

    private final BeanSupplier beanSupplier;

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataFromMysqlToPostgres() {
        JobParameters params = new JobParametersBuilder().addLong(timestamp.name(), System.currentTimeMillis())
                                                         .addString(source.name(), DbType.MYSQL.getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(POSTGRES_JOB_LAUNCHER)
                    .run(beanSupplier.getJobBean(MIGRATE_TO_POSTGRES_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success("Data Migrated Successfully", null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataFromPostgresToMysql() {
        JobParameters params = new JobParametersBuilder().addLong(timestamp.name(), System.currentTimeMillis())
                                                         .addString(source.name(), DbType.POSTGRES.getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(MYSQL_JOB_LAUNCHER).run(beanSupplier.getJobBean(MIGRATE_TO_MYSQL_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success("Data Migrated Successfully", null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataFromMysqlToOracle() {
        JobParameters params = new JobParametersBuilder().addLong(timestamp.name(), System.currentTimeMillis())
                                                         .addString(source.name(), DbType.MYSQL.getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(ORACLE_JOB_LAUNCHER)
                    .run(beanSupplier.getJobBean(MIGRATE_TO_ORACLE_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success("Data Migrated Successfully", null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataFromOracleToMysql() {
        JobParameters params = new JobParametersBuilder().addLong(timestamp.name(), System.currentTimeMillis())
                                                         .addString(source.name(), DbType.ORACLE.getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(MYSQL_JOB_LAUNCHER).run(beanSupplier.getJobBean(MIGRATE_TO_MYSQL_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success("Data Migrated Successfully", null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataFromPostgresToOracle() {
        JobParameters params = new JobParametersBuilder().addLong(timestamp.name(), System.currentTimeMillis())
                                                         .addString(source.name(), DbType.POSTGRES.getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(ORACLE_JOB_LAUNCHER)
                    .run(beanSupplier.getJobBean(MIGRATE_TO_ORACLE_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success("Data Migrated Successfully", null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataFromOracleToPostgres() {
        JobParameters params = new JobParametersBuilder().addLong(timestamp.name(), System.currentTimeMillis())
                                                         .addString(source.name(), DbType.ORACLE.getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(POSTGRES_JOB_LAUNCHER)
                    .run(beanSupplier.getJobBean(MIGRATE_TO_POSTGRES_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success("Data Migrated Successfully", null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> migrateDataToMySql(MigrateToMySqlDTO request) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp.name(), System.currentTimeMillis())
                                                         .addString(source.name(), request.source().getValue())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(MYSQL_JOB_LAUNCHER).run(beanSupplier.getJobBean(MIGRATE_TO_MYSQL_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success("Data Migrated Successfully", null));
    }
}
