package com.paras.db_migrator.oracle.repository;

import com.paras.db_migrator.oracle.entity.OracleClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OracleClientRepository extends JpaRepository<OracleClientEntity, Long> {
}