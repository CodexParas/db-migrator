package com.paras.db_migrator.config.job;

import com.paras.db_migrator.entity.oracle.OracleClientEntity;
import com.paras.db_migrator.generator.MockDataGenerator;
import com.paras.db_migrator.repository.oracle.OracleClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class OracleBatchJobConfig {

    private final MockDataGenerator mockDataGenerator;
    private final OracleClientRepository oracleClientRepository;
    private final JobRepository oracleJobRepository;
    private final PlatformTransactionManager oracleTransactionManager;
    private final JobListener jobListener;

    @Bean
    public Job oracleJob() {
        return new JobBuilder("oracleJob", oracleJobRepository).listener(jobListener).start(insertOracleStep()).build();
    }

    @Bean
    public Step insertOracleStep() {
        return new StepBuilder("oracle-step", oracleJobRepository).<OracleClientEntity, OracleClientEntity>chunk(
                                                                          100, oracleTransactionManager)
                                                                  .reader(oracleMockDataReader(null))
                                                                  .processor(oracleMockDataProcessor())
                                                                  .writer(oracleMockDataWriter())
                                                                  .transactionManager(oracleTransactionManager)
                                                                  .build();
    }

    @Bean
    @StepScope
    public ItemReader<OracleClientEntity> oracleMockDataReader(
            @Value("#{jobParameters['recordCount']}")
            String count) {
        List<OracleClientEntity> mockData = mockDataGenerator.oracleGenerateData(
                Integer.parseInt(Optional.of(count).orElse("100")));
        return new ListItemReader<>(mockData);
    }

    @Bean
    public ItemProcessor<OracleClientEntity, OracleClientEntity> oracleMockDataProcessor() {
        return item -> item;
    }

    @Bean
    public ItemWriter<OracleClientEntity> oracleMockDataWriter() {
        return oracleClientRepository::saveAll;
    }
}
