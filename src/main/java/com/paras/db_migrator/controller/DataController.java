package com.paras.db_migrator.controller;

import com.paras.db_migrator.dto.DataInsertRequestDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import com.paras.db_migrator.service.DataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data/insert")
@Tag(name = "Data Controller",
     description = "Insert demo data to different databases")
public class DataController {

    private final DataService dataService;

    @PostMapping("/mysql")
    public ResponseEntity<ResponseDTO> insertDataToMySql(
            @RequestBody
            DataInsertRequestDTO dataInsertRequestDTO) {
        return dataService.insertDataToMySql(dataInsertRequestDTO);
    }

    @PostMapping("/postgres")
    public ResponseEntity<ResponseDTO> insertDataToPostgres(
            @RequestBody
            DataInsertRequestDTO dataInsertRequestDTO) {
        return dataService.insertDataToPostgres(dataInsertRequestDTO);
    }

    @PostMapping("/oracle")
    public ResponseEntity<ResponseDTO> insertDataToOracle(
            @RequestBody
            DataInsertRequestDTO dataInsertRequestDTO) {
        return dataService.insertDataToOracle(dataInsertRequestDTO);
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDTO> insertDataToAll(
            @RequestBody
            DataInsertRequestDTO dataInsertRequestDTO) {
        return dataService.insertDataToAll(dataInsertRequestDTO);
    }

    //    @PostMapping("/rollback")
    //    public ResponseEntity<ResponseDTO> rollbackData() {
    //        return dataService.rollbackData();
    //    }
}
