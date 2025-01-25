package com.paras.db_migrator.repository.postgres;

import com.paras.db_migrator.entity.postgres.PostgresClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresClientRepository extends JpaRepository<PostgresClientEntity, Long> {
}