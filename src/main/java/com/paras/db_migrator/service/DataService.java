package com.paras.db_migrator.service;

import com.paras.db_migrator.dto.DataInsertRequestDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface DataService {
    ResponseEntity<ResponseDTO> insertDataToMySql(DataInsertRequestDTO dataInsertRequestDTO);

    ResponseEntity<ResponseDTO> insertDataToPostgres(DataInsertRequestDTO dataInsertRequestDTO);

    ResponseEntity<ResponseDTO> insertDataToOracle(DataInsertRequestDTO dataInsertRequestDTO);

    ResponseEntity<ResponseDTO> insertDataToAll(DataInsertRequestDTO dataInsertRequestDTO);

}
