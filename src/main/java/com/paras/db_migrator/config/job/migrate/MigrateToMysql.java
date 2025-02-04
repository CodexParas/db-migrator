package com.paras.db_migrator.config.job.migrate;

import com.paras.db_migrator.config.job.JobListener;
import com.paras.db_migrator.constants.DbType;
import com.paras.db_migrator.mysql.entity.MySqlClientEntity;
import com.paras.db_migrator.mysql.repository.MySqlClientRepository;
import com.paras.db_migrator.supplier.DbDataSupplier;
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

@Configuration
@RequiredArgsConstructor
public class MigrateToMysql {

    private final DbDataSupplier dbDataSupplier;
    private final MySqlClientRepository mySqlClientRepository;
    private final JobRepository mySqlJobRepository;
    private final PlatformTransactionManager mySqlTransactionManager;
    private final JobListener jobListener;

    @Bean
    public Job migrateToMysqlJob() {
        return new JobBuilder("migrateToMysqlJob", mySqlJobRepository).listener(jobListener)
                                                                      .start(migrateToMySqlStep())
                                                                      .incrementer(new RunIdIncrementer())
                                                                      .build();
    }

    @Bean
    public Step migrateToMySqlStep() {
        return new StepBuilder("migrateToMySql-step", mySqlJobRepository).<MySqlClientEntity, MySqlClientEntity>chunk(
                                                                                 100, mySqlTransactionManager)
                                                                         .reader(migrateMySqlDataReader(
                                                                                 DbType.POSTGRES.getValue()))
                                                                         .processor(migrateMySqlDataProcessor())
                                                                         .writer(migrateMySqlDataWriter())
                                                                         .transactionManager(mySqlTransactionManager)
                                                                         .build();
    }

    @Bean
    @StepScope
    public ItemReader<MySqlClientEntity> migrateMySqlDataReader(
            @Value("#{jobParameters['source']}")
            String source) {
        List<MySqlClientEntity> data = dbDataSupplier.getMySqlData(DbType.valueOf(source.toUpperCase()));
        return new ListItemReader<>(data);
    }

    @Bean
    public ItemProcessor<MySqlClientEntity, MySqlClientEntity> migrateMySqlDataProcessor() {
        return item -> item;
    }

    @Bean
    public ItemWriter<MySqlClientEntity> migrateMySqlDataWriter() {
        return mySqlClientRepository::saveAllAndFlush;
    }
}
