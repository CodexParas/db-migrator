package com.paras.db_migrator.postgres.repository;

import com.paras.db_migrator.postgres.entity.PostgresClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresClientRepository extends JpaRepository<PostgresClientEntity, Long> {
}