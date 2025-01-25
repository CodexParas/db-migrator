package com.paras.db_migrator.config.job;

import com.paras.db_migrator.entity.mysql.MySqlClientEntity;
import com.paras.db_migrator.generator.MockDataGenerator;
import com.paras.db_migrator.repository.mysql.MySqlClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
public class MySqlBatchJobConfig {

    private final MockDataGenerator mockDataGenerator;
    private final MySqlClientRepository mySqlClientRepository;
    private final JobRepository mySqlJobRepository;
    private final PlatformTransactionManager mySqlTransactionManager;
    private final JobListener jobListener;

    @Bean
    public Job mySqlJob() {
        return new JobBuilder("mySqlJob", mySqlJobRepository).listener(jobListener)
                                                             .start(insertMySqlDataStep())
                                                             .incrementer(new RunIdIncrementer())
                                                             .build();
    }

    @Bean
    public Step insertMySqlDataStep() {
        return new StepBuilder("mysql-step", mySqlJobRepository).<MySqlClientEntity, MySqlClientEntity>chunk(
                                                                        100, mySqlTransactionManager)
                                                                .reader(mySqlMockDataReader(null))
                                                                .processor(mySqlMockDataProcessor())
                                                                .writer(mySqlMockDataWriter())
                                                                .transactionManager(mySqlTransactionManager)
                                                                .build();
    }

    @Bean
    @StepScope
    public ItemReader<MySqlClientEntity> mySqlMockDataReader(
            @Value("#{jobParameters['recordCount']}")
            String count) {
        List<MySqlClientEntity> mockData = mockDataGenerator.mySqlGenerateData(
                Integer.parseInt(Optional.of(count).orElse("100")));
        return new ListItemReader<>(mockData);
    }

    @Bean
    public ItemProcessor<MySqlClientEntity, MySqlClientEntity> mySqlMockDataProcessor() {
        return item -> item;
    }

    @Bean
    public ItemWriter<MySqlClientEntity> mySqlMockDataWriter() {
        return mySqlClientRepository::saveAll;
    }
}
