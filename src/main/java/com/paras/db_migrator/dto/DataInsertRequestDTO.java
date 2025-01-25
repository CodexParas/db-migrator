package com.paras.db_migrator.dto;

import org.springframework.boot.context.properties.bind.DefaultValue;

public record DataInsertRequestDTO(
        @DefaultValue("10") Integer count
) {
}
