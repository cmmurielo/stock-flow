package com.inventario.stock_flow.inventory.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductSupplierResponse(
        UUID associationId,
        String productName,
        String supplierName,
        BigDecimal purchaseCost,
        LocalDateTime lastUpdate) {
}
