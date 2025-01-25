package com.paras.db_migrator.supplier;

import com.paras.db_migrator.constants.DbType;
import com.paras.db_migrator.entity.mysql.MySqlClientEntity;
import com.paras.db_migrator.entity.oracle.OracleClientEntity;
import com.paras.db_migrator.entity.postgres.PostgresClientEntity;
import com.paras.db_migrator.mapper.MigrationMapper;
import com.paras.db_migrator.repository.mysql.MySqlClientRepository;
import com.paras.db_migrator.repository.oracle.OracleClientRepository;
import com.paras.db_migrator.repository.postgres.PostgresClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbDataSupplier {

    private final MigrationMapper mapper;
    private final MySqlClientRepository mySqlClientRepository;
    private final OracleClientRepository oracleClientRepository;
    private final PostgresClientRepository postgresClientRepository;

    public List<MySqlClientEntity> getMySqlData(DbType source) {
        if(source.getValue().equalsIgnoreCase("postgres")) {
            List<MySqlClientEntity> entities = postgresClientRepository.findAll()
                                                                       .stream()
                                                                       .map(mapper::toMySql)
                                                                       .toList();
            log.info("Entities: {}", entities);
            return entities;
        }
        else if(source.getValue().equalsIgnoreCase("oracle")) {
            List<MySqlClientEntity> entities = oracleClientRepository.findAll().stream().map(mapper::toMySql).toList();
            log.info("Entities: {}", entities);
            return entities;

        }
        return List.of();
    }

    public List<PostgresClientEntity> getPostgresData(DbType source) {
        if(source.getValue().equalsIgnoreCase("mysql")) {
            return mySqlClientRepository.findAll().stream().map(mapper::toPostgres).collect(Collectors.toList());
        }
        else if(source.getValue().equalsIgnoreCase("oracle")) {
            return oracleClientRepository.findAll().stream().map(mapper::toPostgres).collect(Collectors.toList());
        }
        return List.of();
    }

    public List<OracleClientEntity> getOracleData(DbType source) {
        if(source.getValue().equalsIgnoreCase("mysql")) {
            return mySqlClientRepository.findAll().stream().map(mapper::toOracle).collect(Collectors.toList());
        }
        else if(source.getValue().equalsIgnoreCase("postgres")) {
            return postgresClientRepository.findAll().stream().map(mapper::toOracle).collect(Collectors.toList());
        }
        return List.of();
    }
}
