package com.paras.db_migrator.config.job.migrate;

import com.paras.db_migrator.config.job.JobListener;
import com.paras.db_migrator.constants.DbType;
import com.paras.db_migrator.entity.oracle.OracleClientEntity;
import com.paras.db_migrator.repository.oracle.OracleClientRepository;
import com.paras.db_migrator.supplier.DbDataSupplier;
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

@Configuration
@RequiredArgsConstructor
public class MigrateToOracle {

    private final OracleClientRepository oracleClientRepository;
    private final JobRepository oracleJobRepository;
    private final PlatformTransactionManager oracleTransactionManager;
    private final JobListener jobListener;
    private final DbDataSupplier dbDataSupplier;

    @Bean
    public Job migrateToOracleJob() {
        return new JobBuilder("migrateToOracleJob", oracleJobRepository).listener(jobListener)
                                                                        .start(migrateToOracleStep())
                                                                        .build();
    }

    @Bean
    public Step migrateToOracleStep() {
        return new StepBuilder(
                "migrateToOracle-step", oracleJobRepository).<OracleClientEntity, OracleClientEntity>chunk(
                                                                    100, oracleTransactionManager)
                                                            .reader(migrateOracleDataReader(DbType.ORACLE.getValue()))
                                                            .processor(migrateOracleDataProcessor())
                                                            .writer(migrateOracleDataWriter())
                                                            .transactionManager(oracleTransactionManager)
                                                            .build();
    }

    @Bean
    @StepScope
    public ItemReader<OracleClientEntity> migrateOracleDataReader(
            @Value("#{jobParameters['source']}")
            String source) {
        List<OracleClientEntity> items = dbDataSupplier.getOracleData(DbType.valueOf(source.toUpperCase()));
        return new ListItemReader<>(items);
    }

    @Bean
    public ItemProcessor<OracleClientEntity, OracleClientEntity> migrateOracleDataProcessor() {
        return item -> item;
    }

    @Bean
    public ItemWriter<OracleClientEntity> migrateOracleDataWriter() {
        return oracleClientRepository::saveAll;
    }
}
