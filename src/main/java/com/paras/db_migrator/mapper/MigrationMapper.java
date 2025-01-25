package com.paras.db_migrator.mapper;

import com.paras.db_migrator.entity.mysql.MySqlClientEntity;
import com.paras.db_migrator.entity.oracle.OracleClientEntity;
import com.paras.db_migrator.entity.postgres.PostgresClientEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MigrationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "zip", source = "zip")
    @Mapping(target = "isActive", expression = "java(client.isActive())")
    MySqlClientEntity toMySql(PostgresClientEntity client);

    @InheritConfiguration(name = "toMySql")
    @Mapping(target = "id", ignore = true)
    MySqlClientEntity toMySql(OracleClientEntity client);

    @InheritConfiguration(name = "toMySql")
    @Mapping(target = "id", ignore = true)
    PostgresClientEntity toPostgres(MySqlClientEntity client);

    @InheritConfiguration(name = "toPostgres")
    @Mapping(target = "id", ignore = true)
    PostgresClientEntity toPostgres(OracleClientEntity client);

    @InheritConfiguration(name = "toMySql")
    @Mapping(target = "id", ignore = true)
    OracleClientEntity toOracle(PostgresClientEntity client);

    @InheritConfiguration(name = "toOracle")
    @Mapping(target = "id", ignore = true)
    OracleClientEntity toOracle(MySqlClientEntity client);
}
