package com.paras.db_migrator.repository.oracle;

import com.paras.db_migrator.entity.oracle.OracleClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OracleClientRepository extends JpaRepository<OracleClientEntity, Long> {
}