package com.inventario.stock_flow.inventory.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductSupplierResponse(
        Long associationId,
        String productName,
        String supplierName,
        BigDecimal purchaseCost,
        LocalDateTime lastUpdate) {
}
