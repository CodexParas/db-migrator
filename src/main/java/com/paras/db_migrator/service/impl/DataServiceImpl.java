package com.paras.db_migrator.service.impl;

import com.paras.db_migrator.dto.DataInsertRequestDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import com.paras.db_migrator.service.DataService;
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
public class DataServiceImpl implements DataService {

    private final JobLauncher mySqlJobLauncher;
    private final JobLauncher postgresJobLauncher;
    private final JobLauncher oracleJobLauncher;
    private final Job mySqlJob;
    private final Job postgresJob;
    private final Job oracleJob;

    @Override
    @SneakyThrows
    public ResponseEntity<ResponseDTO> insertDataToMySql(DataInsertRequestDTO dataInsertRequestDTO) {
        JobParameters params = new JobParametersBuilder().addLong(timestamp, System.currentTimeMillis())
                                                         .addString(
                                                                 recordCount,
                                                                 dataInsertRequestDTO.count().toString())
                                                         .toJobParameters();
        mySqlJobLauncher.run(mySqlJob, params);
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
        postgresJobLauncher
                    .run(postgresJob, params);
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
        oracleJobLauncher
                    .run(oracleJob, params);
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
