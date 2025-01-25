package com.paras.db_migrator.service.impl;

import com.paras.db_migrator.dto.DataInsertRequestDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import com.paras.db_migrator.service.DataService;
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
public class DataServiceImpl implements DataService {

    private final BeanSupplier beanSupplier;

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> insertDataToMySql(DataInsertRequestDTO dataInsertRequestDTO) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(
                                                                 recordCount,
                                                                 dataInsertRequestDTO.count().toString())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(MYSQL_JOB_LAUNCHER).run(beanSupplier.getJobBean(MYSQL_DATA_INSERT_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_INSERTED, null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> insertDataToPostgres(DataInsertRequestDTO dataInsertRequestDTO) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(
                                                                 recordCount,
                                                                 dataInsertRequestDTO.count().toString())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(POSTGRES_JOB_LAUNCHER)
                    .run(beanSupplier.getJobBean(POSTGRES_DATA_INSERT_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_INSERTED, null));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> insertDataToOracle(DataInsertRequestDTO dataInsertRequestDTO) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(
                                                                 recordCount,
                                                                 dataInsertRequestDTO.count().toString())
                                                         .toJobParameters();
        beanSupplier.getJobLauncherBean(ORACLE_JOB_LAUNCHER)
                    .run(beanSupplier.getJobBean(ORACLE_DATA_INSERT_JOB), params);
        return ResponseEntity.ok(ResponseDTO.success(DATA_INSERTED, null));
    }

    @Override
    public ResponseEntity<ResponseDTO> insertDataToAll(DataInsertRequestDTO dataInsertRequestDTO) {
        insertDataToMySql(dataInsertRequestDTO);
        insertDataToPostgres(dataInsertRequestDTO);
        insertDataToOracle(dataInsertRequestDTO);
        return ResponseEntity.ok(ResponseDTO.success(DATA_INSERTED, null));
    }
}
