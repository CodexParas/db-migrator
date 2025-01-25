package com.paras.db_migrator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String status;
    private String code;
    private String message;
    private Object response;

    public static ResponseDTO success(String message, Object response) {
        return ResponseDTO.builder().status("Success").code("200").message(message).response(response).build();
    }
}
