package com.pepit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AlertDto {

    private FilterDto filter;

    private Long user;

    List<String> listProductsId;

    boolean isConsulted;

    LocalDateTime dateConsulted;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
