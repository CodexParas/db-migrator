package com.paras.db_migrator.repository.mysql;

import com.paras.db_migrator.entity.mysql.MySqlClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlClientRepository extends JpaRepository<MySqlClientEntity, Long> {
}