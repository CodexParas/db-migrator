package com.paras.db_migrator.mysql.repository;

import com.paras.db_migrator.mysql.entity.MySqlClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlClientRepository extends JpaRepository<MySqlClientEntity, Long> {
}