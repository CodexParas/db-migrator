package com.paras.db_migrator.config.job;

import com.paras.db_migrator.entity.postgres.PostgresClientEntity;
import com.paras.db_migrator.generator.MockDataGenerator;
import com.paras.db_migrator.repository.postgres.PostgresClientRepository;
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
public class PostgresBatchJobConfig {

    private final MockDataGenerator mockDataGenerator;
    private final PostgresClientRepository postgreSqlClientRepository;
    private final JobRepository postgreSqlJobRepository;
    private final PlatformTransactionManager postgreSqlTransactionManager;
    private final JobListener jobListener;

    @Bean
    public Job postgresJob() {
        return new JobBuilder("postgresJob", postgreSqlJobRepository).listener(jobListener)
                                                                     .start(insertPostgresStep())
                                                                     .build();
    }

    @Bean
    public Step insertPostgresStep() {
        return new StepBuilder(
                "postgres-step",
                               postgreSqlJobRepository).<PostgresClientEntity, PostgresClientEntity>chunk(
                                                               100, postgreSqlTransactionManager)
                                                       .reader(postgresMockDataReader(null))
                                                       .processor(postgresMockDataProcessor())
                                                       .writer(postgresMockDataWriter())
                                                       .transactionManager(postgreSqlTransactionManager)
                                                       .build();
    }

    @Bean
    @StepScope
    public ItemReader<PostgresClientEntity> postgresMockDataReader(
            @Value("#{jobParameters['recordCount']}")
            String count) {
        List<PostgresClientEntity> mockData = mockDataGenerator.postgresGenerateData(
                Integer.parseInt(Optional.of(count).orElse("100")));
        return new ListItemReader<>(mockData);
    }

    @Bean
    public ItemProcessor<PostgresClientEntity, PostgresClientEntity> postgresMockDataProcessor() {
        return item -> item;
    }

    @Bean
    public ItemWriter<PostgresClientEntity> postgresMockDataWriter() {
        return postgreSqlClientRepository::saveAll;
    }
}
