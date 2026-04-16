package com.inventario.stock_flow.product.infrastructure.rest.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock) {
}