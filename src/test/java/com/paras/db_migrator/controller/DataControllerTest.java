package com.paras.db_migrator.controller;

import com.paras.db_migrator.dto.DataInsertRequestDTO;
import com.paras.db_migrator.dto.ResponseDTO;
import com.paras.db_migrator.service.DataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DataControllerTest {

    @InjectMocks
    private DataController dataController;

    @Mock
    private DataService dataService;

    private DataInsertRequestDTO dataInsertRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dataInsertRequestDTO = new DataInsertRequestDTO(10);

        ResponseDTO responseDTO = ResponseDTO.success("Data inserted successfully", new Object());

        // Mock the service layer methods
        when(dataService.insertDataToMySql(dataInsertRequestDTO)).thenReturn(
                ResponseEntity.status(HttpStatus.OK).body(responseDTO));
        when(dataService.insertDataToPostgres(dataInsertRequestDTO)).thenReturn(
                ResponseEntity.status(HttpStatus.OK).body(responseDTO));
        when(dataService.insertDataToOracle(dataInsertRequestDTO)).thenReturn(
                ResponseEntity.status(HttpStatus.OK).body(responseDTO));
        when(dataService.insertDataToAll(dataInsertRequestDTO)).thenReturn(
                ResponseEntity.status(HttpStatus.OK).body(responseDTO));
    }

    @Test
    void testInsertDataToMySql() {
        ResponseEntity<ResponseDTO> response = dataController.insertDataToMySql(dataInsertRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Data inserted successfully", response.getBody().getMessage());
        assertEquals("200", response.getBody().getCode());
        assertEquals("Success", response.getBody().getStatus());
        verify(dataService, times(1)).insertDataToMySql(dataInsertRequestDTO);
    }

    @Test
    void testInsertDataToPostgres() {
        ResponseEntity<ResponseDTO> response = dataController.insertDataToPostgres(dataInsertRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Data inserted successfully", response.getBody().getMessage());
        assertEquals("200", response.getBody().getCode());
        assertEquals("Success", response.getBody().getStatus());
        verify(dataService, times(1)).insertDataToPostgres(dataInsertRequestDTO);
    }

    @Test
    void testInsertDataToOracle() {
        ResponseEntity<ResponseDTO> response = dataController.insertDataToOracle(dataInsertRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Data inserted successfully", response.getBody().getMessage());
        assertEquals("200", response.getBody().getCode());
        assertEquals("Success", response.getBody().getStatus());
        verify(dataService, times(1)).insertDataToOracle(dataInsertRequestDTO);
    }

    @Test
    void testInsertDataToAll() {
        ResponseEntity<ResponseDTO> response = dataController.insertDataToAll(dataInsertRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Data inserted successfully", response.getBody().getMessage());
        assertEquals("200", response.getBody().getCode());
        assertEquals("Success", response.getBody().getStatus());
        verify(dataService, times(1)).insertDataToAll(dataInsertRequestDTO);
    }
}
