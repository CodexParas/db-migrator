package com.paras.db_migrator.config.job.migrate;

import com.paras.db_migrator.config.job.JobListener;
import com.paras.db_migrator.constants.DbType;
import com.paras.db_migrator.entity.postgres.PostgresClientEntity;
import com.paras.db_migrator.repository.postgres.PostgresClientRepository;
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
public class MigrateToPostgres {

    private final PostgresClientRepository postgreSqlClientRepository;
    private final JobRepository postgreSqlJobRepository;
    private final PlatformTransactionManager postgreSqlTransactionManager;
    private final JobListener jobListener;
    private final DbDataSupplier dbDataSupplier;

    @Bean
    public Job migrateToPostgresJob() {
        return new JobBuilder("migrateToPostgresJob", postgreSqlJobRepository).listener(jobListener)
                                                                              .start(migrateToPostgresStep())
                                                                              .build();
    }

    @Bean
    public Step migrateToPostgresStep() {
        return new StepBuilder(
                "migrateToPostgres-step", postgreSqlJobRepository).<PostgresClientEntity, PostgresClientEntity>chunk(
                                                                          100, postgreSqlTransactionManager)
                                                                  .reader(migratePostgresDataReader(
                                                                          DbType.ORACLE.getValue()))
                                                                  .processor(migratePostgresDataProcessor())
                                                                  .writer(migratePostgresDataWriter())
                                                                  .transactionManager(postgreSqlTransactionManager)
                                                                  .build();
    }

    @Bean
    @StepScope
    public ItemReader<PostgresClientEntity> migratePostgresDataReader(
            @Value("#{jobParameters['source']}")
            String source) {
        List<PostgresClientEntity> items = dbDataSupplier.getPostgresData(DbType.valueOf(source.toUpperCase()));
        return new ListItemReader<>(items);
    }

    @Bean
    public ItemProcessor<PostgresClientEntity, PostgresClientEntity> migratePostgresDataProcessor() {
        return item -> item;
    }

    @Bean
    public ItemWriter<PostgresClientEntity> migratePostgresDataWriter() {
        return postgreSqlClientRepository::saveAll;
    }
}
